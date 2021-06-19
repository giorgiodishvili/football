package com.android.football.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.football.R
import com.android.football.databinding.SecondTeamLayoutBinding
import com.android.football.databinding.SecondTeamSubstitutionLayoutBinding
import com.android.football.entity.TeamAction
import com.android.football.enum.GoalType
import com.android.football.enum.MatchActionType
import com.android.football.extension.loadImage

class TeamTwoRecyclerAdapter(
    private val time: String,
    private val team2Action: MutableList<TeamAction?>,
    private val actionSurplus: Int = 0,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object{
        const val SUBSTITUTION = 1
        const val OTHER = 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            OTHER -> ChildRecyclerViewHolder(
                SecondTeamLayoutBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> {
                SubstitutionViewHolder(
                    SecondTeamSubstitutionLayoutBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChildRecyclerViewHolder -> holder.onBind()
            is SubstitutionViewHolder -> holder.onBind()
        }
    }

    override fun getItemCount(): Int {
        return team2Action.size + actionSurplus
    }

    override fun getItemViewType(position: Int): Int {


        return if (position >= team2Action.size || team2Action[position]!!.actionType != MatchActionType.SUBSTITUTION.id) {
            OTHER
        } else {
            SUBSTITUTION
        }
    }
    inner class ChildRecyclerViewHolder(private val binding: SecondTeamLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            if(absoluteAdapterPosition>=team2Action.size) return

            val action = team2Action[absoluteAdapterPosition]!!.action
            when (team2Action[absoluteAdapterPosition]!!.actionType) {
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
                MatchActionType.YELLOW_CARD.id -> {
                    binding.playerView.loadImage(action.player!!.playerImage)

                    binding.imageView.setImageResource(R.drawable.ic_yellow)
                    binding.text.text = "$time' Tripping"
                    binding.team1Action.text =
                        action.player.playerName
                }
                MatchActionType.RED_CARD.id -> {
                    binding.playerView.loadImage(action.player!!.playerImage)
                    binding.imageView.setImageResource(R.drawable.red_card)

                    binding.text.text = "$time' Tripping"
                    binding.team1Action.text =
                        action.player.playerName
                }
            }
        }
    }

    inner class SubstitutionViewHolder(private val binding: SecondTeamSubstitutionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            if(absoluteAdapterPosition>=team2Action.size) return

            val action = team2Action[absoluteAdapterPosition]!!.action
            when (team2Action[absoluteAdapterPosition]!!.actionType) {
                MatchActionType.SUBSTITUTION.id -> {
                    binding.playerView.loadImage(action.player1!!.playerImage)
                    binding.text.text = "$time' Substitution"
                    binding.player1Name.text =
                        action.player1.playerName
                    binding.player2Name.text =
                        action.player2!!.playerName
                }
            }
        }
    }


}

