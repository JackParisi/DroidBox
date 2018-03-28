package com.github.giacomoparisi.droidbox.architecture.model

import io.reactivex.Single

/**
 * Created by Giacomo Parisi on 28/03/18.
 * https://github.com/giacomoParisi
 */

fun <T> loadingErrorRequest(
        viewModel: DroidViewModel,
        request: Single<T>,
        error: (Throwable) -> Unit): Single<T> {
    return request
            .doOnSubscribe { viewModel.droidUIManager.showLoading() }
            .doOnSuccess { viewModel.droidUIManager.hideLoading() }
            .doOnError { error(it) }
}
 fun <T> loadingRequest(viewModel: DroidViewModel, request: Single<T>): Single<T> {
    return request
            .doOnSubscribe { viewModel.droidUIManager.showLoading() }
            .doFinally { viewModel.droidUIManager.hideLoading() }
}

fun <T> errorRequest(request: Single<T>, error: (Throwable) -> Unit): Single<T> {
    return request
            .doOnError { error(it) }
}