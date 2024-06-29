package com.anand.spinner_app

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.anand.spinner_app.databinding.CustomDialogSelectionBinding
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
        studentArray.add(StudentDataClass("1", "Anand", "B.Tech"))
        studentArray.add(StudentDataClass("2", "Aadhaya", "CSE"))
        studentArray.add(StudentDataClass("3", "Aarav", "CSE"))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDataClassBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.listViewData?.adapter = StudentAdapter
        binding?.listViewData?.setOnItemClickListener { _, _, _, _ ->
            val customDialogBinding = CustomDialogSelectionBinding.inflate(layoutInflater)
            val customDialog = Dialog(requireContext()).apply {
                setContentView(R.layout.custom_dialog_selection)
                show()
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            var selectedItem = binding?.listViewData?.selectedItem as StudentDataClass
            val selectedItemPosition = binding?.listViewData?.selectedItemPosition as Int
            customDialogBinding.btnUpdate.setOnClickListener {
                if (customDialogBinding.etSelectedRoll.text.toString().isNullOrEmpty()) {
                    customDialogBinding.etSelectedRoll.error = "Enter Roll No."
                } else if (customDialogBinding.etSelectedName.text.toString().isNullOrEmpty()) {
                    customDialogBinding.etSelectedName.error = "Enter your Name"
                } else if (customDialogBinding.etSelectedCourse.text.toString()
                        .isNullOrEmpty()
                ) {
                    customDialogBinding.etSelectedCourse.error = "Enter the Course"
                } else {
                    studentArray[selectedItemPosition] = StudentDataClass(
                        "$customDialogBinding.etSelectedRoll",
                        "$customDialogBinding.etUpdatedName",
                        "$customDialogBinding.etUpdatedCourse"
                    )
                    customDialog.dismiss()
                }
            }
        }
        binding?.listViewData?.setOnItemLongClickListener { _, _, _, _ ->
            val selectedItemPosition = binding?.listViewData?.selectedItemPosition as Int
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Delete Item")
            alertDialog.setMessage("Do you want to delete item")
            alertDialog.setPositiveButton("YES") { _, _ ->
                studentArray.removeAt(selectedItemPosition)
                StudentAdapter.notifyDataSetChanged()
            }
            alertDialog.setNegativeButton("NO") { _, _ ->
            }
            return@setOnItemLongClickListener true
        }
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