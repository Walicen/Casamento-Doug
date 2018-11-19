package br.com.walicendalazuana.mannudoug.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.walicendalazuana.mannudoug.Model.Presente
import br.com.walicendalazuana.mannudoug.R
import kotlinx.android.synthetic.main.item_present.view.*
import java.util.*

class PresenteAdapter(items: List<Presente>) : RecyclerView.Adapter<PresenteAdapter.OriginalViewHolder>() {

    private val items = items
    private var mOnItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(view: View, obj: Presente, position: Int)
    }

    fun setOnItemClickListener(mItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mItemClickListener
    }


    class OriginalViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val title = v.title
        val brief = v.brief
        val image_bg = v.image_bg
        val lyt_parent = v.lyt_parent

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OriginalViewHolder {
       // val vh: RecyclerView.ViewHolder
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_present, parent, false)
        //vh = OriginalViewHolder(v)
        return OriginalViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: OriginalViewHolder, position: Int) {
        val view  = holder.itemView

        val p = items[position]

        with(view){
            title.text = p.title
            brief.text = p.brief
            image_bg.setImageResource(p.image_bg)
            lyt_parent.setOnClickListener { view ->
                if (mOnItemClickListener != null) {
                    mOnItemClickListener!!.onItemClick(view, items[position], position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

}