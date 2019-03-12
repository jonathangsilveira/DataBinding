package br.edu.jonathangsilveira.kotlindatabinding.ui


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.edu.jonathangsilveira.kotlindatabinding.R
import br.edu.jonathangsilveira.kotlindatabinding.data.GeneralViewModel
import br.edu.jonathangsilveira.kotlindatabinding.databinding.FirstFragmentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FirstFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.first_fragment, container, false)
        return with(binding) {
            binding.viewModel = ViewModelProviders.of(activity as FragmentActivity).get(GeneralViewModel::class.java)
            binding.lifecycleOwner = this@FirstFragment
            root
        }
        //return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment FirstFragment.
         */
        @JvmStatic
        fun newInstance() = FirstFragment()

    }
}
