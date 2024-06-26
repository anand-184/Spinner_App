package com.anand.spinner_app


import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.ListAdapter
import com.anand.spinner_app.databinding.CustomDialogBinding
import com.anand.spinner_app.databinding.FragmentListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null
    var listFragmentBinding:FragmentListBinding? = null
    var citylist = mutableListOf<String>()
    lateinit var arrayAdapter: ArrayAdapter<String>

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
        listFragmentBinding = FragmentListBinding.inflate(layoutInflater)
        return listFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,citylist)
        listFragmentBinding?.listView?.adapter = arrayAdapter
        listFragmentBinding?.fabAddList?.setOnClickListener {
            val customDialogBinding = CustomDialogBinding.inflate(layoutInflater)
            val dialog = Dialog(requireContext()).apply {
                setContentView(customDialogBinding.root)
                show()
            }
            customDialogBinding?.btnAdd?.setOnClickListener {
                if(customDialogBinding.etCity.text.toString().isNullOrEmpty()){
                    customDialogBinding.etCity.error = "Enter the City for List"
                }else{
                    citylist.add(customDialogBinding?.etCity?.text.toString())
                }
        }



        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    }


