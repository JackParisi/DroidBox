package com.github.jackparisi.droidboxsample.activity.overwatch

import android.util.Log
import com.github.jackparisi.droidbox.architecture.model.DroidViewModel
import com.github.jackparisi.droidboxsample.core.DroidBoxSampleApplication
import com.github.jackparisi.droidboxsample.database.overwatch.OverwatchStatsRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 30/10/17.
 * https://github.com/JackParisi
 */

class OverwatchViewModel @Inject constructor(
        application: DroidBoxSampleApplication,
        private val overwatchStatsRepository: OverwatchStatsRepository) : DroidViewModel(application) {

    fun loadData() {
        overwatchStatsRepository.battletag = "Jack-23410"
        overwatchStatsRepository.dataProvider.repository
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d("T", it.toString())
                }) {
                    Log.d("T", it.toString())
                }
    }
}