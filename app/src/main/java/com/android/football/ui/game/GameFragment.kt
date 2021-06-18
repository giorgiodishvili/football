package com.android.football.ui.game

import android.util.Log.i
import android.view.LayoutInflater
import android.view.ViewGroup
import com.android.football.base.BaseFragment
import com.android.football.databinding.GameFragmentBinding
import com.android.football.enum.MatchActionType

class GameFragment : BaseFragment<GameFragmentBinding, GameViewModel>(GameFragmentBinding::inflate,
    GameViewModel::class.java) {
    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        observer()
        viewModel.getGame()
    }

    private fun observer() {
        viewModel._data.observe(viewLifecycleOwner, {
            it.match.matchSummary.summaries.forEach{
//                    k ->
//                k.team1Action.forEach{
//                    t-> if(t.actionType==MatchActionType.GOAL.id){
//                        i("CLICKDD", k.team1Action.toString())
//                     }
//                }

            }
        })
    }
}