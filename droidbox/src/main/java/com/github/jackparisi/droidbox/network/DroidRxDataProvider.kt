package com.github.jackparisi.droidbox.network

import android.support.annotation.MainThread
import android.support.annotation.WorkerThread
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber


/**
 * Created by Giacomo Parisi on 09/07/2017.
 * https://github.com/JackParisi
 */
abstract class DroidRxDataProvider<ResultType> : DroidDataProvider<Flowable<DroidResource<ResultType>>>() {


    override final var repository: Flowable<DroidResource<ResultType>> =
            Flowable.create({
                emitter: FlowableEmitter<DroidResource<ResultType>> ->
                startRepository(emitter)
            }, BackpressureStrategy.BUFFER)

    private fun startRepository(emitter: FlowableEmitter<DroidResource<ResultType>>) {
        val dbSource = loadFromDb(databaseData)
        if (dbSource != null) {
            dbSource.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
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
        } else if (shouldFetch(null)) {
            fetchFromNetwork(null, emitter)
        }
    }

    private fun fetchFromNetwork(dbSource: ResultType?, emitter: FlowableEmitter<DroidResource<ResultType>>) {
        val apiResponse = fetchFromNetwork(networkData)

        if (shouldLoadFromDbBeforeFetch() && dbSource != null) {
            emitter.onNext(DroidResource.DatabaseResource(dbSource))
        }

        apiResponse?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    if (it != null) {
                        saveResult(it, emitter)
                    }
                }, { emitter.onError(it) })
    }

    private fun saveResult(apiResponse: ResultType, emitter: FlowableEmitter<DroidResource<ResultType>>) {
        val save = Single.create(SingleOnSubscribe<ResultType> {
            saveCallResult(apiResponse)
            it.onSuccess(apiResponse)
        })

        save.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { emitter.onNext(DroidResource.NetworkResource(apiResponse)) }, {
                            Timber.e(it.message)
                    emitter.onError(it)
                        }
                )
    }

    // Called to save the repository of the API response into the database
    @WorkerThread
    protected abstract fun saveCallResult(data: ResultType?)

    // Called with the data in the database to decide whether it should be
// fetched from the network.
    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @MainThread
    protected abstract fun shouldLoadFromDbBeforeFetch(): Boolean

    // Called to get the cached data from the database
    @MainThread
    protected abstract fun loadFromDb(data: Map<String, String>?): Flowable<ResultType>?

    // Called to create the API call.
    @MainThread
    protected abstract fun fetchFromNetwork(data: Map<String, String>?): Single<ResultType>?

    // Called when the fetch fails. The child class may want to reset components
    // like rate limiter.
    @MainThread
    protected fun onFetchFailed() {
    }

}
