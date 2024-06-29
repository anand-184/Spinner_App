package com.anand.spinner_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.BaseAdapter

class StudentAdapter(var studentArray: ArrayList<StudentDataClass>): BaseAdapter() {
    override fun getCount(): Int {
        return studentArray.size
    }

    override fun getItem(p0: Int): Any {
        return studentArray[p0]

    }

    override fun getItemId(p0: Int): Long {
        return studentArray[p0]?.rollno?.toLong()?:0L
    }

    override fun getView(p0: Int, p1: View?, parent: ViewGroup?): View {
        val view= LayoutInflater.from(parent?.context).inflate(R.layout.item_student_class,parent,false)
        val tvRollno = view.findViewById<TextView>(R.id.tvRollno)
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val tvCourse = view.findViewById<TextView>(R.id.tvCourse)
        tvRollno.setText(studentArray[p0].rollno.toString())
        tvName.setText(studentArray[p0].name)
        tvCourse.setText(studentArray[p0].course)
        return view
    }
}