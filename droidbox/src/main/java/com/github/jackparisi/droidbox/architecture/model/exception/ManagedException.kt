package com.github.jackparisi.droidbox.architecture.model.exception

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */
abstract class ManagedException : Exception {

    // Error message resource's id
    var errorMessageRes: Int = 0
        private set
    
    // Error message string
    var errorMessage: String? = null
        private set

    // Retry button label resource's id
    var retryButtonLabelId: Int = 0
        private set


    constructor(errorMessageRes: Int, retryButtonLabelId: Int) {
        this.errorMessageRes = errorMessageRes
        this.retryButtonLabelId = retryButtonLabelId
    }

    constructor(errorMessageRes: Int, retryButtonLabelId: Int, cause: Throwable) : super(cause) {
        this.errorMessageRes = errorMessageRes
        this.retryButtonLabelId = retryButtonLabelId
    }

    constructor(errorMessage: String, retryButtonLabelId: Int) {
        this.errorMessage = errorMessage
        this.errorMessageRes = 0
        this.retryButtonLabelId = retryButtonLabelId
    }

    constructor(errorMessage: String, retryButtonLabelId: Int, cause: Throwable) : super(cause) {
        this.errorMessage = errorMessage
        this.errorMessageRes = 0
        this.retryButtonLabelId = retryButtonLabelId
    }
}
