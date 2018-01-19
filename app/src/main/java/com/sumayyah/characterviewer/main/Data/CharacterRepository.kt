package com.sumayyah.characterviewer.main.Data

import com.sumayyah.characterviewer.main.Console
import com.sumayyah.characterviewer.main.Managers.NetworkManager
import com.sumayyah.characterviewer.main.Model.Character
import com.sumayyah.characterviewer.main.Model.RelatedTopic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CharacterRepository { //TODO singleton

    private val networkManager = NetworkManager() //TODO Inject
    private val dataTransformer = DataTransformer()

    private var characterList : ArrayList<Character>  = ArrayList()

    lateinit var listener: OnCompleteListener //TODO replace with RxJava Observable and/or presenters

    //get all characters
    fun getAllCharacters() : ArrayList<Character> {

        if (characterList.size <=0 ) { //TODO add a database/cache fetch. How does okHttp work?
            return fetchCharactersFromApi()
        }

        return characterList
    }

    fun init() {
        characterList = fetchCharactersFromApi()
    }

    //get a specified character
    fun getSpecificCharacterByName(characterName: String) : Character? {

        characterList.map {
            if (it.name == characterName) return it
        }
        return null
    }

    private fun fetchCharactersFromApi() : ArrayList<Character> {
        Console.log("Repository", "Getting all characters from api" )

        val callback = object : Callback<List<RelatedTopic>> {
            override fun onResponse(call: Call<List<RelatedTopic>>, response: Response<List<RelatedTopic>>) {
                Console.log("REpository", "Completed call, got " + response.body().size + " items")
                if (response.body().isEmpty()) {
                    return
                }

                characterList =  dataTransformer.transform(response.body())
                listener.onDataComplete()
            }

            override fun onFailure(call: Call<List<RelatedTopic>>, t: Throwable) {
                Console.log("REpository", "Call failed")
            }
        }

        networkManager.createCallForAllData().enqueue(callback)

        return characterList
    }

    interface OnCompleteListener {
        fun onDataComplete()
    }
}