
package com.minty.deck.models;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class TweetResponse implements Serializable {

    @SerializedName("statuses")
    @Expose
    private List<Status> statuses;
    private final static long serialVersionUID = 2520188686020529690L;

    /**
     * No args constructor for use in serialization
     */
    public TweetResponse() {
    }

    /**
     * @param statuses
     */
    public TweetResponse(List<Status> statuses) {
        super();
        this.statuses = statuses;
    }

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }

}
