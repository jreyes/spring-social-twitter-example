package org.springframework.social.quickstart.model;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.quickstart.util.JsonDateConverter;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Retweet
{
// ------------------------------ FIELDS ------------------------------

    private int count;

    private Date createdAt;

    private long id;

    private String text;

// --------------------------- CONSTRUCTORS ---------------------------

    @JsonCreator
    public Retweet( @JsonProperty("id") long id, @JsonProperty("text") String text,
                    @JsonProperty("created_at") String createdAt, @JsonProperty("retweet_count") int retweetCount )
    {
        this.id = id;
        this.text = text;
        this.count = retweetCount;
        this.createdAt = JsonDateConverter.toDate( createdAt );
    }

// --------------------- GETTER / SETTER METHODS ---------------------

    public int getCount()
    {
        return count;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public long getId()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }
}
