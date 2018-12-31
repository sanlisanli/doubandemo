package me.mikasa.doubandemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.mikasa.doubandemo.R

/**
 * Created by mikasa on 2018/12/26.
 */
abstract class BaseRvAdapter<Data>(
        var mData: ArrayList<Data> = ArrayList(), var mListener:OnItemClickListener<Data>?=null):
        RecyclerView.Adapter<BaseRvAdapter.ViewHolder<Data>>(),View.OnClickListener{
    abstract fun getItemLayoutResId():Int
    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Data> {
        val inflater = LayoutInflater.from(parent?.context)

        val root: View = inflater.inflate(getItemLayoutResId(), parent, false)
        val holder: ViewHolder<Data> = onCreateViewHolder(root, viewType)

        root.setTag(R.id.tag_recycler_holder, holder)
        root.setOnClickListener(this)

        return holder
    }

    protected abstract fun onCreateViewHolder(root: View, viewType: Int): ViewHolder<Data>

    override fun onBindViewHolder(holder: ViewHolder<Data>, position: Int) {
        val data=mData.get(position)
        holder?.bindData(data)
    }

    override fun onClick(v: View?) {
        val viewHolder: ViewHolder<Data> = v?.getTag(R.id.tag_recycler_holder) as ViewHolder<Data>
        mListener?.let {
            val pos = viewHolder.adapterPosition
            it.onItemClick(viewHolder, mData.get(pos))
        }
    }
    fun refreshData(dataList: ArrayList<Data>?) {
        mData.clear()

        if (dataList != null) {
            mData.addAll(dataList)
            notifyDataSetChanged()
        }
    }
    fun appendData(dataList: ArrayList<Data>?){
        if (dataList != null) {
            mData.addAll(dataList)
            notifyDataSetChanged()
        }
    }

    abstract class ViewHolder<Data>(itemView:View):RecyclerView.ViewHolder(itemView){
        abstract fun bindData(data:Data)
    }
    fun setOnItemClickListener(listener:OnItemClickListener<Data>){
        this.mListener=listener
    }
    interface OnItemClickListener<Data>{
        fun onItemClick(holder:ViewHolder<Data>,data:Data)
    }
}