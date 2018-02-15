package com.github.giacomoparisi.droidboxsample.ui.validator

import android.os.Bundle
import com.github.giacomoparisi.droidbox.architecture.dagger.Injectable
import com.github.giacomoparisi.droidboxsample.core.droidbox.DroidBoxSampleActivity
import com.github.giacomoparisi.droidboxsample.databinding.ActivityValidatorBinding
import javax.inject.Inject

/**
 * Created by Giacomo Parisi on 14/12/2017.
 * https://github.com/giacomoParisi
 */
@Injectable
class ValidatorActivity : DroidBoxSampleActivity() {

    @Inject
    lateinit var viewModel: ValidatorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityValidatorBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        observeViewModel(viewModel)
        setContentView(binding.root)
    }
}
