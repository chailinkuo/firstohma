package com.example.firstohm_produce_kotlin

import android.app.AlertDialog
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import co.ubunifu.kotlinhttpsample.Lib.WebapiClient
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.activity_more_option_layout.*
import kotlinx.android.synthetic.main.activity_more_option_layout.btn_cow
import org.json.JSONObject
import java.io.PrintWriter
import java.io.StringWriter

class more_option: AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_more_option_layout)
            this.supportActionBar?.hide()
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            val rootView = window.decorView.rootView
            val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(rootView)
            if (MainActivity.userBar==""){
                testDB.visibility= View.INVISIBLE
                imageView4_testDB.visibility= View.GONE
                btn_line.visibility= View.INVISIBLE
                imageView6_btn_line.visibility= View.GONE
                button4.visibility= View.INVISIBLE
                button4_imageView.visibility= View.GONE
                btn_edit.visibility= View.INVISIBLE
                btn_edit_imageView.visibility= View.GONE
                btn_cow.visibility= View.INVISIBLE
                btn_cow_imageView.visibility= View.GONE
                Inquire.visibility= View.INVISIBLE
                Inquire_imageView.visibility= View.GONE
                machine.visibility= View.INVISIBLE
                machine_imageView.visibility= View.GONE
                Inquire20.visibility= View.INVISIBLE
                Inquire20_imageView.visibility= View.GONE
                Inquire01.visibility= View.INVISIBLE
                Inquire01_imageView83.visibility= View.GONE
                Inquire1.visibility= View.INVISIBLE
                Inquire1_imageView.visibility= View.GONE
            }else{

                testDB.visibility= View.VISIBLE
                imageView4_testDB.visibility= View.VISIBLE
                btn_line.visibility= View.VISIBLE
                imageView6_btn_line.visibility= View.VISIBLE
                button4.visibility= View.VISIBLE
                button4_imageView.visibility= View.VISIBLE
                btn_edit.visibility= View.VISIBLE
                btn_edit_imageView.visibility= View.VISIBLE
                btn_cow.visibility= View.VISIBLE
                btn_cow_imageView.visibility= View.VISIBLE
                Inquire.visibility= View.VISIBLE
                Inquire_imageView.visibility= View.VISIBLE
                machine.visibility= View.VISIBLE
                machine_imageView.visibility= View.VISIBLE
                Inquire20.visibility= View.VISIBLE
                Inquire20_imageView.visibility= View.VISIBLE
                Inquire01.visibility= View.VISIBLE
                Inquire01_imageView83.visibility= View.VISIBLE
                Inquire1.visibility= View.VISIBLE
                Inquire1_imageView.visibility= View.VISIBLE
                //MainActivity.more_potion_flag="2"//option
                //MainActivity.more_potion_flag="1"//search
                if (MainActivity.more_potion_flag=="1") {
                    lera_network.visibility= View.GONE
                    lera_setting.visibility= View.GONE
                    lera_search.visibility= View.VISIBLE
                }else{
                    lera_network.visibility= View.VISIBLE
                    lera_setting.visibility= View.VISIBLE
                    lera_search.visibility= View.GONE
                }
            }
            btn_retun_imageView.setOnClickListener {
                finish()
            }
            btn_retun.setOnClickListener {
                finish()
            }
            ///////////////////////////////
            btn_edit_imageView.setOnClickListener{
                btn_edit.performClick()
            }
            btn_edit.setOnClickListener {
                startActivity(Intent(this@more_option, editflowActivity::class.java))
            }
            btn_cow_imageView.setOnClickListener{
                btn_cow.performClick()
            }
            btn_cow.setOnClickListener {
                val builder = androidx.appcompat.app.AlertDialog.Builder(this@more_option)
                builder.setMessage("合作")
                builder.setCancelable(false)
                builder.setPositiveButton("確認") { dialog, id ->
                    MainActivity.cow ="1"
                    val scanIntegrator = IntentIntegrator(this)
                    scanIntegrator.initiateScan()
                }
                builder.setNegativeButton("取消") { dialog, which -> }
                builder.show()
            }
            button4_imageView.setOnClickListener{
                button4.performClick()
            }
            button4.setOnClickListener {
                try{
                    if(MainActivity.mStatus =="0"){
                        MainActivity.mStatus ="2"
                    }else{
                        MainActivity.mStatus ="0"
                    }
                    if(MainActivity.machineBar ==""){
                        val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(rootView)
                        ui_Helper.mesage("未選擇機台", this@more_option)
                    }else {
                        var webapiClient = WebapiClient()
                        var url = MainActivity.ip +
                                "PrdMgn/ScanOperate?command=10&UID=" + MainActivity.userBar + "&flowBar=" + MainActivity.flowbar +
                                "&DEPT=" + MainActivity.dept + "&MID=" + MainActivity.machineBar + "&mStatus=" + MainActivity.mStatus
                        var jsonString: String? = webapiClient.requestPOST(url, JSONObject())
                        val jsonStr = JSONObject(jsonString)
                        val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(rootView)
                        if (MainActivity.mStatus == "2") {
                            ui_Helper.mesage("已變更${MainActivity.machineBar} 機台為故障中", this@more_option)
                        } else {
                            ui_Helper.mesage("已變更${MainActivity.machineBar} 機台為待機中", this@more_option)
                        }
                    }
                }catch (ex: Exception){
                    ui_Helper.mesage("連線失敗請重試", this@more_option);
                }
            }
            /////////////////////////////////
            testwifi_imageView.setOnClickListener{
                testwifi.performClick()
            }
            testwifi.setOnClickListener {
                val rootView = window.decorView.rootView
                val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(rootView)
                try {
                    var SubflowInfo = CustomLayoutSubflowInfo(this@more_option, null)
                    var webapiClient = WebapiClient()
                    var url = "http://172.168.1.151:1119/firstohmWebApi/ProdFlow/TestConnection"
                    var jsonString:String?=webapiClient.requestPOST(url, JSONObject())
                    val jsonStr = JSONObject(jsonString)
                    MainActivity.flow_message =jsonStr.getString("Message")
                    if (MainActivity.flow_message.equals("連線成功")){
                        ui_Helper.mesage("連線成功", this@more_option);
                    }else{
                        ui_Helper.mesage("連線失敗請重試", this@more_option);
                    }
                }catch (ex: Exception){
                    ui_Helper.mesage("連線失敗請重試", this@more_option);
                }
            }
            imageView6_btn_line.setOnClickListener{
                btn_line.performClick()
            }
            btn_line.setOnClickListener {
                Dialogline().show(supportFragmentManager, "MyCustomFragment")
            }
            restartwifi_imageView.setOnClickListener{
                restartwifi.performClick()
            }
            restartwifi.setOnClickListener {
                wifi_test()
            }
            imageView4_testDB.setOnClickListener{
                testDB.performClick()
            }
            testDB.setOnClickListener {
                try {
                    var webapiClient = WebapiClient()
                    var url=MainActivity.ip+"/PrdMgn/UnLockDB"
                    var jsonString:String?=webapiClient.requestPOST(url, JSONObject())
                    val jsonStr = JSONObject(jsonString)
                    ui_Helper.mesage(jsonStr.getString("Message"), this@more_option)
                    Log.d("Message111", jsonStr.getString("Message"))
                }catch (ex: Exception){
                    val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(rootView)
                    ui_Helper.mesage("錯誤已發生，請將操作流程告知資訊部，謝謝。", this@more_option)
                    val stacktrace = StringWriter().also { ex.printStackTrace(PrintWriter(it)) }.toString().trim()
                    //ui_Helper.send_mail(stacktrace)
                }
            }
            ////////////////////////////////////
            Inquire.setOnClickListener{
                if(MainActivity.SIGNID==""||MainActivity.SIGNID=="null"){
                    val builder = AlertDialog.Builder(this@more_option)
                    builder.setMessage("未掃描流程單")
                            .setPositiveButton("是") { dialog, which -> // TODO Auto-generated method stub

                            }
                    val about_dialog = builder.create()
                    about_dialog.show()
                }else{
                    MainActivity.seach="5"
                    startActivity(Intent(this@more_option, InquireviewsActivity::class.java))
                    finish()
                }
            }
            Inquire_imageView.setOnClickListener{
                Inquire.performClick()
            }
            machine_imageView.setOnClickListener{
                machine.performClick()
            }
            machine.setOnClickListener {
                MainActivity.seach="2"
                startActivity(Intent(this@more_option, InquireviewsActivity::class.java))
                finish()
            }
            Inquire20_imageView.setOnClickListener{
                Inquire20.performClick()
            }
            Inquire20.setOnClickListener {
                MainActivity.seach="3"
                startActivity(Intent(this@more_option, InquireviewsActivity::class.java))
                finish()
            }
            Inquire01_imageView83.setOnClickListener{
                Inquire01.performClick()
            }
            Inquire01.setOnClickListener {
                MainActivity.seach="4"
                startActivity(Intent(this@more_option, InquireviewsActivity::class.java))
                finish()
            }
            Inquire1_imageView.setOnClickListener{
                Inquire1.performClick()
            }
            Inquire1.setOnClickListener {
                MainActivity.seach="1"
                startActivity(Intent(this@more_option, InquireviewsActivity::class.java))
                finish()
            }
        }catch (ex: Exception){
            val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(window.decorView.rootView)
            ui_Helper.mesage("錯誤已發生，請將操作流程告知資訊部，謝謝。", this@more_option)
            val stacktrace = StringWriter().also { ex.printStackTrace(PrintWriter(it)) }.toString().trim()
            ui_Helper.send_mail(stacktrace)}
    }

    private fun wifi_test() {
        try {
            val wifiManager = applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
            wifiManager.setWifiEnabled(false);
            Thread.sleep(1000);
            wifiManager.setWifiEnabled(true);
            var count = 0
            while (!wifiManager.isWifiEnabled) {
                if (count >= 10) {
                    Log.i("TAG", "Took too long to enable wi-fi, quitting")
                    return
                }
                Log.i("TAG", "Still waiting for wi-fi to enable...")
                try {
                    Thread.sleep(1000L)
                } catch (ie: InterruptedException) {
                    // continue
                }
                count++
            }
            wifi_test2()
        }catch (ex: Exception){
            Log.d("1", ex.message)
        }
    }
    private fun wifi_test2() {
        Thread.sleep(5000);
        val rootView = window.decorView.rootView
        val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(rootView)
        try {
            var SubflowInfo = CustomLayoutSubflowInfo(this@more_option, null)
            var webapiClient = WebapiClient()
            var url = "http://172.168.1.33:1111/firstohmWebApi/ProdFlow/TestConnection"
            var jsonString:String?=webapiClient.requestPOST(url, JSONObject())
            val jsonStr = JSONObject(jsonString)
            MainActivity.flow_message =jsonStr.getString("Message")
            if (MainActivity.flow_message.equals("連線成功")){
                ui_Helper.mesage("連線成功", this@more_option);
            }else{
                ui_Helper.mesage("連線失敗請重試", this@more_option);
            }
        }catch (ex: Exception){
            ui_Helper.mesage("連線失敗請重試", this@more_option);
        }
    }
}
