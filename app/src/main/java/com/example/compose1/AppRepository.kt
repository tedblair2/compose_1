package com.example.compose1

import com.example.compose1.model.Person

class AppRepository {

    fun getUsers():List<Person>{
        return listOf(
            Person("Ted Omino",24,"Mbita, Homabay"),
            Person("Israel Omino",21,"Juja, Kiambu"),
            Person("Brayden Omino", 10, "Komarock, Nairobi"),
            Person("Egbert Omino", 32, "Langata, Nairobi"),
            Person("Young Bill", 29,"Kitengela, Kajiado")
        )
    }
}