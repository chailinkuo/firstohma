package com.example.firstohm_produce_kotlin




import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import co.ubunifu.kotlinhttpsample.Lib.ListViewModelAdapter
import java.util.*


public class ListViewModelAdapterflowspile1_part(context: Context, listModelArrayList: ArrayList<ListViewModelAdapterflowspile100>)
    : ListViewModelAdapter<ListViewModelAdapterflowspile100>(
        context, listModelArrayList
) {
    private var selectedEditTextPosition = -1

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view: View?
        val vh: ViewHolderWh12

        // TODO Auto-generated method stub

        val ArrayPosition = position;
        if (convertView == null) {
            val layoutInflater = LayoutInflater.from(context)
            view = layoutInflater.inflate(R.layout.dialog_part_list_views, parent, false)
            vh = ViewHolderWh12(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolderWh12
        }
        convertView?.setTag(R.layout.dialog_partflow_list_viiews, position); // 应该在这里让convertView绑定position
        vh.SUBFLOWID.text = listModelArrayList[position].SUBFLOWID
        vh.BATCH_QTY.text = listModelArrayList[position].BATCH_QTY
        vh.DONE_QTY.text = listModelArrayList[position].DONE_QTY
        vh.parentID.text = listModelArrayList[position].parentID
        vh.splitStep.text = listModelArrayList[position].splitStep
        vh.splitStatus.text = listModelArrayList[position].splitStatus
        vh.short.text = listModelArrayList[position].short
        return view
    }

}
//ViewList 裡面 Row 的定義
class ViewHolderWh12(view: View?) {
    val SUBFLOWID: TextView = view?.findViewById<TextView>(R.id.SUBFLOWID) as TextView
    val BATCH_QTY: TextView = view?.findViewById<TextView>(R.id.BATCH_QTY) as TextView
    val DONE_QTY: TextView = view?.findViewById<TextView>(R.id.DONE_QTY) as TextView
    val parentID: TextView = view?.findViewById<TextView>(R.id.parentID) as TextView
    val splitStep: TextView = view?.findViewById<TextView>(R.id.splitStep) as TextView
    val splitStatus: TextView = view?.findViewById<TextView>(R.id.splitStatus) as TextView
    val short: TextView = view?.findViewById<TextView>(R.id.short1) as TextView

}
data class ListViewModelAdapterflowspile100(
        var id: Int, var SUBFLOWID: String, var BATCH_QTY: String, var DONE_QTY: String, var parentID: String
        , var splitStep: String, var splitStatus: String, var short: String
){

}


