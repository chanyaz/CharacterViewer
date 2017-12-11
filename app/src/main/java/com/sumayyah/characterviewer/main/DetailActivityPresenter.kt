package com.sumayyah.characterviewer.main

import com.sumayyah.characterviewer.main.Data.CharacterRepository
import com.sumayyah.characterviewer.main.Model.Character

/**
 * Created by sahmed336 on 11/29/17.
 */
class DetailActivityPresenter(val repository: CharacterRepository, val view: DetailActivityView) {

    fun loadCharacterData(position: Int) {
        view.loadCharacterData(repository.getAllCharacters()[position])
    }
 }