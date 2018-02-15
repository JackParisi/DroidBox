package com.github.giacomoparisi.droidboxsample.ui.home

import android.os.Bundle
import com.github.giacomoparisi.droidbox.architecture.dagger.Injectable
import com.github.giacomoparisi.droidboxsample.core.droidbox.DroidBoxSampleActivity
import com.github.giacomoparisi.droidboxsample.databinding.ActivityHomeBinding
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 14/12/2017.
 * https://github.com/giacomoParisi
 */
@Injectable
class HomeActivity : DroidBoxSampleActivity() {

    @Inject
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        observeViewModel(viewModel)
        binding.viewModel = viewModel
        setContentView(binding.root)
        binding.executePendingBindings()
    }
}
