package com.wizeline.academy.hangman.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wizeline.academy.hangman.R
import com.wizeline.academy.hangman.databinding.FragmentScoreBinding
import com.wizeline.academy.hangman.databinding.ItemScoreBinding
import com.wizeline.academy.hangman.domain.model.UserModel

class ScoreAdapter (
    private val usersScore: List<UserModel>
    ):RecyclerView.Adapter<ScoreAdapter.ItemScoreViewHolder>(){

    private var binding: ItemScoreBinding? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemScoreViewHolder {
        binding = ItemScoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemScoreViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ItemScoreViewHolder, position: Int) {
        val username = usersScore[position]
        holder.bind(username,(position+1).toString())
    }

    override fun getItemCount(): Int = usersScore.size


    inner class ItemScoreViewHolder(
        private val binding: ItemScoreBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(userScore: UserModel, place: String){
            binding.tvUsername.text = userScore.username
            binding.tvPlace.text = place
            binding.tvScore.text = userScore.score.toString()
        }
    }
}

