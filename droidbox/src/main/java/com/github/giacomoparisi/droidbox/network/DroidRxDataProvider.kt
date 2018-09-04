package com.github.giacomoparisi.droidbox.network

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.github.giacomoparisi.droidbox.architecture.model.exception.EmptyRepositoryException
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


/**
 * Created by Giacomo Parisi on 09/07/2017.
 * https://github.com/giacomoParisi
 */

/**
 *
 * Data provider based on RX
 * Load data from database and perform network requests with RX Flowable
 * The repository follow this FLOW*:
 * should load from db?
 * YES ==> load from db ==> should fetch from network?
 *         YES ==> should load from db before fetch?
 *                 YES ==> emit database data ==> fetch form network ==> save data to db ==> emit fetched data
 *                 NO  ==> fetch form network ==> save data to db ==> emit fetched data
 *         NO  ==> emit database data
 * NO  ==> should fetch from network?
 *         YES ==> fetch from network ==> save data to db ==> emit fetch data
 *         NO  ==> exit
 */
abstract class DroidRxDataProvider<ResultType> : DroidDataProvider<Flowable<DroidResource<ResultType>>>() {


    // Repository flowable (subscribe to it to start the repository)
    override final var repository: Flowable<DroidResource<ResultType>> =
            Flowable.create({ emitter: FlowableEmitter<DroidResource<ResultType>> ->
                startRepository(emitter)
            }, BackpressureStrategy.BUFFER)

    /**
     *
     * Start to load/fetch the data based on the repository FLOW*
     *
     * @param emitter The emitter of the flowable that execute this function
     */
    private fun startRepository(emitter: FlowableEmitter<DroidResource<ResultType>>) {
        val dbSource = loadFromDb(databaseData)
        when {
            dbSource != null ->
                dbSource.subscribeOn(Schedulers.io())
                        .subscribe({
                            if (shouldFetch(it)) {
                                fetchFromNetwork(it, emitter)
                            } else {
                                emitter.onNext(DroidResource.DatabaseResource(it))
                            }
                        }, {

                            Timber.e(it.message)
                            emitter.onError(it)
                        })
            shouldFetch(null) -> fetchFromNetwork(null, emitter)
            else -> emitter.onError(EmptyRepositoryException())
        }
    }

    /**
     *
     * Emit the database data if exist and if shouldLoadFromDbBeforeFetch,
     * then fetch from network and try save the data to db
     *
     * @param dbSource Database data (load previously)
     * @param emitter The emitter of the flowable that execute this function
     */
    private fun fetchFromNetwork(dbSource: ResultType?, emitter: FlowableEmitter<DroidResource<ResultType>>) {
        val apiResponse = fetchFromNetwork(networkData)

        if (dbSource != null && shouldLoadFromDbBeforeFetch()) {
            emitter.onNext(DroidResource.DatabaseResource(dbSource))
        }

        apiResponse?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    if (it != null) {
                        saveResult(it, emitter)
                    }
                }, { emitter.onError(it) })
    }

    /**
     *
     * Try to save the data to database and after emit them
     *
     * @param apiResponse The new data to save (fetched from network)
     * @param emitter The emitter of the flowable that execute this function
     */
    private fun saveResult(apiResponse: ResultType, emitter: FlowableEmitter<DroidResource<ResultType>>) {
        val save = Single.create(SingleOnSubscribe<ResultType> {
            saveCallResult(apiResponse)
            it.onSuccess(apiResponse)
        })

        save.subscribeOn(Schedulers.io())
                .subscribe(
                        { emitter.onNext(DroidResource.NetworkResource(apiResponse)) }, {
                    Timber.e(it.message)
                    emitter.onError(it)
                }
                )
    }

    /**
     *
     * Called to save the new fetched data to db
     *
     * @param data The new data to save (fetched from network)
     */
    @WorkerThread
    protected abstract fun saveCallResult(data: ResultType?)

    /**
     *
     * Called to know if the repository should fetch the data from the network
     *
     * @param data The data loaded from the database (null if not exist)
     */
    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    /**
     *
     * Called to know if the repository should emit the data loaded from db
     * before fetch the new data
     */
    @MainThread
    protected abstract fun shouldLoadFromDbBeforeFetch(): Boolean

    /**
     *
     * Called to create the call to get the cached data from the database
     *
     * @param data The map of data needed for the database load request
     */
    @MainThread
    protected abstract fun loadFromDb(data: Map<String, String>?): Flowable<ResultType>?

    /**
     *
     * Called to create the API call to fetch the new data from network
     *
     * @param data The map of data needed for the API request
     */
    @MainThread
    protected abstract fun fetchFromNetwork(data: Map<String, String>?): Single<ResultType>?

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    @MainThread
    protected fun onFetchFailed() {
    }
}
