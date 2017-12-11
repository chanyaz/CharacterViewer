package com.sumayyah.characterviewer.main

import com.sumayyah.characterviewer.main.Data.CharacterRepository

/**
 * Created by sumayyah on 11/22/17.
 */
class MainPresenter(val listView: ListView, val detailView: DetailView, val repository: CharacterRepository) {

    fun initViews() {
        Console.log("Sumi", "Main presenter shownig empty views")
        listView.showEmptyList()
        detailView.showEmptyDetailView()
    }

    fun onResume() {
        Console.log("Sumi", "Main presenter on resume, loading data")

        listView.loadListData(repository.getAllCharacters())
        detailView.showDetailForCharacter(repository.getAllCharacters()[0])
    }

    fun onPause() {
        //Pause data loading, cancel threads, etc
    }

    fun onItemClicked(position: Int) {
        Console.log("Sumi", "Main presenter on itemclicked" )

        detailView.showDetailForCharacter(repository.getAllCharacters()[position])
    }
}