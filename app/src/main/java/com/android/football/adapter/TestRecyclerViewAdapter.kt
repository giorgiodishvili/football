package com.android.football.adapter

import androidx.viewbinding.ViewBinding
import com.android.football.base.BaseRecyclerAdapter
import com.android.football.databinding.FirstTeamSubstitutionLayoutBinding

class TestRecyclerViewAdapter : BaseRecyclerAdapter<FirstTeamSubstitutionLayoutBinding>(FirstTeamSubstitutionLayoutBinding::inflate) {


    override fun onBindViewHold(position: Int, itemView: Any?) {
        TODO("Not yet implemented")
    }

    override fun getBinding(binding: ViewBinding) {

    }
}