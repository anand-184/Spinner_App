package com.anand.spinner_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.anand.spinner_app.databinding.FragmentDataClassBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Data_Class_Fragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    var binding: FragmentDataClassBinding? = null
    var studentArray = arrayListOf<StudentDataClass>()
    var StudentAdapter = StudentAdapter(studentArray)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        studentArray.add(StudentDataClass("1","Anand","B.Tech"))
        studentArray.add(StudentDataClass("2","Aadhaya","CSE"))
        studentArray.add(StudentDataClass("3","Aarav","CSE"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      binding = FragmentDataClassBinding.inflate(layoutInflater)
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Data_Class_Fragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}