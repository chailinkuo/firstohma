package co.ubunifu.kotlinhttpsample.Lib

import android.content.Context
import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import com.example.firstohm_produce_kotlin.R
import kotlinx.android.synthetic.main.custom_layout_quant_input.view.*
import java.net.URL

class WebapiClient_test(var context: Context) : AsyncTask<URL, Int, String>(){
    val GET : String = "GET"
    val POST : String = "POST"
    public var json_string=null
    override fun onPreExecute() {
        super.onPreExecute()
        val toast = Toast.makeText(context,
                "00000000000Message", Toast.LENGTH_LONG)
        toast.show()
    }
    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }
    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

    }

     override public fun doInBackground(vararg params: URL?): String? {
         Handler(Looper.getMainLooper()).postDelayed({
             val inflater = LayoutInflater.from(context)
             val v: View = inflater.inflate(R.layout.custom_layout_quant_input, null)
             v.progressBar.visibility = View.VISIBLE
             v.progressBar.invalidate()
             Log.d("20220310", "setvisibility")
             val toast = Toast.makeText(context,
                     "00000000000Message", Toast.LENGTH_LONG)
             toast.show()
         }, 1000)


         return null
     }

 }