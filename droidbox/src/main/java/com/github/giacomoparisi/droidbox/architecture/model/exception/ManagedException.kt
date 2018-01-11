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

    constructor(errorMessageRes: Int) {
        this.errorMessageRes = errorMessageRes
    }

    constructor(errorMessage: String) {
        this.errorMessage = errorMessage
    }

    constructor(errorMessageRes: Int, cause: Throwable) : super(cause) {
        this.errorMessageRes = errorMessageRes
    }

    constructor(errorMessage: String, cause: Throwable) : super(cause) {
        this.errorMessage = errorMessage
    }
}
