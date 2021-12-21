package com.example.chucknorrisapp.utils

import java.util.regex.Pattern
import java.util.regex.Matcher


fun validateHero(hero : String) : MutableList<String>?{
    var name: MutableList<String>? = mutableListOf()
    val special: Pattern = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
    val hasSpecial: Matcher = special.matcher(hero)
    if(hero.isEmpty()){
        return null
    }
    val names = hero.split(" ")
    //Just have a first space last name
    if(names.size>2) return null
    for (item in names){
        if (
            !item[0].isUpperCase() //First letter must be uppercase
            || item.length > 15 //No name greater than 15 chars
            || item.length <= 2 // or less than 2
            || hasSpecial.find() // No special chars in name
        ) return null
        else{
            name?.add(item)
        }
    }
    if (name!!.size < 2) return null
    return name
}
