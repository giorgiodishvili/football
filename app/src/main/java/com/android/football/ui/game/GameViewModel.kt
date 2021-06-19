package com.android.football.ui.game

import android.util.Log.i
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.football.entity.Game
import com.android.football.api.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameViewModel : ViewModel() {

    private var data = MutableLiveData<Game>().apply {
    }

    val _data get() = data

    fun getGame() {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val response = RetrofitService.RETROFIT.getMatch()
                i("HERE",response.toString())
                if (response.isSuccessful) {
                    val body = response.body()
                    data.postValue(body)
                }
            }

        }
    }
}