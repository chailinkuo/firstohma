package com.example.firstohm_produce_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.DialogFragment
import co.ubunifu.kotlinhttpsample.Lib.WebapiClient
import co.ubunifu.kotlinhttpsample.ListViewModelAdapterPlating_exclude1
import kotlinx.android.synthetic.main.dialog_spilflow_list.*
import kotlinx.android.synthetic.main.dialog_spilflow_list.flow_list
import kotlinx.android.synthetic.main.dialog_subflow_list.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.ArrayList

class dialog_edit_partquan: DialogFragment()  {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.ic_launcher_background);
        return inflater.inflate(R.layout.dialog_edit_partquan, container, false)
    }

    override fun onStart() {
        super.onStart()

    }
}