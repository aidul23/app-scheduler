package xit.zubrein.demo.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T : ViewDataBinding, VM : ViewModel> : Fragment() {

    protected lateinit var binidng : T
    protected lateinit var viewModel : VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binidng = DataBindingUtil.inflate(
            inflater,
            getLayout(),
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(getViewModel())

        return binidng.root
    }

    abstract fun getLayout() : Int
    abstract fun getViewModel() : Class<VM>

}