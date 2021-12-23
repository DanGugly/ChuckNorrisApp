package com.example.chucknorrisapp.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.chucknorrisapp.R
import java.util.regex.Pattern
import java.util.regex.Matcher

/**
 * This validation is good, but is too aggressive
 * What about the character Venom ? it does not have a last name
 * What about the people they usually don't write the capital letters?
 *
 * Lets have some room for naming without last name
 * And if we need some specific format (capital letters) we can do it internally
 *
 * We need to avoid the fully confidence that user will enter correct data
 */
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

/**
 * This method will help to reduce the duplicated code of changing fragments
 */
fun switchFragments(manager: FragmentManager, fragment: Fragment) {
    manager.beginTransaction()
        .replace(R.id.fragment_container, fragment)
        .addToBackStack(null)
        .commit()
}
