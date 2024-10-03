package com.example.homegymapp

import android.os.Bundle
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

        val time = intent.getStringExtra("time")
        val name = intent.getStringExtra("name")
        binding.exeName.text = name

        if (time != null) {
            try {

                val timeParts = time.split(":")
                val minutes = timeParts[0].toIntOrNull() ?: 0
                val seconds = timeParts[1].toIntOrNull() ?: 0
                val totalTimeInSeconds = minutes * 60 + seconds

                binding.progressBar.setCircularTimerListener(object : CircularTimerListener {
                    override fun updateDataOnTick(remainingTimeInMs: Long): String {
                        return ceil(remainingTimeInMs / 1000f).toInt().toString()
                    }

                    override fun onTimerFinished() {
                        binding.progressBar.setPrefix("")
                        binding.progressBar.setSuffix("")
                        binding.progressBar.setText("FINISHED THANKS!")
                    }
                }, totalTimeInSeconds.toLong(), TimeFormatEnum.SECONDS, totalTimeInSeconds.toLong())

                binding.progressBar.startTimer()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Invalid time format", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
