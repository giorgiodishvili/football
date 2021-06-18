package com.android.football.ui.game

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.football.adapter.GameRecyclerViewAdapter
import com.android.football.base.BaseFragment
import com.android.football.databinding.GameFragmentBinding
import com.android.football.entity.Game
import com.android.football.extension.convertLongToTime
import com.android.football.extension.loadImage

class GameFragment : BaseFragment<GameFragmentBinding, GameViewModel>(
    GameFragmentBinding::inflate,
    GameViewModel::class.java
) {
    private lateinit var adapter: GameRecyclerViewAdapter
    override fun start(inflater: LayoutInflater, container: ViewGroup?) {
        observer()
        viewModel.getGame()
    }

    private fun observer() {
        viewModel._data.observe(viewLifecycleOwner, {
            setUpFragment(it)
            initRecycler(it)
        })
    }

    private fun initRecycler(it: Game) {
        adapter = GameRecyclerViewAdapter(it)
        binding.ParentRecyclerView.layoutManager =
            LinearLayoutManager(context)
        binding.ParentRecyclerView.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun setUpFragment(it: Game) {
        binding.tvDateAndStadium.text =
            it.match.matchDate.convertLongToTime() + " " + it.match.stadiumAdress
        binding.team1Name.text = it.match.team.teamName
        binding.team2Name.text = it.match.team2.teamName
        binding.matchTime.text = it.match.matchTime.toString()
        binding.team1Possission.text = it.match.team.ballPosition.toString() + "%"
        binding.team2Possission.text = it.match.team2.ballPosition.toString() + "%"
        binding.ballPositionProgress.progress = it.match.team.ballPosition
        binding.ballPositionProgress.progressTintList = ColorStateList.valueOf(Color.BLUE);
        binding.ballPositionProgress.progressBackgroundTintList =
            ColorStateList.valueOf(Color.BLUE);
        binding.team1.loadImage(it.match.team.teamImage)
        binding.team2.loadImage(it.match.team2.teamImage)
        binding.tvScore.text = it.match.team.score.toString() + " : " + it.match.team2.score.toString()
    }
}