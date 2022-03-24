package com.example.firstohm_produce_kotlin
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_inquire.*
import kotlinx.android.synthetic.main.activity_inquire_views.*
import kotlinx.android.synthetic.main.activity_plating.*
import kotlinx.android.synthetic.main.activity_plating_callback.*
import kotlinx.android.synthetic.main.activity_plating_exclude.*
import kotlinx.android.synthetic.main.custom_layout_quant_input.view.*
import java.util.*

class InquireActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inquire)
        this.supportActionBar?.hide()  //hide title bar
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        val inflater = LayoutInflater.from(this@InquireActivity)
        try {
            btn_backhome.setOnClickListener {
                finish()
            }
            prdStepRecords.setOnClickListener {
                if(MainActivity.SIGNID==""||MainActivity.SIGNID=="null"){
                    val builder = AlertDialog.Builder(this@InquireActivity)
                    builder.setMessage("未掃描流程單")
                            .setPositiveButton("是") { dialog, which -> // TODO Auto-generated method stub

                            }
                    val about_dialog = builder.create()
                    about_dialog.show()
                }else{
                    MainActivity.seach="5"
                    startActivity(Intent(this@InquireActivity, InquireviewsActivity::class.java))
                    finish()
                }
            }
            usedingmid.setOnClickListener {
                MainActivity.seach="2"
                startActivity(Intent(this@InquireActivity, InquireviewsActivity::class.java))
                finish()
            }
            views.setOnClickListener {
                MainActivity.seach="3"
                startActivity(Intent(this@InquireActivity, InquireviewsActivity::class.java))
                finish()
            }
            flow.setOnClickListener {
                MainActivity.seach="4"
                startActivity(Intent(this@InquireActivity, InquireviewsActivity::class.java))
                finish()
            }
            myPodRecords.setOnClickListener {
                MainActivity.seach="1"
                startActivity(Intent(this@InquireActivity, InquireviewsActivity::class.java))
                finish()
            }
        } catch (ex: Exception) {

        }

}
}