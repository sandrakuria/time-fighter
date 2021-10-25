package com.example.timefighter.UI

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.timefighter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var uitimer:CountDownTimer
    private var time_in_ms = 0L
    private var isRunning:Boolean = false
    private  var clickcount = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.playbtn.setOnClickListener {
            startTimer()
            binding.timertext.setOnClickListener {
                clickcount++
            }
        }
        binding.pausebtn.setOnClickListener {
            pauseTimer()
        }

        return binding.root

    }
    private fun startTimer(){
        uitimer = object : CountDownTimer(10000,1000){
            override fun onFinish() {
                timesUp()
            }

            override fun onTick(p0: Long) {
                time_in_ms = p0
                updateTextUI()
            }
        }
        uitimer.start()
        isRunning = true
    }

    private fun pauseTimer(){
        binding.timertext.text = time_in_ms.toString()
        uitimer.cancel()
        isRunning = false
    }
    private fun timesUp(){
        binding.timertext.text = "Time's Up"
    }

    private fun updateTextUI(){
        val seconds = (time_in_ms/1000)%60
        binding.timertext.text = seconds.toString()
    }
}