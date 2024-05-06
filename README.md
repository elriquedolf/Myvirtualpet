App description:

Welcome Screen and Start Game Button:

![Screenshot (2172)](https://github.com/elriquedolf/Myvirtualpet/assets/163988160/cd0ef6be-2895-4e43-b9e3-d5b785743253)

When you open the app, you see a friendly welcome message and a button that says "Start Game."
Clicking the "Start Game" button takes you to the main part of the app where you take care of your virtual pet.

Main Game Screen:

![Screenshot (2169)](https://github.com/elriquedolf/Myvirtualpet/assets/163988160/5819f685-178e-414f-a60a-749fe3ede1e7)

On this screen, you see your virtual pet and some important information about it, like its name and how it's feeling.
There are buttons to feed, play with, and clean your pet.

Feed Button:

![Screenshot (2170)](https://github.com/elriquedolf/Myvirtualpet/assets/163988160/e8f8f206-881a-4b9a-96b8-5be168d48a97)

Clicking the feed button gives your pet some food to eat.
It makes your pet less hungry, so it feels better.

Play Button:

![Screenshot (2173)](https://github.com/elriquedolf/Myvirtualpet/assets/163988160/5438651c-2102-4cea-812f-69d955644dff)

Clicking the play button lets you play with your pet, like throwing a ball or giving it a toy.
It makes your pet happier, so it feels good.

Clean Button:

![Screenshot (2171)](https://github.com/elriquedolf/Myvirtualpet/assets/163988160/a5b4bda6-48b8-4924-9558-55176c942765)

Clicking the clean button cleans your pet up, like giving it a bath or brushing its fur.
It makes your pet cleaner, so it stays healthy.

Percentage Indicators:

![Screenshot (2174)](https://github.com/elriquedolf/Myvirtualpet/assets/163988160/07ac7ee8-1017-4eb5-a7e0-b86a5239ad09)


There are numbers that show how clean, hungry, and happy your pet is.
These numbers change when you feed, play, or clean your pet.

Interaction and Feedback:

![Screenshot (2176)](https://github.com/elriquedolf/Myvirtualpet/assets/163988160/6573adce-3af9-4ab7-a20f-b897f8b31e0d)


Everything you do affects how your pet feels.
When you take care of your pet, you can see it getting happier, cleaner, and less hungry. It's like taking care of a real pet, but on your phone!
The main goal of the app is to have fun taking care of your virtual pet and making sure it's happy and healthy.

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
