package com.example.homegymapp

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.homegymapp.databinding.ActivityExerciceDetailsBinding
import com.uzairiqbal.circulartimerview.CircularTimerListener
import com.uzairiqbal.circulartimerview.TimeFormatEnum
import kotlin.math.ceil


class ExerciceDetails : AppCompatActivity() {
    lateinit var binding: ActivityExerciceDetailsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityExerciceDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.progressBar.setCircularTimerListener(object : CircularTimerListener {
            override fun updateDataOnTick(remainingTimeInMs: Long): String {
                return ceil((remainingTimeInMs / 1000f).toDouble()).toInt().toString()
            }

            override fun onTimerFinished() {
                binding.progressBar.setPrefix("")
                binding.progressBar.setSuffix("")
                binding.progressBar.setText("FINISHED THANKS!")
            }
        }, 10, TimeFormatEnum.SECONDS, 10)

        binding.progressBar.startTimer()
    }

}
