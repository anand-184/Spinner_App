package com.anand.spinner_app

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.anand.spinner_app.databinding.FragmentMainFragmentBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentMainFragmentBinding? = null
    var navController: NavController? = null

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
        binding = FragmentMainFragmentBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.fabspinner?.setOnClickListener {
            Dialog(requireContext()).apply {
                setContentView(R.layout.custom_dialog)
                window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
                show()
                var city = findViewById<EditText>(R.id.etCity)
                var add = findViewById<Button>(R.id.btnAdd)
                var cityarray = Array<String>(3){"$city"}
                add.setOnClickListener {
                    if(city?.text?.toString().isNullOrEmpty()){
                        city?.error="Please Enter one City"
                    }else{
                        for (i in 0..cityarray.size){
                            cityarray[i]=city.text.toString()

                        }

                    }
                }
            }



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