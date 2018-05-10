package com.github.giacomoparisi.droidbox.architecture.model

import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Giacomo Parisi on 28/03/18.
 * https://github.com/giacomoParisi
 */

fun <T> loadingErrorRequest(
        viewModel: DroidViewModel,
        request: Single<T>,
        error: (Throwable) -> Unit): Single<T> =
        request.doOnSubscribe { viewModel.droidUIManager.showLoading() }
                .doOnSuccess { viewModel.droidUIManager.hideLoading() }
                .doOnError { error(it) }

fun <T> loadingErrorRequest(
        viewModel: DroidViewModel,
        request: Observable<T>,
        error: (Throwable) -> Unit): Observable<T> =
        request.doOnSubscribe { viewModel.droidUIManager.showLoading() }
                .doOnComplete { viewModel.droidUIManager.hideLoading() }
                .doOnError { error(it) }

fun <T> loadingRequest(viewModel: DroidViewModel, request: Single<T>): Single<T> =
        request.doOnSubscribe { viewModel.droidUIManager.showLoading() }
                .doFinally { viewModel.droidUIManager.hideLoading() }

fun <T> loadingRequest(viewModel: DroidViewModel, request: Observable<T>): Observable<T> =
        request.doOnSubscribe { viewModel.droidUIManager.showLoading() }
                .doFinally { viewModel.droidUIManager.hideLoading() }

fun <T> errorRequest(request: Single<T>, error: (Throwable) -> Unit): Single<T> =
        request.doOnError { error(it) }

fun <T> errorRequest(request: Observable<T>, error: (Throwable) -> Unit): Observable<T> =
        request.doOnError { error(it) }
