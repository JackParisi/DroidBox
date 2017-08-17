package com.github.jackparisi.droidbox.recycler;

import android.databinding.ViewDataBinding;

/**
 * Created by Giacomo Parisi on 10/04/17.
 * https://github.com/JackParisi
 */

public interface ViewHolderFactory<B extends ViewDataBinding> {

    DroidViewHolder newInstance(B binding);
}
