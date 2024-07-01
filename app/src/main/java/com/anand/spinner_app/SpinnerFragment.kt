package com.anand.spinner_app

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.anand.spinner_app.databinding.FragmentSpinnerFragmentBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class SpinnerFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var spinnerFragmentBinding: FragmentSpinnerFragmentBinding? = null
    var cityarray = arrayListOf<String>()
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
        spinnerFragmentBinding = FragmentSpinnerFragmentBinding.inflate(layoutInflater)
        return spinnerFragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arrayAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cityarray)
        spinnerFragmentBinding?.dynamicSpinner?.adapter = arrayAdapter
        spinnerFragmentBinding?.fabAdd?.setOnClickListener {
            Dialog(requireContext()).apply {
                setContentView(R.layout.custom_dialog)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                show()
                var city = findViewById<EditText>(R.id.etCity)
                var add = findViewById<Button>(R.id.btnAdd)

                add.setOnClickListener {
                    if (city?.text?.toString().isNullOrEmpty()) {
                        city?.error = "Please Enter one City"
                    } else {
                        cityarray.add(city.text.toString())

                    }
                    this.dismiss()
                }

            }
        }
    }

    companion object {


        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SpinnerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }



}