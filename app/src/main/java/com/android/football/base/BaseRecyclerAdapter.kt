package com.android.football.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseRecyclerAdapter<VB : ViewBinding>(private val inflate: Inflate<VB>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected var dataList: List<*> = listOf(null)

    private var _binding: VB? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        _binding = inflate.invoke(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return MyViewHolder(_binding!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindViewHold(position, dataList[position])
    }

    abstract fun getBinding(binding: ViewBinding)

    override fun getItemCount(): Int {
        return if (dataList.isEmpty()) 0 else {
            dataList.size
        }
    }

    abstract fun onBindViewHold(position: Int, itemView: Any?)

    inner class MyViewHolder(bnd: VB) : RecyclerView.ViewHolder(bnd.root) {
        init {
            getBinding(bnd)
        }
    }
}
