package Data;

/**
 * Created by sumayyah on 8/10/16.
 */
public class Character {

    private String characterName;
    private String characterDescription;
    private String characterPicURL;


    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterNameAndDetails(String jsonText) {
        parseJSONText(jsonText);
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterPicURL(String urlFromJson) {
        characterPicURL = urlFromJson;
    }

    public String getCharacterPicURL() {
        return characterPicURL;
    }

    private void parseJSONText(String JSONText) {
        String array[] = JSONText.split("-", 2);

        String name = array[0];
        String desc = array[1];

        this.characterName = name;
        this.characterDescription = desc;
    }
}
