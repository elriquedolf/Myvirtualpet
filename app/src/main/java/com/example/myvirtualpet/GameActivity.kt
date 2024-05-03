package com.example.myvirtualpet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.logging.Handler

class GameActivity : AppCompatActivity() {
    private lateinit var pet: Pets
    private lateinit var happinessLevelTextView: TextView
    private lateinit var hungerLevelTextView: TextView
    private lateinit var hygieneLevelTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        pet = Pets()

        happinessLevelTextView = findViewById(R.id.happinessLevelTextView)
        hungerLevelTextView = findViewById(R.id.hungerLevelTextView)
        hygieneLevelTextView = findViewById(R.id.hygieneLevelTextView)

        updateSatus()

        findViewById<Button>(R.id.feedButton).setOnClickListener { pet.feed(); updateSatus() }
        findViewById<Button>(R.id.cleanButton).setOnClickListener { pet.clean(); updateSatus() }
        findViewById<Button>(R.id.playButton).setOnClickListener { pet.play(); updateSatus() }
        startTimer()

    }

    private fun updateSatus() {
        with(pet) {
            happinessLevelTextView.text = "$happiness"
            hungerLevelTextView.text = "$hunger"
            hygieneLevelTextView.text = "$hygiene"

        }
    }

    private fun startTimer() {
        GlobalScope.launch {
            while (true) {
                with(pet) {
                    increaseHappiness()
                    decreaseHunger()
                    increaseHygiene()
                    updateSatus()
                }
                delay(500000)
            }
        }
    }
}