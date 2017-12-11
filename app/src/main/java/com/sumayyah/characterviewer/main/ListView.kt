package com.sumayyah.characterviewer.main

import com.sumayyah.characterviewer.main.Model.Character

/**
 * Created by sumayyah on 11/29/17.
 */
interface ListView {

    fun showEmptyList()
    fun loadListData(characters: ArrayList<Character>)
}