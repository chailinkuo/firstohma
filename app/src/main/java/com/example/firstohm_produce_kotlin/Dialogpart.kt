package com.example.firstohm_produce_kotlin

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import co.ubunifu.kotlinhttpsample.Lib.WebapiClient
import kotlinx.android.synthetic.main.custom_layout_quant_input.view.*
import kotlinx.android.synthetic.main.dialog_partedit.*
import kotlinx.android.synthetic.main.dialog_prd_test.btn_prd_cancel
import kotlinx.android.synthetic.main.dialog_prd_test.btn_prd_submit
import org.json.JSONArray
import org.json.JSONObject


class Dialogpart(view1: View) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //getDialog()!!.getWindow()?.setBackgroundDrawableResource(R.drawable.ic_launcher_background);
        return inflater.inflate(R.layout.dialog_partedit, container, false)
    }
    var listViewModelArrayList = ArrayList<ListViewModelAdapterflowspile100>()
    var v1=view1
    override fun onStart() {
        super.onStart()
        try {
            val width = 1000
            val height = 1000
            dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)


                try {

                    var listViewAdapter = context?.let {
                        ListViewModelAdapterflowspile1_part(
                                it,
                                listViewModelArrayList as ArrayList<ListViewModelAdapterflowspile100>
                        )
                    }
                    var subquan=quan.text.toString()
                    listViewModelArrayList.add(
                            ListViewModelAdapterflowspile100(
                                    1,"ID" ,  "小批ID",
                                    "工令單號", "小批時間"
                                    ,"批次號", "小批完成數量", "小批開始數量"

                            )
                    )
                    var webapiClient = WebapiClient()
                    //var url=MainActivity.ip+"/PrdMgn/apiQueryNaibeiBySigID?dept=花蓮切割&SignID=193772"
                    var url=MainActivity.ip+"/PrdMgn/apiQueryNaibeiBySigID?dept=花蓮切割&SignID="+MainActivity.SIGNID
                    var jsonString: String? = webapiClient.requestPOST(url, JSONObject())

                    Log.d("url",url)
                    val obj = JSONObject(jsonString)
                    val array = JSONArray(obj.optString("Data"))
                    for (i in 0 until array.length()) {
                        val jsonObject = array.getJSONObject(i)
                        val SUBFLOWID = jsonObject.getString("naibeiID")
                        val BATCH_QTY = jsonObject.getString("SignID")
                        var DONE_QTY = jsonObject.getString("批次")
                        val parentID = jsonObject.getString("工令單號")
                        val splitStep = jsonObject.getString("小批完成數量")
                        val TIME = jsonObject.getString("小批開始時間")
                        val splitStatus = jsonObject.getString("小批完成數量")
                        val short = jsonObject.getString("小批開始數量")
                        val short2 = jsonObject.getString("誤差率%")
                        val START_TIME= jsonObject.getString("備註")
                        Log.d("000123",parentID)
                        val splitStatus1 =splitStatus+"\t"+splitStep+"\t"+short+"\t"+short2
                        listViewModelArrayList.add(
                                ListViewModelAdapterflowspile100(
                                        1,BATCH_QTY ,  SUBFLOWID, parentID, TIME
                                        ,DONE_QTY, splitStep, short
                                )
                        )

                        listViewAdapter?.notifyDataSetChanged()
                        flow_list000.adapter = listViewAdapter
                    }

                    flow_list000.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

                        flow_list000.adapter = listViewAdapter
                        MainActivity.edit_naibeisign =listViewAdapter?.getItem(position)?.BATCH_QTY.toString()
                        val dialog = Dialog(context)
                        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                        dialog.setCancelable(false)
                        dialog.setContentView(R.layout.dialog_edit_partquan)

                        val inflater = LayoutInflater.from(context)
                        val v: View = inflater.inflate(R.layout.dialog_edit_partquan, null)
                        val yesBtn = dialog.findViewById(R.id.btn_submit) as Button
                        val btn_cls = dialog.findViewById(R.id.btn_cls) as Button
                        val editTextTextPersonName2=dialog.findViewById(R.id.editTextTextPersonName2) as EditText
                        yesBtn.setOnClickListener {
                        var url=MainActivity.ip+
                                    "/PrdMgn/apiUpdateNaibei?dept=花蓮切割&naibeiID="+MainActivity.edit_naibeisign+"&newQuant="+editTextTextPersonName2.text.toString()
                            Log.d("url",url)
                            var jsonString: String? = webapiClient.requestPOST(url, JSONObject())
                            val jsonStr = JSONObject(jsonString)
                            MainActivity.flow_message =jsonStr.getString("Message")
                            var ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(it)
                            //context?.let { it -> ui_Helper?.mesage("小批修改完成", context!!) };
                            val builder =
                                    androidx.appcompat.app.AlertDialog.Builder(dialog.getContext())
                            builder.setMessage("小批修改完成")
                            builder.setCancelable(false)
                            builder.setPositiveButton(
                                    "確認"
                            ) { dialog, id ->
                                v1.get_machins.performClick()
                            }

                            builder.show()
                            dialog.dismiss()
                        }
                        btn_cls.setOnClickListener {
                            dialog.dismiss()

                        }
                        dialog.show()
                        dismiss()
                    }
                }catch (ex: Exception){
                }



            btn_prd_submit.setOnClickListener {
            }
            btn_prd_cancel.setOnClickListener {
                dismiss()
            }
        } catch (ex: Exception) {

        }
    }
}
