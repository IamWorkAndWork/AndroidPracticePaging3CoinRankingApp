package app.practice.cryptowongnaitest.presentation.base

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseEpoxyModel<Binding : ViewDataBinding> constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    protected lateinit var binding: Binding

    init {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            layout(),
            this,
            true
        )
    }

    @LayoutRes
    abstract fun layout(): Int

}