package com.example.homegymapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homegymapp.databinding.ActivityHomePageBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var database: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = UserDatabase.getDatabase(this)

        binding.arm.setOnClickListener { navigateToDayList("Arm") }
        binding.abs.setOnClickListener { navigateToDayList("Abs") }
        binding.chest.setOnClickListener { navigateToDayList("Chest") }
        binding.leg.setOnClickListener { navigateToDayList("Leg") }

        checkAndInsertDays()
        checkAndInsertExercises()
    }

    private fun navigateToDayList(muscleGroup: String) {
        val intent = Intent(this, DayList::class.java)
        intent.putExtra("muscle_group", muscleGroup)
        startActivity(intent)
    }

    private fun checkAndInsertDays() {
        val sharedPreferences = getSharedPreferences("com.example.homegymapp", Context.MODE_PRIVATE)
        val daysInserted = sharedPreferences.getBoolean("days_inserted", false)

        if (!daysInserted) {
            insertDays()
            sharedPreferences.edit().putBoolean("days_inserted", true).apply()
        }
    }

    private fun insertDays() {
        val muscles = listOf("Arm", "Chest", "Leg", "Abs")
        CoroutineScope(Dispatchers.IO).launch {
            for (muscle in muscles) {
                for (i in 1..30) {
                    val day = DaydataEntity(id = 0, dayNumber = i, muscleGroup = muscle)
                    database.daydataDao().insertDays(day)
                }
            }
            withContext(Dispatchers.Main) {
                Toast.makeText(this@HomePage, "Days inserted successfully", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun checkAndInsertExercises() {
        val sharedPreferences = getSharedPreferences("com.example.homegymapp", Context.MODE_PRIVATE)
        val exercisesInserted = sharedPreferences.getBoolean("exercises_inserted", false)

        if (!exercisesInserted) {
            insertExercises()
            sharedPreferences.edit().putBoolean("exercises_inserted", true).apply()
        }
    }
    private fun insertExercises() {
        CoroutineScope(Dispatchers.IO).launch{

            val exercises = listOf(
                ExerciseDataEntity(id=0, name="Dynamic chest", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/cheststretch", dayId=1, muscleGroup="Arm", time="0:30"),//
                ExerciseDataEntity(id=0, name="Triceps dips", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/tricepsdips", dayId=1, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Push ups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/pushups", dayId=1, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Diagonal plank", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/diagonalplank", dayId=1, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Incline pushups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/inclinepushups", dayId=1, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Star crawl", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/cheststretch", dayId=1, muscleGroup="Arm", time="0:30"), //
                ExerciseDataEntity(id=0, name="Triceps dips", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/tricepsdips", dayId=1, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Push ups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/pushups", dayId=1, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Diagonal plank", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/diagonalplank", dayId=1, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Incline pushups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/inclinepushups", dayId=1, muscleGroup="Arm", time="0:30"),

                ExerciseDataEntity(id= 0,name ="Elbow back", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 2, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =2, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 2, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 2, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Incline pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 2, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId = 2, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Plank taps", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/planktaps", dayId = 2, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Crab walk", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =2, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Standing biceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 2, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Standing biceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 2, muscleGroup = "Arm", time = "0:20"),//

                ExerciseDataEntity(id=0, name="Punches", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/punches", dayId=3, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Push ups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/tricepsdips", dayId=3, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Knee push ups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/pushups", dayId=3, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Diagonal plank", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/diagonalplank", dayId=3, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Alternating hooks", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/inclinepushups", dayId=3, muscleGroup="Arm", time="0:30"),//
                ExerciseDataEntity(id=0, name="Wall pushups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/wallpushups", dayId=3, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Knee pushups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/tricepsdips", dayId=3, muscleGroup="Arm", time="0:30"),//
                ExerciseDataEntity(id=0, name="Diagonal plank", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/diagonalplank", dayId=3, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Alternating hooks", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/diagonalplank", dayId=3, muscleGroup="Arm", time="0:20"),//
                ExerciseDataEntity(id=0, name="Incline pushups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/inclinepushups", dayId=3, muscleGroup="Arm", time="0:30"),

                ExerciseDataEntity(id= 0,name ="Dynamic chest", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 4, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =4, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 4, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 4, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Knee pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 4, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId = 4, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Wide arm pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 4, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Crab walk", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =4, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 4, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 4, muscleGroup = "Arm", time = "0:20"),//


                ExerciseDataEntity(id=0,name ="Elbow back", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 5, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Push ups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =5, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Wide arm pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 5, muscleGroup = "Arm", time = "0:20"),
                ExerciseDataEntity(id=0,name ="Incline pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 5, muscleGroup = "Arm", time = "0:20"),
                ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 5, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Start crawl", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 5, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Wall pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/wallpushups", dayId = 5, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =5, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Standing biceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 5, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Standing biceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 5, muscleGroup = "Arm", time = "0:20"),//

            ExerciseDataEntity(id=0,name ="Punches", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/punches", dayId = 6, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Push ups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId =6, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Diamond pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diamondpushups", dayId = 6, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId = 6, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Plank tabs", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/planktaps", dayId = 6, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Inch worms", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 6, muscleGroup = "Arm", time = "0:30"),//
            ExerciseDataEntity(id=0,name ="Pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 6, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId =6, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Plank taps", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/planktaps", dayId = 6, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Inch worms", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 6, muscleGroup = "Arm", time = "0:20"),//


            ExerciseDataEntity(id=0,name ="Dynamic chest", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 7, muscleGroup = "Arm", time = "0:30"),//
            ExerciseDataEntity(id=0,name ="Push ups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId =7, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Doorway curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 7, muscleGroup = "Arm", time = "0:20"),//
            ExerciseDataEntity(id=0,name ="Doorway curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 7, muscleGroup = "Arm", time = "0:20"),//
            ExerciseDataEntity(id=0,name ="Knee pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 7, muscleGroup = "Arm", time = "0:30"),//
            ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId = 7, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Inch worms", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 7, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =7, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 7, muscleGroup = "Arm", time = "0:20"),//
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 7, muscleGroup = "Arm", time = "0:20"),//

            ExerciseDataEntity(id=0,name ="Elbow back", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 8, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =8, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Diagonal plank", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 8, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Incline pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 8, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 8, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Crab walk", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 8, muscleGroup = "Arm", time = "0:30"),//
            ExerciseDataEntity(id=0,name ="Wall pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/wallpushups", dayId = 8, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =8, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 8, muscleGroup = "Arm", time = "0:20"),//
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 8, muscleGroup = "Arm", time = "0:20"),//


            ExerciseDataEntity(id=0,name ="Punches", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/punches", dayId = 9, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Knee pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =9, muscleGroup = "Arm", time = "0:30"),//
            ExerciseDataEntity(id=0,name ="Diamond pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diamondpushups", dayId = 9, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 9, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Plank taps", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/planktaps", dayId = 9, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Inch worms", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 9, muscleGroup = "Arm", time = "0:30"),//
            ExerciseDataEntity(id=0,name ="Wall pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/wallpushups", dayId = 9, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Knee pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =9, muscleGroup = "Arm", time = "0:30"),//
            ExerciseDataEntity(id=0,name ="Triceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 9, muscleGroup = "Arm", time = "0:20"),//
            ExerciseDataEntity(id=0,name ="Triceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 9, muscleGroup = "Arm", time = "0:20"),//

            ExerciseDataEntity(id=0,name ="Dynamic chest", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 10, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =10, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Diamond pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diamondpushups", dayId = 10, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Incline pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 10, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 10, muscleGroup = "Arm", time = "0:30"),//
            ExerciseDataEntity(id=0,name ="Plank taps", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/planktaps", dayId = 10, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Inch worms", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 10, muscleGroup = "Arm", time = "0:30"),//
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =10, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Standing biceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 10, muscleGroup = "Arm", time = "0:20"),//
            ExerciseDataEntity(id=0,name ="Standing biceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 10, muscleGroup = "Arm", time = "0:20"),//


            ExerciseDataEntity(id=0,name ="Elbow back", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 11, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId =11, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Wide arm pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/widepushups", dayId = 11, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Diagonal plank", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 11, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Incline pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 11, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId = 11, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Star crawl", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 11, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Wall pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =11, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 11, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Triceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 11, muscleGroup = "Arm", time = "0:20"),

            ExerciseDataEntity(id=0,name ="Punches", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/punches", dayId = 12, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =12, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 12, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 12, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Diamond pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diamondpushups", dayId = 12, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 12, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Plank taps", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/planktaps", dayId = 12, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Inch worms", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =12, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 12, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 12, muscleGroup = "Arm", time = "0:20"),


            ExerciseDataEntity(id=0,name ="Dynamic chest", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 13, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =13, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Push ups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 13, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Doorway curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 13, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Doorway curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 13, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId = 13, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 13, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Crab walk", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =13, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 13, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 13, muscleGroup = "Arm", time = "0:20"),


            ExerciseDataEntity(id=0,name ="Elbow back", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 14, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =14, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Wide arm pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/widepushups", dayId = 14, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Diagonal plank", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 14, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Incline pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 14, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Plank taps", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/planktaps", dayId = 14, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Star crawl", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 14, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =14, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 14, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Triceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 14, muscleGroup = "Arm", time = "0:20"),


            ExerciseDataEntity(id=0,name ="Punches", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 15, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId =15, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 15, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 15, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Knee pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 15, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 15, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Inch worms", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 15, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId =15, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 15, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 15, muscleGroup = "Arm", time = "0:20"),


            ExerciseDataEntity(id=0,name ="Dynamic chest", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 16, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =16, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Wide arm pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 16, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Diamond pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diamondpushups", dayId = 16, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumping jack", dayId = 16, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Plank taps", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/planktaps", dayId = 16, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Crab walk", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 16, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Wall pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/wallpushups", dayId =16, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Standing biceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 16, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Standing biceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 16, muscleGroup = "Arm", time = "0:20"),


            ExerciseDataEntity(id=0,name ="Elbow back", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 17, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =17, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 17, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 17, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 17, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 17, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Inch worms ", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 17, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Wall pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/wallpushups", dayId =17, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 17, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 17, muscleGroup = "Arm", time = "0:20"),


            ExerciseDataEntity(id=0,name ="Punches", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/punches", dayId = 18, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId =18, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 18, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 18, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Diagonal plank", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 18, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Diamond pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diamondpushups", dayId = 18, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId = 18, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Inch worms", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =18, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 18, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Triceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 18, muscleGroup = "Arm", time = "0:20"),


            ExerciseDataEntity(id=0,name ="Elbow back", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 19, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId =19, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Doorway curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 19, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Doorway curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 19, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Wide arm pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/widepushups", dayId = 19, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Diagonal plank", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 19, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Incline pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 19, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId =19, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 19, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 19, muscleGroup = "Arm", time = "0:20"),


            ExerciseDataEntity(id=0,name ="Dynamic chest", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 20, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =20, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Incline pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 20, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId = 20, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Diamond pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diamondpushups", dayId = 20, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/cheststretch", dayId = 20, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Plank taps", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/planktaps", dayId = 20, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Wall pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/wallpushups", dayId =20, muscleGroup = "Arm", time = "0:30"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 20, muscleGroup = "Arm", time = "0:20"),
            ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/diagonalplank", dayId = 20, muscleGroup = "Arm", time = "0:20"),
            )

            database.exerciseDao().insertExercises(exercises)






            withContext(Dispatchers.Main){
                Toast.makeText(this@HomePage, "Exercises inserted successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
