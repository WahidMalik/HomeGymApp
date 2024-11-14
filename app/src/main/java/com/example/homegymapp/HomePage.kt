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

            val armExercises = listOf(
                ExerciseDataEntity(id=0, name="Dynamic chest", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/cheststretch", dayId=1, muscleGroup="Arm", time="0:30"),//
                ExerciseDataEntity(id=0, name="Triceps dips", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/tricepsdips", dayId=1, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Push ups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/pushups", dayId=1, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Diagonal plank", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/diagonalplank", dayId=1, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Incline pushups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/inclinepushups", dayId=1, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Star crawl", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/starcrawl", dayId=1, muscleGroup="Arm", time="0:30"),
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
                ExerciseDataEntity(id=0,name ="Standing biceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/standingbiceps", dayId = 2, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Standing biceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/standingbiceps", dayId = 2, muscleGroup = "Arm", time = "0:20"),//

                ExerciseDataEntity(id=0, name="Punches", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/punches", dayId=3, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Push ups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/pushups", dayId=3, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Knee push ups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/kneepushups", dayId=3, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Diagonal plank", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/diagonalplank", dayId=3, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Alternating hooks", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/hookups", dayId=3, muscleGroup="Arm", time="0:30"),//
                ExerciseDataEntity(id=0, name="Wall pushups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/wallpushups", dayId=3, muscleGroup="Arm", time="0:30"),
                ExerciseDataEntity(id=0, name="Knee pushups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/kneepushups", dayId=3, muscleGroup="Arm", time="0:30"),//
                ExerciseDataEntity(id=0, name="Diagonal plank", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/diagonalplank", dayId=3, muscleGroup="Arm", time="0:20"),
                ExerciseDataEntity(id=0, name="Alternating hooks", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/hookups", dayId=3, muscleGroup="Arm", time="0:20"),//
                ExerciseDataEntity(id=0, name="Incline pushups", image=R.id.leg, videoUrl="android.resource://${packageName}/raw/inclinepushups", dayId=3, muscleGroup="Arm", time="0:30"),

                ExerciseDataEntity(id= 0,name ="Dynamic chest", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 4, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =4, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/legbarbellcurl", dayId = 4, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/legbarbellcurl", dayId = 4, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Knee pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/kneepushups", dayId = 4, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Jumping jack", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/jumpingjack", dayId = 4, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Wide arm pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId = 4, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Crab walk", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/crabwalk", dayId =4, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Leg barbel curl left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/legbarbellcurl", dayId = 4, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Leg barbel curl right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/legbarbellcurl", dayId = 4, muscleGroup = "Arm", time = "0:20"),//


                ExerciseDataEntity(id=0,name ="Elbow back", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/elbowback", dayId = 5, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Push ups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/pushups", dayId =5, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Wide arm pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/widearmpushups", dayId = 5, muscleGroup = "Arm", time = "0:20"),
                ExerciseDataEntity(id=0,name ="Incline pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/inclinepushups", dayId = 5, muscleGroup = "Arm", time = "0:20"),
                ExerciseDataEntity(id=0,name ="Alternating hooks", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/hookups", dayId = 5, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Start crawl", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/starcrawl", dayId = 5, muscleGroup = "Arm", time = "0:30"),//
                ExerciseDataEntity(id=0,name ="Wall pushups", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/wallpushups", dayId = 5, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Triceps dips", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/tricepsdips", dayId =5, muscleGroup = "Arm", time = "0:30"),
                ExerciseDataEntity(id=0,name ="Standing biceps stretch left", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/standingbiceps", dayId = 5, muscleGroup = "Arm", time = "0:20"),//
                ExerciseDataEntity(id=0,name ="Standing biceps stretch right", image = R.id.leg, videoUrl = "android.resource://${packageName}/raw/standingbiceps", dayId = 5, muscleGroup = "Arm", time = "0:20"),//

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



            val absExercises = listOf(

                ExerciseDataEntity(id = 0, name = "Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/crunches", dayId = 1, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Leg Raises", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/leg_raises", dayId = 1, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Mountain Climbers", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/mountain_climbers", dayId = 1, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "Bicycle Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bicycle_crunch", dayId = 1, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_hold", dayId = 1, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Heel Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/heel_touches", dayId = 1, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Russian Twists", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/russian_twists", dayId = 1, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "V-Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/v_ups", dayId = 1, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "Side Plank Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_left", dayId = 1, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_right", dayId = 1, muscleGroup = "Abs", time = "0:30"),

                // Day 2
                ExerciseDataEntity(id = 0, name = "Reverse Crunch", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/reverse_crunch", dayId = 2, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Flutter Kicks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/flutter_kicks", dayId = 2, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Toe Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/toe_touches", dayId = 2, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Seated Knee Tucks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/knee_tucks", dayId = 2, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Dead Bug", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/dead_bug", dayId = 2, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hollow Body Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hollow_body", dayId = 2, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunch Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunch_left", dayId = 2, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunch Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunch_right", dayId = 2, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "High Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/high_plank", dayId = 2, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Scissor Kicks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/scissor_kicks", dayId = 2, muscleGroup = "Abs", time = "0:20"),

                ExerciseDataEntity(id = 0, name = "Sit Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/sit_ups", dayId = 3, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Russian Twist", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/russian_twist", dayId = 3, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Lying Leg Raise", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/lying_leg_raise", dayId = 3, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "V-Sit Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/v_sit_hold", dayId = 3, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Bicycle Kick", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bicycle_kick", dayId = 3, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Elbow Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/elbow_plank", dayId = 3, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Spiderman Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/spiderman_plank", dayId = 3, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank to Push Up", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_to_push_up", dayId = 3, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Crunch Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_crunch_left", dayId = 3, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Crunch Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_crunch_right", dayId = 3, muscleGroup = "Abs", time = "0:30"),

                ExerciseDataEntity(id = 0, name = "Hanging Leg Raise", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hanging_leg_raise", dayId = 4, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Dips Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_dips_left", dayId = 4, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Dips Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_dips_right", dayId = 4, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Leg Scissors", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/leg_scissors", dayId = 4, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hip Raise", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hip_raise", dayId = 4, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Shoulder Taps", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_shoulder_taps", dayId = 4, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Climbers", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/climbers", dayId = 4, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side V-Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_v_ups", dayId = 4, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Reverse Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/reverse_plank", dayId = 4, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Bear Crawl", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bear_crawl", dayId = 4, muscleGroup = "Abs", time = "0:20"),

                // Day 5
                ExerciseDataEntity(id = 0, name = "Tuck Crunch", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/tuck_crunch", dayId = 5, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Leg Raises Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_leg_raises_left", dayId = 5, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Leg Raises Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_leg_raises_right", dayId = 5, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Crossbody Climbers", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/crossbody_climbers", dayId = 5, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Seated Russian Twists", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/seated_twists", dayId = 5, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Flutter Kicks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/flutter_kicks", dayId = 5, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Boat Pose Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/boat_pose", dayId = 5, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Spiderman Push-ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/spiderman_pushups", dayId = 5, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hollow Body Rock", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hollow_body_rock", dayId = 5, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Elbow-to-Knee Crunch", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/elbow_knee_crunch", dayId = 5, muscleGroup = "Abs", time = "0:20"),

                // Day 6
                ExerciseDataEntity(id = 0, name = "Ab Rollouts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/ab_rollouts", dayId = 6, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Rotations Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_rotations_left", dayId = 6, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Rotations Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_rotations_right", dayId = 6, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Ab Bicycle", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/ab_bicycle", dayId = 6, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Superman Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/superman_plank", dayId = 6, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank to Side Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_to_side_plank", dayId = 6, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Toe Taps", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/toe_taps", dayId = 6, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Butterfly Sit-ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/butterfly_situps", dayId = 6, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank with Reach", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_with_reach", dayId = 6, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "V-Up Twists", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/v_up_twists", dayId = 6, muscleGroup = "Abs", time = "0:20"),

                ExerciseDataEntity(id = 0, name = "Dragon Flags", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/dragon_flags", dayId = 7, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Windshield Wipers", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/windshield_wipers", dayId = 7, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Swiss Ball Rollout", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/swiss_ball_rollout", dayId = 7, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Mountain Climbers", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/mountain_climbers", dayId = 7, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "V-Sits", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/v_sits", dayId = 7, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Walkouts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_walkouts", dayId = 7, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hollow Rock", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hollow_rock", dayId = 7, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Toe Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/toe_touches", dayId = 7, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Superman Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/superman_hold", dayId = 7, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_left", dayId = 7, muscleGroup = "Abs", time = "0:30"),

                ExerciseDataEntity(id = 0, name = "Russian Twists", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/russian_twists", dayId = 8, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunches", dayId = 8, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Dips", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_dips", dayId = 8, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Leg Raises", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/leg_raises", dayId = 8, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Seated Knee Tucks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/knee_tucks", dayId = 8, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Climbers with Twists", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/climbers_twists", dayId = 8, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "High Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/high_plank", dayId = 8, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Flutter Kicks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/flutter_kicks", dayId = 8, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Shoulder Taps", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_shoulder_taps", dayId = 8, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Bicycle Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bicycle_crunches", dayId = 8, muscleGroup = "Abs", time = "0:30"),

                ExerciseDataEntity(id = 0, name = "Bear Crawl", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bear_crawl", dayId = 9, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "Reverse Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/reverse_crunches", dayId = 9, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Butterfly Sit-Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/butterfly_situps", dayId = 9, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hollow Body Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hollow_body_hold", dayId = 9, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Tuck Crunch", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/tuck_crunch", dayId = 9, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side V-Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_v_ups", dayId = 9, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Dead Bug", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/dead_bug", dayId = 9, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Toe Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/toe_touches", dayId = 9, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunch Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunch_left", dayId = 9, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunch Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunch_right", dayId = 9, muscleGroup = "Abs", time = "0:30"),

                ExerciseDataEntity(id = 0, name = "Bicycle Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bicycle_crunches", dayId = 10, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Mountain Climbers", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/mountain_climbers", dayId = 10, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "Russian Twists", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/russian_twists", dayId = 10, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Leg Raises", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/leg_raises", dayId = 10, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Shoulder Taps", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_shoulder_taps", dayId = 10, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_left", dayId = 10, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_right", dayId = 10, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Heel Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/heel_touches", dayId = 10, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "V-Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/v_ups", dayId = 10, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "High Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/high_plank", dayId = 10, muscleGroup = "Abs", time = "0:30"),

                // Day 11
                ExerciseDataEntity(id = 0, name = "Reverse Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/reverse_crunches", dayId = 11, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Toe Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/toe_touches", dayId = 11, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunch Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunch_left", dayId = 11, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunch Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunch_right", dayId = 11, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Scissor Kicks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/scissor_kicks", dayId = 11, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Dead Bug", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/dead_bug", dayId = 11, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hollow Body Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hollow_body", dayId = 11, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Walkouts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_walkouts", dayId = 11, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Superman Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/superman_hold", dayId = 11, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Bear Crawl", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bear_crawl", dayId = 11, muscleGroup = "Abs", time = "0:20"),

                // Day 12
                ExerciseDataEntity(id = 0, name = "Bicycle Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bicycle_crunch", dayId = 12, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Seated Knee Tucks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/knee_tucks", dayId = 12, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "V-Sits", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/v_sits", dayId = 12, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hollow Rock", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hollow_rock", dayId = 12, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side V-Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_v_ups", dayId = 12, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Climbers with Twists", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/climbers_twists", dayId = 12, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "Leg Raises", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/leg_raises", dayId = 12, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunches", dayId = 12, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Heel Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/heel_touches", dayId = 12, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Dragon Flags", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/dragon_flags", dayId = 12, muscleGroup = "Abs", time = "0:30"),

                ExerciseDataEntity(id = 0, name = "Sit-Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/sit_ups", dayId = 13, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Leg Raises", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/leg_raises", dayId = 13, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank with Reach Under (Left)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_left", dayId = 13, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank with Reach Under (Right)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_right", dayId = 13, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Flutter Kicks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/flutter_kicks", dayId = 13, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank to Push-Up", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_push_up", dayId = 13, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Bicycle Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bicycle_crunches", dayId = 13, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Toe Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/toe_touches", dayId = 13, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank with Arm Lift", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_arm_lift", dayId = 13, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Mountain Climbers", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/mountain_climbers", dayId = 13, muscleGroup = "Abs", time = "0:20"),

                // Day 14
                ExerciseDataEntity(id = 0, name = "Reverse Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/reverse_crunches", dayId = 14, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunch Left", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunch_left", dayId = 14, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Oblique Crunch Right", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/oblique_crunch_right", dayId = 14, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side V-Ups (Left)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_v_ups", dayId = 14, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side V-Ups (Right)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_v_ups", dayId = 14, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_hold", dayId = 14, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "V-Sits", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/v_sits", dayId = 14, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Scissor Kicks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/scissor_kicks", dayId = 14, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Superman Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/superman_hold", dayId = 14, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Climbers with Twists", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/climbers_twists", dayId = 14, muscleGroup = "Abs", time = "0:20"),

                // Day 15
                ExerciseDataEntity(id = 0, name = "Dead Bug", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/dead_bug", dayId = 15, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hollow Body Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hollow_body", dayId = 15, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Dragon Flags", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/dragon_flags", dayId = 15, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Heel Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/heel_touches", dayId = 15, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Bear Crawl", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bear_crawl", dayId = 15, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "Plank with Shoulder Taps", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_shoulder_taps", dayId = 15, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "V-Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/v_ups", dayId = 15, muscleGroup = "Abs", time = "0:20"),
                ExerciseDataEntity(id = 0, name = "Toe Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/toe_touches", dayId = 15, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Seated Knee Tucks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/knee_tucks", dayId = 15, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Walkouts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_walkouts", dayId = 15, muscleGroup = "Abs", time = "0:30"),

                ExerciseDataEntity(id = 0, name = "Russian Twists", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/russian_twists", dayId = 16, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Jacks", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_jacks", dayId = 16, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Crunches (Left)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_crunch_left", dayId = 16, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Crunches (Right)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_crunch_right", dayId = 16, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Lying Leg Lifts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/lying_leg_lifts", dayId = 16, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Reach", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_reach", dayId = 16, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Crab Toe Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/crab_toe_touches", dayId = 16, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Windshield Wipers", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/windshield_wipers", dayId = 16, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hanging Knee Raises", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hanging_knee_raises", dayId = 16, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Boat Pose", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/boat_pose", dayId = 16, muscleGroup = "Abs", time = "0:30"),

                // Day 17
                ExerciseDataEntity(id = 0, name = "Plank with Leg Lifts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_leg_lifts", dayId = 17, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Hip Dips (Left)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_hip_dips_left", dayId = 17, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Plank Hip Dips (Right)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_plank_hip_dips_right", dayId = 17, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank Up-Downs", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_up_downs", dayId = 17, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Boxing Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/boxing_crunches", dayId = 17, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Kneeling Ab Rollouts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/ab_rollouts", dayId = 17, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Lateral Leg Raises", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/lateral_leg_raises", dayId = 17, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank to Side Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_to_side_plank", dayId = 17, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Leg Lifts (Left)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_leg_lifts_left", dayId = 17, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Side Leg Lifts (Right)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/side_leg_lifts_right", dayId = 17, muscleGroup = "Abs", time = "0:30"),

                // Day 18
                ExerciseDataEntity(id = 0, name = "T-Push-Ups", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/t_push_ups", dayId = 18, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Seated Leg Lifts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/seated_leg_lifts", dayId = 18, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Ab Rollouts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/ab_rollouts", dayId = 18, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hip Bridges", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hip_bridges", dayId = 18, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Cross-Body Mountain Climbers", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/cross_body_mountain_climbers", dayId = 18, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Single-Leg Plank (Left)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/single_leg_plank_left", dayId = 18, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Single-Leg Plank (Right)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/single_leg_plank_right", dayId = 18, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Back Widows", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/back_widows", dayId = 18, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Tabletop Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/tabletop_crunches", dayId = 18, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Knee Tucks with Stability Ball", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/knee_tucks_stability_ball", dayId = 18, muscleGroup = "Abs", time = "0:30"),

                ExerciseDataEntity(id = 0, name = "Toe Taps", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/toe_taps", dayId = 19, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Lateral Crunches (Left)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/lateral_crunch_left", dayId = 19, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Lateral Crunches (Right)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/lateral_crunch_right", dayId = 19, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Bear Crawls", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/bear_crawls", dayId = 19, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Wall Sit", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/wall_sit", dayId = 19, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Alternating Toe Touches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/alternating_toe_touches", dayId = 19, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Abdominal Squeeze", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/abdominal_squeeze", dayId = 19, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Pike Crunch", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/pike_crunch", dayId = 19, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Superman Hold", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/superman_hold", dayId = 19, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Dumbbell Side Bend (Left)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/dumbbell_side_bend_left", dayId = 19, muscleGroup = "Abs", time = "0:30"),

                // Day 20
                ExerciseDataEntity(id = 0, name = "Dumbbell Side Bend (Right)", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/dumbbell_side_bend_right", dayId = 20, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Plank with Shoulder Taps", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/plank_with_shoulder_taps", dayId = 20, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Standing Oblique Crunches", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/standing_oblique_crunches", dayId = 20, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Reverse Plank", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/reverse_plank", dayId = 20, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Hip Thrusts", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/hip_thrusts", dayId = 20, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Leg Lifts on a Bench", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/leg_lifts_bench", dayId = 20, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Sledgehammer Twist", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/sledgehammer_twist", dayId = 20, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Standing Knee Raise", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/standing_knee_raise", dayId = 20, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Stability Ball Pass", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/stability_ball_pass", dayId = 20, muscleGroup = "Abs", time = "0:30"),
                ExerciseDataEntity(id = 0, name = "Pilates Scissors", image = R.id.abs, videoUrl = "android.resource://${packageName}/raw/pilates_scissors", dayId = 20, muscleGroup = "Abs", time = "0:30")


            )
            database.exerciseDao().insertExercises(armExercises)
            database.exerciseDao().insertExercises(absExercises)






            withContext(Dispatchers.Main){
                Toast.makeText(this@HomePage, "Exercises inserted successfully", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
