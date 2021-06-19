package com.android.football.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.football.R
import com.android.football.databinding.FirstTeamLayoutBinding
import com.android.football.databinding.FirstTeamSubstitutionLayoutBinding
import com.android.football.entity.TeamAction
import com.android.football.enum.GoalType
import com.android.football.enum.MatchActionType
import com.android.football.extension.loadImage

class TeamOneRecyclerAdapter(
    private val time: String,
    private val team1Action: MutableList<TeamAction?>,
    private val actionSurplus: Int = 0,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object{
        const val SUBSTITUTION = 1
        const val OTHER = 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TeamTwoRecyclerAdapter.OTHER -> ChildRecyclerViewHolder(
                FirstTeamLayoutBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> {
                SubstitutionViewHolder(
                    FirstTeamSubstitutionLayoutBinding.inflate(
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

    inner class SubstitutionViewHolder(private val binding: FirstTeamSubstitutionLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            if(absoluteAdapterPosition>=team1Action.size) return

            val action = team1Action[absoluteAdapterPosition]!!.action
            when (team1Action[absoluteAdapterPosition]!!.actionType) {
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

    override fun getItemCount(): Int {
        return team1Action.size + actionSurplus
    }


    override fun getItemViewType(position: Int): Int {


        return if (position >= team1Action.size || team1Action[position]!!.actionType != MatchActionType.SUBSTITUTION.id) {
            OTHER
        } else {
            SUBSTITUTION
        }
    }
    inner class ChildRecyclerViewHolder(private val binding: FirstTeamLayoutBinding) :
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

