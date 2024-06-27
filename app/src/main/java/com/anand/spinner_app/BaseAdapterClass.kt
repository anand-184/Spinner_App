package com.anand.spinner_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BaseAdapterClass(var colorArray: ArrayList<String>) :BaseAdapter() {
    override fun getCount(): Int {
        return colorArray.size
    }

    override fun getItem(p0: Int): Any {
        return colorArray[p0]
    }

    override fun getItemId(p0: Int): Long {
       return 1L
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
      val view = LayoutInflater.from(p1?.context).inflate(R.layout.item_list_adapter,p2,false)
        var tvname = view.findViewById<TextView>(R.id.tvname)
        var tvrollno = view.findViewById<TextView>(R.id.tvrollno)
        tvname.setText(colorArray[p0])
        return view
    }
}