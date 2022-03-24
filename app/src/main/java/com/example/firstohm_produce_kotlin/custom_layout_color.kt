package com.example.firstohm_produce_kotlin


import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.custom_layout_quant_color.view.*
import java.io.PrintWriter
import java.io.StringWriter

class custom_layout_color(
        context: Context,
        attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {
    init {
        inflate(context, R.layout.custom_layout_quant_color, this)
        val customAttributesStyle = context.obtainStyledAttributes(
                attrs,
                R.styleable.custom_layout_quant_color,
                0,
                0
        )
    }
    fun draw_color(color_str: String,view: View){
        try {
            Log.d("colo",color_str)
            val color_map = HashMap<String, String>()
            color_map["白"]="#ffffff"
            color_map["7921 藍色B"]="#73C3AA"
            color_map["蘋果綠"]="#9BFFAA"
            color_map["磚紅"]="#FFBEBE"
            color_map["MO 藍色"]="#64A6CE"
            color_map["紫色"]="#CC99CC"
            color_map["MP106 粉紅"]="#FACCBA"
            color_map["金屬 墨綠"]="#73AD8F"
            color_map["白"]="#ffffff"
            color_map["7921 藍色B"]="#73C3AA"
            color_map["蘋果綠"]="#9BFFAA"
            color_map["磚紅"]="#FFBEBE"
            color_map["MO 藍色"]="#64A6CE"
            color_map["MP106 粉紅"]="#FF99CC"
            color_map["MP106"]="#FF99CC"
            color_map["2070"]="#00B0F0"
            color_map["2791"]="#00FFFF"
            color_map["2791漆"]="#0DE1CD"
            color_map["7921漆"]="#73C3AA"
            color_map["Z0154"]="#FFCCFF"
            color_map["Z0360"]="#F2B300"
            color_map["紫色"]="#CC99CC"
            color_map["紫"]="#CC99CC"
            color_map["金屬 墨綠"]="#73AD8F"
            color_map["磚紅"]="#FFBEBE"
            color_map["綠色"]="#4B854B"
            color_map["碳膜 乳黃"]="#E8D296"
            color_map["CM02 碳膜乳黃"]="#FFE0A0"
            color_map["粉紅A"]="#FFCCAF"
            color_map["MM02 藍色A"]="#1E82C8"
            color_map["MP106 粉紅B"]="#FACCBA"
            color_map["紅"]="#FF0000"
            color_map["橙"]="#FF6600"
            color_map["黃"]="#FFFF00"
            color_map["綠"]="#009900"
            color_map["棕"]="#F5F5DC"
            color_map["藍"]="#0000FF"
            color_map["灰"]="#999999"
            color_map["白"]="#FFFFFF"
            color_map["金"]="#FFCC00"
            color_map["銀"]="#CCCCCC"
            color_map["粉紅B"]="#FACCBA"
            color_map["藍色A"]="#1E82C8"
            color_map["7921 藍色"]="#73C3AA"
            color_map["粉紅A"]="#FFCCAF"
            color_map["碳膜乳黃"]="#FFFF00"
            color_map["蘋果綠不燃性漆"]="#66FF66"
            color_map["綠色不燃性漆"]="#375623"
            color_map["藍色不燃性漆"]="#3C8C4D"
            color_map["紫色不燃性漆"]="#E3A5E3"
            color_map["磚紅不燃性漆"]="#FF6600"
            color_map["金屬塗料"]="#73AD8F"
            color_map["黑"]="#000000"
            color_map["GR-2"]="#4B854B"
            val color_image = arrayOfNulls<ImageView>(70)
            val Color_text = arrayOfNulls<TextView>(60)
            if((MainActivity.color_direction=="rigth") || (MainActivity.color_direction=="") ){
                view.textView26.setText("→     →     →")
            }else{
                view.textView26.setText("←     ←     ←")
            }
            color_image[0] = view.color1
            color_image[1] = view.color2
            color_image[2] = view.color3
            color_image[3] = view.color4
            color_image[4] = view.color5
            color_image[5] = view.color6
            color_image[6] = view.color7
            Color_text[0]=view.color_text1
            Color_text[1]=view.color_text2
            Color_text[2]=view.color_text3
            Color_text[3]=view.color_text4
            Color_text[4]=view.color_text5
            Color_text[5]=view.color_text6
            Color_text[6]=view.color_text7
            if (color_str!="--"){
                Handler(Looper.getMainLooper()).postDelayed({
                    color_image[0]!!.setBackgroundColor(Color.parseColor("#ffffff"))
                    color_image[1]!!.setBackgroundColor(Color.parseColor("#ffffff"))
                    color_image[2]!!.setBackgroundColor(Color.parseColor("#ffffff"))
                    color_image[3]!!.setBackgroundColor(Color.parseColor("#ffffff"))
                    color_image[4]!!.setBackgroundColor(Color.parseColor("#ffffff"))
                    color_image[5]!!.setBackgroundColor(Color.parseColor("#ffffff"))
                    color_image[6]!!.setBackgroundColor(Color.parseColor("#ffffff"))
                    color_image[0]!!.setColorFilter(Color.parseColor("#ffffff"))
                    color_image[1]!!.setColorFilter(Color.parseColor("#ffffff"))
                    color_image[2]!!.setColorFilter(Color.parseColor("#ffffff"))
                    color_image[3]!!.setColorFilter(Color.parseColor("#ffffff"))
                    color_image[4]!!.setColorFilter(Color.parseColor("#ffffff"))
                    color_image[5]!!.setColorFilter(Color.parseColor("#ffffff"))
                    color_image[6]!!.setColorFilter(Color.parseColor("#ffffff"))
                    color_image[0]!!.visibility= INVISIBLE
                    color_image[1]!!.visibility= INVISIBLE
                    color_image[2]!!.visibility= INVISIBLE
                    color_image[3]!!.visibility= INVISIBLE
                    color_image[4]!!.visibility= INVISIBLE
                    color_image[5]!!.visibility= INVISIBLE
                    color_image[6]!!.visibility= INVISIBLE

                    view.color_text7.setText("")
                    view.color_text6.setText("")
                    view.color_text5.setText("")
                    view.color_text4.setText("")
                    view.color_text3.setText("")
                    view.color_text2.setText("")
                    view.color_text1.setText("")
                    //處理少量資訊或UI
                    val parts = color_str.split("-".toRegex()).toTypedArray()
                    val i = parts[0].length
                    var first = parts[0]
                    if((MainActivity.color_direction=="rigth") || (MainActivity.color_direction=="") ){

                    }else{
                        //色碼向左反轉文字Array回填
                        val s = first
                        val sb = StringBuffer(s)
                        first=sb.reverse().toString()
                    }
                    val reverse = StringBuffer(first).reverse().toString()
                    val content = first.toCharArray()
                    Log.d("colocontent.size",content.size.toString())

                    for(i in 0..content.size-1){
                        try {
                            Log.d("colo",content[i].toString())
                            val colo=content[i].toString()
                            val co=color_map.get(colo).toString()
                            if (co!="null" || co!="-" ){
                                color_image[i]!!.visibility= VISIBLE
                                //黑框
                                color_image[i]!!.setBackgroundColor(Color.parseColor("#000000"))
                                Log.d("colo",co)
                                //內色
                                color_image[i]!!.setColorFilter(Color.parseColor(co))
                                Color_text[i]!!.setText(colo)

                            }

                        } catch (ex: Exception) {

                        }
                    }
                    try {
                        view.color_text7.visibility= GONE
                        view.color7.visibility= GONE
                    if (parts[3]!==null){
                        try {
                            Handler(Looper.getMainLooper()).postDelayed({
                                try {
                                color_image[6] = view.color7

                                Color_text[6]=view.color_text7

                                Log.d("colo3",parts[3])
                                color_image[6]!!.visibility= VISIBLE
                                Color_text[6]!!.visibility= VISIBLE
                                val co=color_map.get(parts[3]).toString()
                                    //.color_image[6]!!.setBackgroundColor(Color.parseColor(co))
                                color_image[i]!!.setBackgroundColor(Color.parseColor("#000000"))
                                //srcCompat
                                Log.d("colo",parts[3])
                                color_image[6]!!.setColorFilter(Color.parseColor(co))
                                Color_text[6]!!.setText(parts[3])
                                //2063307

                            } catch (ex: Exception) {

                            }
                            }, 5)
                        } catch (ex: Exception) {

                        }
                    }
                    } catch (ex: Exception) {

                    }
                    try {
                        view.back_color.setText("底色:"+parts[2].toString())
                    } catch (ex: Exception) {

                    }
                    //view.progressBar.visibility=View.GONE
                }, 0)
            }
        } catch (ex: Exception) {
            val ui_Helper = co.ubunifu.kotlinhttpsample.Lib.UI_Helper(rootView)
            ui_Helper.mesage("錯誤已發生，請將操作流程告知資訊部，謝謝。", context)
            val stacktrace = StringWriter().also { ex.printStackTrace(PrintWriter(it)) }.toString().trim()
            ui_Helper.send_mail(stacktrace)
        }
        ////////////////////////////////
        //COLOR_NUM="紅紅黑-黑-MP106 粉紅";

    }
}