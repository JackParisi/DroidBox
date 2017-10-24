package com.github.giacomoparisi.droidbox.architecture.model.exception

import android.content.res.Resources
import com.github.giacomoparisi.droidbox.R
import java.lang.Exception

/**
 * Created by Giacomo Parisi on 09/10/17.
 * https://github.com/giacomoParisi
 */

/**
 *
 * Exception raised when trying to start a repository without specifying
 * how to fetch from the network and load from db
 *
 * @param context Context used for exception message
 */
class EmptyRepositoryException : Exception(Resources.getSystem().getString(R.string.EXCEPTION_EmptyRepository))