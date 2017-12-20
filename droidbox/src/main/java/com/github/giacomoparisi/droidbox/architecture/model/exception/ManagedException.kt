package com.github.giacomoparisi.droidbox.architecture.model.exception

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */
open class ManagedException : Exception {

    // Error message resource's id
    var errorMessageRes: Int = 0
        private set
    var errorMessage: String? = null
        private set

    // Retry button label resource's id
    var retryButtonLabelId: Int = 0
        private set
    var retryButtonLabel: String? = null


    constructor(errorMessageRes: Int, retryButtonLabelId: Int) {
        this.errorMessageRes = errorMessageRes
        this.retryButtonLabelId = retryButtonLabelId
    }

    constructor(errorMessage: String, retryButtonLabel: String) {
        this.errorMessage = errorMessage
        this.retryButtonLabel = retryButtonLabel
    }

    constructor(errorMessageRes: Int, retryButtonLabelId: Int, cause: Throwable) : super(cause) {
        this.errorMessageRes = errorMessageRes
        this.retryButtonLabelId = retryButtonLabelId
    }

    constructor(errorMessage: String, retryButtonLabel: String, cause: Throwable) : super(cause) {
        this.errorMessage = errorMessage
        this.retryButtonLabel = retryButtonLabel
    }

    constructor(errorMessageRes: Int, retryButtonLabel: String, cause: Throwable) : super(cause) {
        this.errorMessageRes = errorMessageRes
        this.retryButtonLabel = retryButtonLabel
    }

    constructor(errorMessage: String, retryButtonLabelId: Int, cause: Throwable) : super(cause) {
        this.errorMessage = errorMessage
        this.retryButtonLabelId = retryButtonLabelId
    }

    constructor(errorMessageRes: Int, retryButtonLabel: String) {
        this.errorMessageRes = errorMessageRes
        this.retryButtonLabel = retryButtonLabel
    }

    constructor(errorMessage: String, retryButtonLabelId: Int) {
        this.errorMessage = errorMessage
        this.retryButtonLabelId = retryButtonLabelId
    }
}
