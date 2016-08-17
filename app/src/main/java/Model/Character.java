package Model;

/**
 * Created by sumayyah on 8/10/16.
 */
public class Character {

    private String name;
    private String description;
    private String imageURL;

    public Character() {}
    public Character(RelatedTopic relatedTopic) {
        setCharacterNameAndDetails(relatedTopic.getText());
        setImageURL(relatedTopic.getIcon().getURL());
    }

    /** Getters */
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    /** Setters */
    public void setCharacterNameAndDetails(String jsonText) {
        parseJSONText(jsonText);
    }

    public void setImageURL(String jsonURL) {
        imageURL = jsonURL;
    }

    /** Helper methods */
    private void parseJSONText(String JSONText) {
        String array[] = JSONText.split("-", 2);

        String name = array[0];
        String desc = array[1];

        this.name = name;
        this.description = desc;
    }

    @Override
    public String toString() {
        return "Character: [ Name: "+ name +", Desc: "+ description +", URL: "+ imageURL+"]";
    }
}
