package com.example.firstohm_produce_kotlin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.CalendarView.OnDateChangeListener
import androidx.appcompat.app.AppCompatActivity
import co.ubunifu.kotlinhttpsample.*
import co.ubunifu.kotlinhttpsample.Lib.WebapiClient
import kotlinx.android.synthetic.main.activity_edit_flow.*
import kotlinx.android.synthetic.main.activity_plating_exclude.*
import kotlinx.android.synthetic.main.custom_layout_quant_input.view.*
import kotlinx.android.synthetic.main.plating_finshed.*
import kotlinx.android.synthetic.main.plating_manual.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*


class editflowActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_flow)
        this.supportActionBar?.hide()  //hide title bar
        var date=""
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        val inflater = LayoutInflater.from(this@editflowActivity)
        var webapiClient = WebapiClient()
        var mfo_id=""
        var BATCH_NO=""
        var VAL=""
        var PPM=""
        var TOL=""
        var InputQuan=""
        var array = JSONArray()

        var OutputQuan=""
        var DefectQuan=""
        var StepLeft=""
        var BATCH_QTY=""
        val rootView = window.decorView.rootView
        val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(rootView)
        calendarView1.setOnDateChangeListener(OnDateChangeListener { view, year, month, dayOfMonth ->
            try {
                var nmonth = month + 1
                var z1 = ""
                var z2 = ""
                if (nmonth < 10) z1 = "0"
                if (dayOfMonth < 10) z2 = "0"
                date = "$year-$z1$nmonth-$z2$dayOfMonth"
                var url = MainActivity.ip + "PrdMgn/getOpertorInfoByDate?signDate=" + date
                var jsonString: String? = webapiClient.requestPOST(
                        "$url", JSONObject())
                val jsonStr = JSONObject(jsonString)
                array = JSONArray(jsonStr.getString("Data"))
                val mylist = ArrayList<String>()
                val mid_Array = JSONArray()
                val MachineID_Array = JSONArray()
                var lastuser = ""
                var lastMachineID = ""
                for (i in 0 until array.length()) {
                    val jsonObject = array.getJSONObject(i)
                        //if(mid_Array.indexOf(jsonObject.getString("USER_ID")) > -1)
                        if (lastuser!=jsonObject.getString("USER_ID")
                                &&jsonObject.getString("FLOW_STEP")==MainActivity.dept ){
                            if (lastuser!=jsonObject.getString("USER_ID")){
                                mid_Array.put(jsonObject.getString("USER_ID") +" | "+jsonObject.getString("EMPNAME") )
                            }
                        }

                    lastuser = jsonObject.getString("USER_ID")
                }
                if (MainActivity.ifLeader=="1"){
                    ui_Helper.set_spinner_data(user_spinner, this@editflowActivity, mid_Array, null)
                }

                if (MainActivity.ifLeader!="1"){
                    mid_Array.put(MainActivity.userBar+" | "+MainActivity.EMPNAME)

                ui_Helper.set_spinner_data(user_spinner, this@editflowActivity, mid_Array, null)
            }
                var selectuser=user_spinner.getSelectedItem().toString()
                val separated1: Array<String> = selectuser.split("|".toRegex()).toTypedArray()

                for (i in 0 until array.length()) {
                    val jsonObject = array.getJSONObject(i)
                    if (jsonObject.getString("MachineID") != lastMachineID
                            &&jsonObject.getString("FLOW_STEP")==MainActivity.dept
                            &&jsonObject.getString("USER_ID").toString().indexOf(separated1[0])>-1)
                        MachineID_Array.put(jsonObject.getString("MachineID"))
                    lastMachineID = jsonObject.getString("MachineID")
                }

               // ui_Helper.set_spinner_data(MachineID_spinner, this@editflowActivity, MachineID_Array, null)

            } catch (ex: Exception) {
                var ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(window.decorView.rootView)
                ui_Helper.send_mail(ex.toString())
            }
        })

        if (MainActivity.SIGNID_Edit==""){
            user_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    try {
                        var url = MainActivity.ip + "PrdMgn/getOpertorInfoByDate?signDate=" + date
                        var jsonString: String? = webapiClient.requestPOST("$url", JSONObject())
                        val jsonStr = JSONObject(jsonString)
                        val mylist = ArrayList<String>()
                        val mid_Array = JSONArray()
                        val MachineID_Array = JSONArray()
                        var lastuser = ""
                        var lastMachineID = ""

                        val inflater = LayoutInflater.from(this@editflowActivity)
                        val v: View = inflater.inflate(R.layout.activity_edit_flow, null)
                        var selectuser=user_spinner.getSelectedItem().toString()
                        Log.d("selectuser000000000",selectuser)
                        val separated1: Array<String> = selectuser.split(" | ".toRegex()).toTypedArray()
                        Log.d("selectuser000000000",selectuser)
                        for (i in 0 until array.length()) {
                            val jsonObject = array.getJSONObject(i)
                            if (jsonObject.getString("MachineID") != lastMachineID
                                    &&jsonObject.getString("FLOW_STEP")==MainActivity.dept
                                    &&jsonObject.getString("USER_ID").toString().indexOf(separated1[0])>-1){
                                MachineID_Array.put(jsonObject.getString("MachineID"))
                                lastMachineID = jsonObject.getString("MachineID")
                            }
                            Log.d("USER_ID111",jsonObject.getString("USER_ID").toString())
                            Log.d("USER_ID111separated1[0]",separated1[0])
                        }
                        ui_Helper.set_spinner_data(MachineID_spinner, this@editflowActivity, MachineID_Array, null)
                    }catch (ex: Exception){
                        var ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(window.decorView.rootView)
                        ui_Helper.send_mail(ex.toString())
                    }
                }
            }

            MachineID_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    try {
                        var mid = MachineID_spinner.getSelectedItem().toString()
                        var user=user_spinner.getSelectedItem().toString()
                        val separated: Array<String> = user.split(" | ".toRegex()).toTypedArray()

                        var url=MainActivity.ip +"PrdMgn/getSignsFromUserMachine?signDate="+date+
                                "&userID="+separated[0]+"&machineID="+mid

                        var jsonString:String?=webapiClient.requestPOST(
                                "$url", JSONObject())
                        val jsonStr = JSONObject(jsonString)
                        val array = JSONArray(jsonStr.getString("Data"))
                        val mylist = ArrayList<String>()
                        val SIGNID_Array = JSONArray()
                        var lastuser=""
                        var lastMachineID=""
                        for (i in 0 until array.length()) {
                            val jsonObject = array.getJSONObject(i)
                            if (jsonObject.getString("SIGNID")!=lastuser)
                                SIGNID_Array.put(jsonObject.getString("SIGNID")+" | "+jsonObject.getString("MASTER_MFO_ID"))
                            lastuser=jsonObject.getString("SIGNID")
                        }
                        ui_Helper.set_spinner_data(SIGNID_spinner, this@editflowActivity, SIGNID_Array, null)
                    }catch (ex: Exception){
                        var ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(window.decorView.rootView)
                        ui_Helper.send_mail(ex.toString())
                    }
                }
            }

            SIGNID_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {}
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    var user=SIGNID_spinner.getSelectedItem().toString()
                    val separated: Array<String> = user.split(" | ".toRegex()).toTypedArray()
                    var url=MainActivity.ip +"PrdMgn/getSignInfo?signID="+separated[0]


                    var jsonString:String?=webapiClient.requestPOST(
                            "$url", JSONObject())
                    val jsonStr = JSONObject(jsonString)
                    val data2 = JSONArray(jsonStr.getString("Data"))
                    val data = JSONObject(data2[0].toString())
                    try {
                        mfo_id=data.getString("mfo_id")
                        BATCH_NO=data.getString("BATCH_NO")
                        VAL=data.getString("VAL")
                        PPM=data.getString("PPM")
                        TOL=data.getString("TOL")
                        InputQuan=data.getString("InputQuan")
                        OutputQuan=data.getString("OutputQuan")
                        DefectQuan=data.getString("DefectQuan")
                        StepLeft=data.getString("StepLeft")
                        BATCH_QTY=data.getString("BATCH_QTY")
                        t1.setText("流程單號 " + mfo_id)
                        t2.setText("支數 " + BATCH_NO)
                        t3.setText("VAL " + VAL)
                        t4.setText("PPM " + PPM)
                        t5.setText("TOL " + TOL)
                        edit_InputQuan.setText(InputQuan)
                        edit_Output.setText(OutputQuan)
                        edit_DefectQuan.setText(DefectQuan)
                        edit_StepLeft.setText(StepLeft)
                    }catch (ex: Exception){
                        var ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(window.decorView.rootView)
                        ui_Helper.send_mail(ex.toString())
                    }
                }
            }

        }

        edit_Output.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    val input = edit_InputQuan.text.toString().toFloat()
                    val output = edit_Output.text.toString().toFloat()
                    val DefectQuan = edit_DefectQuan.text.toString().toFloat()
                    var sum = input - output - DefectQuan
                    if (MainActivity.dept == "花蓮切割") {
                        try {
                            if (MainActivity.sumOfNaiBei != "null") {
                                var neb = MainActivity.sumOfNaiBei.toFloat()
                                sum = sum - neb
                            }


                        } catch (ex: Exception) {
                            Log.d("1", ex.message)
                        }
                    }
                    if (sum <= 0)
                        sum = 0F

                    val sum1 = sum.toInt()
                    edit_StepLeft.setText(sum1.toString().replace(".0", ""))
                } catch (ex: Exception) {
                    Log.d("1", ex.message)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        edit_DefectQuan.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                try {
                    val input = edit_InputQuan.text.toString().toFloat()
                    val output = edit_Output.text.toString().toFloat()
                    val DefectQuan = edit_DefectQuan.text.toString().toFloat()
                    var sum = input - output - DefectQuan
                    if (MainActivity.dept == "花蓮切割") {
                        try {
                            if (MainActivity.sumOfNaiBei != "null") {
                                var neb = MainActivity.sumOfNaiBei.toFloat()
                                sum = sum - neb
                            }


                        } catch (ex: Exception) {
                            Log.d("1", ex.message)
                        }
                    }
                    if (sum <= 0)
                        sum = 0F

                    val sum1 = sum.toInt()
                    edit_StepLeft.setText(sum1.toString().replace(".0", ""))
                } catch (ex: Exception) {
                    Log.d("1", ex.message)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
        btn_editflow.setOnClickListener {
            try{
                var url=""
                if (MainActivity.SIGNID_Edit!=""){
                    url = MainActivity.ip + "PrdMgn/reviseSignInfo?signID=" + MainActivity.SIGNID_Edit+
                            "&InputQuan=" + edit_InputQuan.text +
                            "&outputQuan=" + edit_Output.text +
                            "&defectQuan=" + edit_DefectQuan.text +
                            "&StepLeft=" + edit_StepLeft.text +
                            "&note=abcde&userName=" + MainActivity.userBar
                }else{
                    val nurl1 = SIGNID_spinner.getSelectedItem().toString().split(" | ".toRegex()).toTypedArray()


                    url = MainActivity.ip + "PrdMgn/reviseSignInfo?signID=" + nurl1[0] +
                            "&InputQuan=" + edit_InputQuan.text +
                            "&outputQuan=" + edit_Output.text +
                            "&defectQuan=" + edit_DefectQuan.text +
                            "&StepLeft=" + edit_StepLeft.text +
                            "&note=abcde&userName=" + MainActivity.userBar
                }//InputQuan=200&StepLeft=30&outputQuan=100&defectQuan=10&note=abcde&userName=韓國瑜
                var jsonString:String?=webapiClient.requestPOST("$url", JSONObject())
                val jsonStr = JSONObject(jsonString)
                var msg="已修改工令單"+mfo_id+
                        "開始量"+edit_InputQuan.text+
                        "完成量"+edit_Output.text+
                        "不良量"+edit_DefectQuan.text+
                        "交接量"+edit_StepLeft.text+"製程"+MainActivity.dept+"人員："+MainActivity.userBar
                ui_Helper.send_mail(msg)
                ui_Helper.mesage("修改完成",this@editflowActivity)
            }catch (ex: Exception){
                val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(rootView)
                val stacktrace = StringWriter().also { ex.printStackTrace(PrintWriter(it)) }.toString().trim()
                ui_Helper.send_mail(stacktrace)
                ui_Helper.mesage("錯誤已發生，請將操作流程告知資訊部，謝謝。", this@editflowActivity)

            }
        }
        btn_back.setOnClickListener{finish()}
        edit_StepLeft.setEnabled(false)
        if (MainActivity.SIGNID_Edit!=""){
            MainActivity.date_Edit=MainActivity.date_Edit.replace("開始:", "")
            Log.d("Edit000", MainActivity.date_Edit)
            Log.d("Edit000", MainActivity.MID_Edit)
            Log.d("Edit000", MainActivity.date_Edit)
            var itemValue=MainActivity.date_Edit
            val nurl1 = itemValue.split("\\s".toRegex()).toTypedArray()
            Log.d("Edit000", nurl1[0])
            val mid_Array = JSONArray()
            MainActivity.name_Edit=MainActivity.name_Edit.replace("姓名:","")
            mid_Array.put(MainActivity.name_Edit)
            ui_Helper.set_spinner_data(user_spinner, this@editflowActivity, mid_Array, null)

            val MachineID_Array = JSONArray()
            MachineID_Array.put(MainActivity.MID_Edit)
            ui_Helper.set_spinner_data(MachineID_spinner, this@editflowActivity, MachineID_Array, null)

            val SIGNID_Array1 = JSONArray()
            Log.d("MainActivity.SIGNID0000000", MainActivity.SIGNID_Edit)
            SIGNID_Array1.put(MainActivity.SIGNID_Edit)

            //SIGNID_Array1.put(MainActivity.SIGNID_Edit)
            ui_Helper.set_spinner_data(SIGNID_spinner, this@editflowActivity, SIGNID_Array1, null)

            var url=MainActivity.ip +"PrdMgn/getSignInfo?signID="+MainActivity.SIGNID_Edit
            try {
            var jsonString:String?=webapiClient.requestPOST(
                    "$url", JSONObject())
            val jsonStr = JSONObject(jsonString)
            val data2 = JSONArray(jsonStr.getString("Data"))
            val data = JSONObject(data2[0].toString())
                mfo_id=data.getString("mfo_id")
                BATCH_NO=data.getString("BATCH_NO")
                VAL=data.getString("VAL")
                PPM=data.getString("PPM")
                TOL=data.getString("TOL")
                InputQuan=data.getString("InputQuan")
                OutputQuan=data.getString("OutputQuan")
                DefectQuan=data.getString("DefectQuan")
                StepLeft=data.getString("StepLeft")
                BATCH_QTY=data.getString("BATCH_QTY")
                t1.setText("流程單號 " + mfo_id)
                t2.setText("支數 " + BATCH_NO)
                t3.setText("VAL " + VAL)
                t4.setText("PPM " + PPM)
                t5.setText("TOL " + TOL)
                edit_InputQuan.setText(InputQuan)
                edit_Output.setText(OutputQuan)
                edit_DefectQuan.setText(DefectQuan)
                edit_StepLeft.setText(StepLeft)
                MainActivity.SIGNID_Edit=""
            }catch (ex: Exception){
                var ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(window.decorView.rootView)
                ui_Helper.send_mail(ex.toString())
            }

        }
    }
    }


