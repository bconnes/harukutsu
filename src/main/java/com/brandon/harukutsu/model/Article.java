package com.brandon.harukutsu.model;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/13/2019
 */
public class Article
{
    private final UUID id;
    private final Timestamp timestamp;
    private final String url;
    private final String headline;
    private final String content;

    public Article(UUID id, Timestamp timestamp, String url, String headline, String content)
    {
        this.id = id;
        this.timestamp = timestamp;
        this.url = url;
        this.headline = headline;
        this.content = content;
    }

    public UUID getId()
    {
        return id;
    }

    public Timestamp getTimestamp()
    {
        return timestamp;
    }

    public String getUrl()
    {
        return url;
    }

    public String getHeadline()
    {
        return headline;
    }

    public String getContent()
    {
        return content;
    }
}
