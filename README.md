Code:

MainActivity.kt 

package com.example.myvirtualpet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.startButton)
        startButton.setOnClickListener {
            startActivity(Intent( this, GameActivity::class.java))
        }
    }
}

GameActivity.kt

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

Pets.kt

package com.example.myvirtualpet

class Pets {
    var happiness: Int = 10
    var hunger: Int = 100
    var hygiene: Int = 10
// function to feed with pet
    fun feed() {
        hunger -= 10
        if (hunger < 0) hunger = 0
    }
// function to clean the pet
    fun clean() {
        hygiene += 20
        if (hygiene > 100) hygiene = 100
    }
// function to play with the pet
    fun play() {
        happiness += 10
        if (happiness > 100) happiness = 100
    }

    fun increaseHappiness() {
        happiness += 10
        if (happiness > 100) happiness = 100
    }

    fun decreaseHunger() {
        hunger -= 10
        if (hunger < 0) hunger = 0
    }

    fun increaseHygiene() {
        hygiene += 20
        if (hygiene > 100) hygiene = 100
    }
}

// reference list
// IIE 2024. Introduction to mobile application development. Module Manual. The Independent Institution of Education.
