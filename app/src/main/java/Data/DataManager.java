package Data;


import java.util.ArrayList;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DataManager {

    private static ArrayList<Character> characterList = null;

    public DataManager() {
        characterList = new ArrayList<>();
        populate();
    }

    public ArrayList<Character> getList() {
        return characterList;
    }

    private void populate() {

        for(int i=0; i<15; i++) {
            Character c = new Character();

            String jsonText = "Barney Gumble  - Barnard \"Barney\" Gumble is a fictional character on the American animated sitcom The Simpsons. The character is voiced by Dan Castellaneta and first appeared in the series premiere episode \"Simpsons Roasting on an Open Fire\".";

            c.setCharacterNameAndDetails(jsonText);
            c.setCharacterPicURL("https://duckduckgo.com/i/7b1c968b.png");

            characterList.add(c);
        }
    }
}
