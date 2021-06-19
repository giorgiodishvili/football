package com.android.football.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.football.R
import com.android.football.databinding.FirstTeamLayoutBinding
import com.android.football.databinding.SecondTeamLayoutBinding
import com.android.football.entity.TeamAction
import com.android.football.enum.GoalType
import com.android.football.enum.MatchActionType
import com.android.football.extension.loadImage

class TeamOneRecyclerAdapter(
    private val time: String,
    private val team1Action: MutableList<TeamAction?>,
    private val actionSurplus: Int = 0,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var binding: FirstTeamLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = FirstTeamLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChildRecyclerViewHolder).onBind()
    }

    override fun getItemCount(): Int {
        return team1Action.size + actionSurplus
    }

    inner class ChildRecyclerViewHolder(binding: FirstTeamLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            if(absoluteAdapterPosition>=team1Action.size) return
            val action = team1Action[absoluteAdapterPosition]!!.action
            when (team1Action[absoluteAdapterPosition]!!.actionType) {
                MatchActionType.GOAL.id -> {
                    if(action.goalType == GoalType.OWN_GOAL.id){
                        binding.imageView.setImageResource(R.drawable.ic_own_goal)
                        binding.text.text = "$time' Own Goal by"
                        binding.text.setTextColor(Color.RED);

                    }else{
                        binding.text.text = "$time' Goal by"
                        binding.imageView.setImageResource(R.drawable.ic_goal)
                    }
                    binding.playerView.loadImage(action.player!!.playerImage)
                    binding.team1Action.text =
                        action.player.playerName
                }
                MatchActionType.SUBSTITUTION.id -> {
                    binding.playerView.loadImage(action.player1!!.playerImage)

                    binding.imageView.setImageResource(R.drawable.ic_yellow)
                    binding.text.text = "$time' Substitution"
                    binding.team1Action.text =
                        action.player1!!.playerName
                }
                MatchActionType.YELLOW_CARD.id -> {
                    binding.imageView.setImageResource(R.drawable.ic_yellow)
                    binding.text.text = "$time' Tripping"
                    binding.playerView.loadImage(action.player!!.playerImage)
                    binding.team1Action.text =
                        action.player!!.playerName
                }
                MatchActionType.RED_CARD.id -> {
                    binding.playerView.loadImage(action.player!!.playerImage)

                    binding.imageView.setImageResource(R.drawable.red_card)
                    binding.text.text = "$time' Tripping"
                    binding.team1Action.text =
                        action.player!!.playerName
                }
            }
        }

    }
}

