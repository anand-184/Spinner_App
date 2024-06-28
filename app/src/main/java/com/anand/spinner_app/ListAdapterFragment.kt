package com.anand.spinner_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anand.spinner_app.databinding.FragmentListAdapterBinding

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListAdapterFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var listAdapterBinding: FragmentListAdapterBinding? = null
    var colorArray = arrayListOf("Red","Blue","yellow")
    private var baseAdapterClass = BaseAdapterClass(colorArray)


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
        listAdapterBinding = FragmentListAdapterBinding.inflate(layoutInflater)
        return listAdapterBinding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listAdapterBinding?.listAdapter?.adapter = baseAdapterClass


    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListAdapterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}