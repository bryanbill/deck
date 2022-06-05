
package com.minty.deck.models;

import java.io.Serializable;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Entities__1 implements Serializable
{

    @SerializedName("description")
    @Expose
    private Description description;
    private final static long serialVersionUID = -4733799715567013123L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Entities__1() {
    }

    /**
     * 
     * @param description
     */
    public Entities__1(Description description) {
        super();
        this.description = description;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

}
