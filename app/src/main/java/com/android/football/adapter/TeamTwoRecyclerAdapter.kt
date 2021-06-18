package com.android.football.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.football.R
import com.android.football.databinding.ChildRvItemBinding
import com.android.football.entity.TeamAction
import com.android.football.enum.GoalType
import com.android.football.enum.MatchActionType
import com.android.football.extension.changeVisibility
import com.android.football.extension.loadImage

class TeamTwoRecyclerAdapter(
    private val time: String,
    private val team2Action: MutableList<TeamAction?>,
    private val actionSurplus: Int = 0,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var binding: ChildRvItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ChildRvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ChildRecyclerViewHolder).onBind()
    }

    override fun getItemCount(): Int {
        return team2Action.size + actionSurplus
    }

    inner class ChildRecyclerViewHolder(binding: ChildRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            if(absoluteAdapterPosition>=team2Action.size) return

            val action = team2Action[absoluteAdapterPosition]!!.action
            when (team2Action[absoluteAdapterPosition]!!.actionType) {
                MatchActionType.GOAL.id -> {
                    if(action.goalType == GoalType.OWN_GOAL.id){
                        binding.imageView.setImageResource(R.mipmap.own_goal)
                        binding.text.text = "$time Own Goal by"
                    }else{
                        binding.text.text = "$time Goal by"
                        binding.imageView.setImageResource(R.mipmap.goal)
                    }
                    binding.playerView.loadImage(action.player!!.playerImage)
                    binding.team1Action.text =
                        action.player!!.playerName
                }
                MatchActionType.SUBSTITUTION.id -> {
                    binding.imageView.setImageResource(R.mipmap.yellow)
                    binding.playerView.loadImage(action.player1!!.playerImage)
                    binding.text.text = "$time Substitution"
                    binding.team1Action.text =
                        action.player1!!.playerName
                }
                MatchActionType.YELLOW_CARD.id -> {
                    binding.playerView.loadImage(action.player!!.playerImage)

                    binding.imageView.setImageResource(R.mipmap.yellow)
                    binding.text.text = "$time Tripping"
                    binding.team1Action.text =
                        action.player!!.playerName
                }
                MatchActionType.RED_CARD.id -> {
                    binding.playerView.loadImage(action.player!!.playerImage)
                    binding.imageView.setImageResource(R.mipmap.yellow)
                    binding.text.text = "$time Tripping"
                    binding.team1Action.text =
                        action.player!!.playerName
                }
            }
        }
    }
}

