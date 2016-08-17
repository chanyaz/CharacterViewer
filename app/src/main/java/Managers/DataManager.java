package Managers;


import com.sumayyah.characterviewer.Views.Console;

import java.util.ArrayList;

import Model.APIResponse;
import Model.Character;
import Model.RelatedTopic;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DataManager {

    private static DataManager instance = null;
    public static synchronized DataManager getInstance() {
        if(instance == null) {
            instance = new DataManager();
            characterList = new ArrayList<>();
        }
        return instance;
    }

    private static ArrayList<Character> characterList = null;
    private String BASE_URL = "http://api.duckduckgo.com/?q=simpsons+characters&format=json";

    public void populateList(APIResponse apiResponse, ListCompleteListener listCompleteListener) {

        Console.log("Populating list");
        for(RelatedTopic relatedTopic: apiResponse.getRelatedTopics()) {
            Character c = new Character(relatedTopic);
            if(!characterList.contains(c)) {
                characterList.add(c);
            }
        }
        listCompleteListener.onListPopulateComplete();
    }

    public interface ListCompleteListener {
        void onListPopulateComplete();
    }

//    public DataManager() {
//        characterList = new ArrayList<>();
//        populate();
//    }

    public ArrayList<Character> getList() {
        return characterList;
    }

    public String getBaseUrl() {
        return BASE_URL;
    }

//    private void populate() {
//
//        for(int i=0; i<15; i++) {
//            Character c = new Character();
//
//            String jsonText = "Barney Gumble  - Barnard \"Barney\" Gumble is a fictional character on the American animated sitcom The Simpsons. The character is voiced by Dan Castellaneta and first appeared in the series premiere episode \"Simpsons Roasting on an Open Fire\".";
//            c.setCharacterNameAndDetails(jsonText);
//            c.setImageURL("https://duckduckgo.com/i/7b1c968b.png");
//
//            characterList.add(c);
//        }
//    }

}
