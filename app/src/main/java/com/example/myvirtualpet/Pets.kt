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