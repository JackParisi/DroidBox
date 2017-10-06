package com.github.jackparisi.droidbox.architecture.model.exception

import com.github.jackparisi.droidbox.R

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

class ConnectionNotAvailableException(
        errorMessageId: Int = R.string.connection_error_message,
        retryMessageId: Int = R.string.reconnect) : ManagedException(errorMessageId, retryMessageId)
