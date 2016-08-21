
package com.sumayyah.characterviewer.main.Model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class APIResponse {

    @SerializedName("DefinitionSource")
    @Expose
    private String definitionSource;
    @SerializedName("Heading")
    @Expose
    private String heading;
    @SerializedName("ImageWidth")
    @Expose
    private Integer imageWidth;
    @SerializedName("RelatedTopics")
    @Expose
    private List<RelatedTopic> relatedTopics = new ArrayList<RelatedTopic>();
    @SerializedName("Entity")
    @Expose
    private String entity;
    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Redirect")
    @Expose
    private String redirect;
    @SerializedName("DefinitionURL")
    @Expose
    private String definitionURL;
    @SerializedName("AbstractURL")
    @Expose
    private String abstractURL;
    @SerializedName("Definition")
    @Expose
    private String definition;
    @SerializedName("AbstractSource")
    @Expose
    private String abstractSource;
    @SerializedName("Infobox")
    @Expose
    private String infobox;
    @SerializedName("Image")
    @Expose
    private String image;
    @SerializedName("ImageIsLogo")
    @Expose
    private Integer imageIsLogo;
    @SerializedName("Abstract")
    @Expose
    private String _abstract;
    @SerializedName("AbstractText")
    @Expose
    private String abstractText;
    @SerializedName("AnswerType")
    @Expose
    private String answerType;
    @SerializedName("ImageHeight")
    @Expose
    private Integer imageHeight;
    @SerializedName("Answer")
    @Expose
    private String answer;
    @SerializedName("Results")
    @Expose
    private List<Object> results = new ArrayList<Object>();

    /**
     * 
     * @return
     *     The definitionSource
     */
    public String getDefinitionSource() {
        return definitionSource;
    }

    /**
     * 
     * @param definitionSource
     *     The DefinitionSource
     */
    public void setDefinitionSource(String definitionSource) {
        this.definitionSource = definitionSource;
    }

    /**
     * 
     * @return
     *     The heading
     */
    public String getHeading() {
        return heading;
    }

    /**
     * 
     * @param heading
     *     The Heading
     */
    public void setHeading(String heading) {
        this.heading = heading;
    }

    /**
     * 
     * @return
     *     The imageWidth
     */
    public Integer getImageWidth() {
        return imageWidth;
    }

    /**
     * 
     * @param imageWidth
     *     The ImageWidth
     */
    public void setImageWidth(Integer imageWidth) {
        this.imageWidth = imageWidth;
    }

    /**
     * 
     * @return
     *     The relatedTopics
     */
    public List<RelatedTopic> getRelatedTopics() {
        return relatedTopics;
    }

    /**
     * 
     * @param relatedTopics
     *     The RelatedTopics
     */
    public void setRelatedTopics(List<RelatedTopic> relatedTopics) {
        this.relatedTopics = relatedTopics;
    }

    /**
     * 
     * @return
     *     The entity
     */
    public String getEntity() {
        return entity;
    }

    /**
     * 
     * @param entity
     *     The Entity
     */
    public void setEntity(String entity) {
        this.entity = entity;
    }

    /**
     * 
     * @return
     *     The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     * 
     * @param meta
     *     The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The Type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The redirect
     */
    public String getRedirect() {
        return redirect;
    }

    /**
     * 
     * @param redirect
     *     The Redirect
     */
    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    /**
     * 
     * @return
     *     The definitionURL
     */
    public String getDefinitionURL() {
        return definitionURL;
    }

    /**
     * 
     * @param definitionURL
     *     The DefinitionURL
     */
    public void setDefinitionURL(String definitionURL) {
        this.definitionURL = definitionURL;
    }

    /**
     * 
     * @return
     *     The abstractURL
     */
    public String getAbstractURL() {
        return abstractURL;
    }

    /**
     * 
     * @param abstractURL
     *     The AbstractURL
     */
    public void setAbstractURL(String abstractURL) {
        this.abstractURL = abstractURL;
    }

    /**
     * 
     * @return
     *     The definition
     */
    public String getDefinition() {
        return definition;
    }

    /**
     * 
     * @param definition
     *     The Definition
     */
    public void setDefinition(String definition) {
        this.definition = definition;
    }

    /**
     * 
     * @return
     *     The abstractSource
     */
    public String getAbstractSource() {
        return abstractSource;
    }

    /**
     * 
     * @param abstractSource
     *     The AbstractSource
     */
    public void setAbstractSource(String abstractSource) {
        this.abstractSource = abstractSource;
    }

    /**
     * 
     * @return
     *     The infobox
     */
    public String getInfobox() {
        return infobox;
    }

    /**
     * 
     * @param infobox
     *     The Infobox
     */
    public void setInfobox(String infobox) {
        this.infobox = infobox;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The Image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * 
     * @return
     *     The imageIsLogo
     */
    public Integer getImageIsLogo() {
        return imageIsLogo;
    }

    /**
     * 
     * @param imageIsLogo
     *     The ImageIsLogo
     */
    public void setImageIsLogo(Integer imageIsLogo) {
        this.imageIsLogo = imageIsLogo;
    }

    /**
     * 
     * @return
     *     The _abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     * 
     * @param _abstract
     *     The Abstract
     */
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    /**
     * 
     * @return
     *     The abstractText
     */
    public String getAbstractText() {
        return abstractText;
    }

    /**
     * 
     * @param abstractText
     *     The AbstractText
     */
    public void setAbstractText(String abstractText) {
        this.abstractText = abstractText;
    }

    /**
     * 
     * @return
     *     The answerType
     */
    public String getAnswerType() {
        return answerType;
    }

    /**
     * 
     * @param answerType
     *     The AnswerType
     */
    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    /**
     * 
     * @return
     *     The imageHeight
     */
    public Integer getImageHeight() {
        return imageHeight;
    }

    /**
     * 
     * @param imageHeight
     *     The ImageHeight
     */
    public void setImageHeight(Integer imageHeight) {
        this.imageHeight = imageHeight;
    }

    /**
     * 
     * @return
     *     The answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 
     * @param answer
     *     The Answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Object> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The Results
     */
    public void setResults(List<Object> results) {
        this.results = results;
    }

}
