package com.android.football.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.football.databinding.GameRecyclerItemBinding
import com.android.football.entity.Game
import com.android.football.entity.Summary
import com.android.football.entity.TeamAction

class GameRecyclerViewAdapter(
    private var game: Game,
) :
    RecyclerView.Adapter<GameRecyclerViewAdapter.ChildRecyclerViewHolder>() {
    private lateinit var binding: GameRecyclerItemBinding
    private val viewPool = RecyclerView.RecycledViewPool()

    inner class ChildRecyclerViewHolder(binding: GameRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val team1Recycler: RecyclerView = binding.team1Recycler
        private val team2Recycler: RecyclerView = binding.team2Recycler
        private lateinit var summary: Summary

        private lateinit var team1Action: MutableList<TeamAction?>
        private lateinit var team2Action: MutableList<TeamAction?>
        fun onBind() {
            team1Action= mutableListOf()
            team2Action= mutableListOf()

            summary = game.match.matchSummary.summaries[absoluteAdapterPosition]
            populateTeamActions()
            val team1recyclerLayoutManager = LinearLayoutManager(
                team1Recycler.context, LinearLayoutManager.HORIZONTAL, false
            )
            team1recyclerLayoutManager.initialPrefetchItemCount = 4
            team1Recycler.apply {
                layoutManager = team1recyclerLayoutManager
                adapter = TeamOneRecyclerAdapter(summary.actionTime,team1Action)
                setRecycledViewPool(viewPool)
            }

            val team2recyclerLayoutManager = LinearLayoutManager(
                team2Recycler.context, LinearLayoutManager.HORIZONTAL, false
            )
            team2recyclerLayoutManager.initialPrefetchItemCount = 4
            team2Recycler.apply {
                layoutManager = team2recyclerLayoutManager
                adapter = TeamTwoRecyclerAdapter(summary.actionTime,team2Action)
                setRecycledViewPool(viewPool)
            }





        }

        private fun populateTeamActions() {
                if (summary.team1Action != null) {
                    team1Action.addAll(summary.team1Action!!)
                }
                if(summary.team2Action != null){
                    team2Action.addAll(summary.team2Action!!)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildRecyclerViewHolder {
        binding = GameRecyclerItemBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ChildRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildRecyclerViewHolder, position: Int) {
        holder.onBind()
    }

    override fun getItemCount(): Int {
        return game.match.matchSummary.summaries.size
    }
}