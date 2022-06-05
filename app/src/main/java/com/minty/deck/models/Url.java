
package com.minty.deck.models;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class Url {

    @SerializedName("urls")
    @Expose
    private List<Url__1> urls = null;

    public List<Url__1> getUrls() {
        return urls;
    }

    public void setUrls(List<Url__1> urls) {
        this.urls = urls;
    }

}
