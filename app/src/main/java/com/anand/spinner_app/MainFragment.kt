package com.anand.spinner_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.anand.spinner_app.databinding.FragmentMainBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var mainFragmentBinding: FragmentMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding = FragmentMainBinding.inflate(layoutInflater)
       return mainFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainFragmentBinding?.btnSpinner?.setOnClickListener {
            findNavController().navigate(R.id.spinner_Fragment)
        }
        mainFragmentBinding?.btnListView?.setOnClickListener {
            findNavController().navigate(R.id.listFragment)
        }
        mainFragmentBinding?.btnListAdapter?.setOnClickListener {
            findNavController().navigate(R.id.listAdapterFragment)
        }
        mainFragmentBinding?.btndataAdapter?.setOnClickListener {
            findNavController()?.navigate(R.id.data_Class_Fragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}