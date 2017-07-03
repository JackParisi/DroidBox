package com.github.jackparisi.droidbox.architecture.model.exception

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */
abstract class ManagedException : Exception {

    var errorMessageRes: Int = 0
        private set
    var errorMessage: String? = null
        private set
    var retryButtonText: Int = 0
        private set

    constructor(errorMessageRes: Int, retryButtonText: Int) {
        this.errorMessageRes = errorMessageRes
        this.retryButtonText = retryButtonText
    }

    constructor(errorMessageRes: Int, retryButtonText: Int, cause: Throwable) : super(cause) {
        this.errorMessageRes = errorMessageRes
        this.retryButtonText = retryButtonText
    }

    constructor(errorMessage: String, retryButtonText: Int) {
        this.errorMessage = errorMessage
        this.errorMessageRes = 0
        this.retryButtonText = retryButtonText
    }

    constructor(errorMessage: String, retryButtonText: Int, cause: Throwable) : super(cause) {
        this.errorMessage = errorMessage
        this.errorMessageRes = 0
        this.retryButtonText = retryButtonText
    }
}
