package com.wizeline.academy.hangman.ui.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.findFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.wizeline.academy.hangman.R
import com.wizeline.academy.hangman.databinding.FragmentGameBinding
import com.wizeline.academy.hangman.databinding.FragmentLoginBinding
import com.wizeline.academy.hangman.ui.viewmodel.GameViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@AndroidEntryPoint
class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    var hangman = 0
    private var username = ""

    private var alertDialog : AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameViewModel = ViewModelProvider(this)[GameViewModel::class.java]
        val navController = Navigation.findNavController(view)
        username = arguments?.get("username").toString()
//
//
//        gameViewModel.getRandomWords().apply {
//        timer.start()
//        setObservers(gameViewModel,navController)
//        setlistenersKeyBoard(gameViewModel)
//        }

        /**** Rx Kotlin**/
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                gameViewModel.getRandomWords()
            }
        }

            timer.start()
            setObservers(gameViewModel,navController)
            setlistenersKeyBoard(gameViewModel)

        /*****/

       alertDialog = parentFragment?.let {
            val builder = AlertDialog.Builder(it.context)
            builder.apply {
                setPositiveButton("Leaderboard"
                ) { dialog, id ->
                    timer.cancel()
                    endGame(navController, gameViewModel)
                }
                setNegativeButton("Try Again"
                ) { dialog, id ->
                    navController.navigate(R.id.action_gameFragment_to_loginFragment)
                }
            }.setTitle("GAME OVER").setCancelable(false)
            builder.create()
        }

    }

    private fun setObservers(viewModel: GameViewModel, navController: NavController){
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    viewModel.wordToGame.collect{randomWord ->
                        binding.etFindWords.text = randomWord
                    }
                }

                launch{
                    viewModel.counterIntents.collect{
                        if (it==10){
                            bindHangman(it)
                            alertDialog?.show()
                        }
                        else
                        {
                            bindHangman(it)
                        }
                    }
                }
                launch {
                    viewModel.totalScore.collect{
                        binding.tvScore.text = "Score:${it.toString()}"
                    }
                }
                launch {
                    viewModel.winRound.collect{
                        if(it) {
                            setEnabledButtons(false)
                            binding.btnNext.visibility = View.VISIBLE
                            binding.btnHint.visibility = View.INVISIBLE
                        }
                    }
                }
                launch {
                    viewModel.pointerMovie.collect{
                        binding.textView5.text = "${it+1}/10"

                        if(it==10){
                            alertDialog?.show()
                        }
                    }
                }
            }
        }


        binding.btnNext.setOnClickListener {
            setEnabledButtons(true)
            viewModel.counterIntents.value = 0
            viewModel.pointerMovie.value += 1
            /** DESCOMENTAR!!!!!s**/
            viewModel.getRandomWords()
            binding.btnNext.visibility = View.INVISIBLE
            binding.btnHint.visibility = View.VISIBLE
        }

        binding.btnHint.setOnClickListener {
            setEnabledButtons(false)
            binding.btnNext.visibility = View.VISIBLE
            binding.btnHint.visibility = View.INVISIBLE
            viewModel.hintWord()
        }
    }


    private fun endGame(navController: NavController, viewModel: GameViewModel){

        viewModel.insertScore(username).apply {
            navController.navigate(R.id.action_gameFragment_to_scoreFragment)
        }

    }

    private fun bindHangman(intentsCounter: Int){
        when(intentsCounter){
            0 -> binding.ivHangman.setImageResource(R.color.my_purple_background)
            1 -> binding.ivHangman.setImageResource(R.drawable.hangman_1)
            2 -> binding.ivHangman.setImageResource(R.drawable.hangman_2)
            3 -> binding.ivHangman.setImageResource(R.drawable.hangman_3)
            4 -> binding.ivHangman.setImageResource(R.drawable.hangman_4)
            5 -> binding.ivHangman.setImageResource(R.drawable.hangman_5)
            6 -> binding.ivHangman.setImageResource(R.drawable.hangman_6)
            7 -> binding.ivHangman.setImageResource(R.drawable.hangman_7)
            8 -> binding.ivHangman.setImageResource(R.drawable.hangman_8)
            9 -> binding.ivHangman.setImageResource(R.drawable.hangman_9)
            10 -> binding.ivHangman.setImageResource(R.drawable.hangman_10)
        }

    }

    private fun setlistenersKeyBoard(viewModel: GameViewModel){
        binding.include.btnA.setOnClickListener { viewModel.validateChar("A")
            binding.include.btnA.isEnabled = false}
        binding.include.btnB.setOnClickListener { viewModel.validateChar("B")
            binding.include.btnB.isEnabled = false}
        binding.include.btnC.setOnClickListener { viewModel.validateChar("C")
            binding.include.btnC.isEnabled = false}
        binding.include.btnD.setOnClickListener { viewModel.validateChar("D")
            binding.include.btnD.isEnabled = false}
        binding.include.btnE.setOnClickListener { viewModel.validateChar("E")
            binding.include.btnE.isEnabled = false}
        binding.include.btnF.setOnClickListener { viewModel.validateChar("F")
            binding.include.btnF.isEnabled = false}
        binding.include.btnG.setOnClickListener { viewModel.validateChar("G")
            binding.include.btnG.isEnabled = false}
        binding.include.btnH.setOnClickListener { viewModel.validateChar("H")
            binding.include.btnH.isEnabled = false}
        binding.include.btnI.setOnClickListener { viewModel.validateChar("I")
            binding.include.btnI.isEnabled = false}
        binding.include.btnJ.setOnClickListener { viewModel.validateChar("J")
            binding.include.btnJ.isEnabled = false}
        binding.include.btnK.setOnClickListener { viewModel.validateChar("K")
            binding.include.btnK.isEnabled = false}
        binding.include.btnL.setOnClickListener { viewModel.validateChar("L")
            binding.include.btnL.isEnabled = false}
        binding.include.btnM.setOnClickListener { viewModel.validateChar("M")
            binding.include.btnM.isEnabled = false}
        binding.include.btnN.setOnClickListener { viewModel.validateChar("N")
            binding.include.btnN.isEnabled = false}
        binding.include.btnO.setOnClickListener { viewModel.validateChar("O")
            binding.include.btnO.isEnabled = false}
        binding.include.btnP.setOnClickListener { viewModel.validateChar("P")
            binding.include.btnP.isEnabled = false}
        binding.include.btnQ.setOnClickListener { viewModel.validateChar("Q")
            binding.include.btnQ.isEnabled = false}
        binding.include.btnR.setOnClickListener { viewModel.validateChar("R")
            binding.include.btnR.isEnabled = false}
        binding.include.btnS.setOnClickListener { viewModel.validateChar("S")
            binding.include.btnS.isEnabled = false}
        binding.include.btnT.setOnClickListener { viewModel.validateChar("T")
            binding.include.btnT.isEnabled = false}
        binding.include.btnU.setOnClickListener { viewModel.validateChar("U")
            binding.include.btnU.isEnabled = false}
        binding.include.btnV.setOnClickListener { viewModel.validateChar("V")
            binding.include.btnV.isEnabled = false}
        binding.include.btnW.setOnClickListener { viewModel.validateChar("W")
            binding.include.btnW.isEnabled = false}
        binding.include.btnX.setOnClickListener { viewModel.validateChar("X")
            binding.include.btnX.isEnabled = false}
        binding.include.btnY.setOnClickListener { viewModel.validateChar("Y")
            binding.include.btnY.isEnabled = false}
        binding.include.btnZ.setOnClickListener { viewModel.validateChar("Z")
            binding.include.btnZ.isEnabled = false}



    }

    private fun setEnabledButtons(setEnabled: Boolean){
        binding.include.btnA.isEnabled = setEnabled
        binding.include.btnB.isEnabled = setEnabled
        binding.include.btnC.isEnabled = setEnabled
        binding.include.btnD.isEnabled = setEnabled
        binding.include.btnE.isEnabled = setEnabled
        binding.include.btnF.isEnabled = setEnabled
        binding.include.btnG.isEnabled = setEnabled
        binding.include.btnH.isEnabled = setEnabled
        binding.include.btnI.isEnabled = setEnabled
        binding.include.btnJ.isEnabled = setEnabled
        binding.include.btnK.isEnabled = setEnabled
        binding.include.btnL.isEnabled = setEnabled
        binding.include.btnM.isEnabled = setEnabled
        binding.include.btnN.isEnabled = setEnabled
        binding.include.btnO.isEnabled = setEnabled
        binding.include.btnP.isEnabled = setEnabled
        binding.include.btnQ.isEnabled = setEnabled
        binding.include.btnR.isEnabled = setEnabled
        binding.include.btnS.isEnabled = setEnabled
        binding.include.btnT.isEnabled = setEnabled
        binding.include.btnU.isEnabled = setEnabled
        binding.include.btnV.isEnabled = setEnabled
        binding.include.btnW.isEnabled = setEnabled
        binding.include.btnX.isEnabled = setEnabled
        binding.include.btnY.isEnabled = setEnabled
        binding.include.btnZ.isEnabled = setEnabled
    }

    private val timer = object: CountDownTimer(300000,1000){
        override fun onTick(millisUntilFinished: Long) {
            var valueInSeconds =(millisUntilFinished/1000L).toDuration(DurationUnit.SECONDS).toString()
            binding.tvTimer.text = valueInSeconds
            println((millisUntilFinished/1000L).toDuration(DurationUnit.SECONDS))
        }
        override fun onFinish() {
            alertDialog?.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}