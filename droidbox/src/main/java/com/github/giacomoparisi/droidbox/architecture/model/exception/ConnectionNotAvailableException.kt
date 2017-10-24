package com.github.giacomoparisi.droidbox.architecture.model.exception

import com.github.giacomoparisi.droidbox.R

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/giacomoParisi
 */

/**
 *
 * ManagedException used for networking error
 */
class ConnectionNotAvailableException(
        errorMessageId: Int = R.string.ERROR_NetworkError,
        retryMessageId: Int = R.string.ERROR_Reconnect) : ManagedException(errorMessageId, retryMessageId)
