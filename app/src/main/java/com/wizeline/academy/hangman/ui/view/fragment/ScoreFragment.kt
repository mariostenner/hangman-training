package com.wizeline.academy.hangman.ui.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.wizeline.academy.hangman.R
import com.wizeline.academy.hangman.databinding.FragmentGameBinding
import com.wizeline.academy.hangman.databinding.FragmentScoreBinding
import com.wizeline.academy.hangman.ui.view.adapter.ScoreAdapter
import com.wizeline.academy.hangman.ui.viewmodel.ScoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScoreFragment : Fragment() {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val scoreViewModel = ViewModelProvider(this)[ScoreViewModel::class.java]
        val navController = Navigation.findNavController(view)

        scoreViewModel.getScoreTable()
        setObservers(scoreViewModel,navController)
    }

    private fun setObservers(viewModel: ScoreViewModel, navController: NavController){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.tableScore.collect{
                    val scoreAdapter = ScoreAdapter(it)
                    binding.rvScore.adapter = scoreAdapter
                    binding.rvScore.layoutManager = LinearLayoutManager(context)
                }
            }
        }

        binding.btnFinish.setOnClickListener {
            navController.navigate(R.id.action_scoreFragment_to_loginFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}