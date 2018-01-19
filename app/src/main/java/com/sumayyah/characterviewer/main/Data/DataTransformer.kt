package com.sumayyah.characterviewer.main.Data

import com.sumayyah.characterviewer.main.Model.Character
import com.sumayyah.characterviewer.main.Model.RelatedTopic

/**
 * Created by sahmed014c on 11/17/17.
 */
//TODO singleton
class DataTransformer {

    fun transform(relatedTopicList: List<RelatedTopic>) : ArrayList<Character> {
        var characterList = ArrayList<Character>()

        relatedTopicList
                .map { Character(it) }
                .filterNot { characterList.contains(it) }
                .forEach { characterList.add(it) }

        return characterList
    }
}