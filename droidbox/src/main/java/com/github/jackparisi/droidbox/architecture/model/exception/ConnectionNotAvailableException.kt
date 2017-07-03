package com.github.jackparisi.droidbox.architecture.model.exception

import com.github.jackparisi.droidbox.R

/**
 * Created by Giacomo Parisi on 30/06/2017.
 * https://github.com/JackParisi
 */

class ConnectionNotAvailableException : ManagedException(R.string.connection_error_message, R.string.reconnect)
