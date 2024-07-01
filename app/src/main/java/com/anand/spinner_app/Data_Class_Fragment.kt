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
        binding?.listViewData?.setOnItemClickListener { _, _, itemPosition, _ ->
            val customDialogBinding = CustomDialogSelectionBinding.inflate(layoutInflater)
            val customDialog = Dialog(requireContext()).apply {
                setContentView(customDialogBinding.root)
                show()
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
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
                        //  var selectedItemPosition = binding?.listViewData?.selectedItemPosition
                        studentArray.set(
                            itemPosition,
                            StudentDataClass(
                                "${customDialogBinding.etSelectedRoll.text.toString()}",
                                "${customDialogBinding.etSelectedName.text.toString()}",
                                "${customDialogBinding.etSelectedCourse.text.toString()}"
                            )
                        )
                        StudentAdapter.notifyDataSetChanged()
                        this.dismiss()
                    }
                }
            }
            return@setOnItemClickListener
        }
        binding?.listViewData?.setOnItemLongClickListener {_, _, itemPosition, _ ->

            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Delete Item")
            alertDialog.setMessage("Do you want to delete item")
            alertDialog.setPositiveButton("YES") { _, _ ->
                studentArray.removeAt(itemPosition)
                StudentAdapter.notifyDataSetChanged()
            }
            alertDialog.setNegativeButton("NO") { _, _ ->
            }
            alertDialog.show()
            return@setOnItemLongClickListener true
        }
        binding?.btnAddinList?.setOnClickListener {
            var customDialogBinding = CustomDialogSelectionBinding.inflate(layoutInflater)
            var addDialog = Dialog(requireContext()).apply {
                setContentView(customDialogBinding.root)
                show()
                window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
                customDialogBinding.btnUpdate.setOnClickListener {
                    if (customDialogBinding.etSelectedRoll.text.toString().isNullOrEmpty()) {
                        customDialogBinding.etSelectedRoll.error = "Enter Roll No."
                    } else if (customDialogBinding.etSelectedName.text.toString().isNullOrEmpty()) {
                        customDialogBinding.etSelectedName.error = "Enter your Name"
                    } else if (customDialogBinding.etSelectedCourse.text.toString()
                            .isNullOrEmpty()
                    ) {
                        customDialogBinding.etSelectedCourse.error = "Enter the Course"
                    } else{
                        studentArray.add(StudentDataClass("${customDialogBinding.etSelectedRoll.text.toString()}",
                            "${customDialogBinding.etSelectedName.text.toString()}",
                            "${customDialogBinding.etSelectedCourse.text.toString()}"))
                        StudentAdapter.notifyDataSetChanged()
                        this.dismiss()
                    }

                }

            }
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