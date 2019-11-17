package com.brandon.harukutsu.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/13/2019
 */
public class Article
{
    private final UUID id;
    private final LocalDateTime timestamp;
    private final String url;
    private final String headline;
    private final String content;
    private final List<KanjiPhrase> kanjiPhrases;
    private final List<KanjiPhrase> headlineKanjiPhrases;

    public Article(UUID id, LocalDateTime timestamp, String url, String headline, String content, List<KanjiPhrase> kanjiPhrases, List<KanjiPhrase> headlineKanjiPhrases)
    {
        this.id = id;
        this.timestamp = timestamp;
        this.url = url;
        this.headline = headline;
        this.content = content;
        this.kanjiPhrases = kanjiPhrases;
        this.headlineKanjiPhrases = headlineKanjiPhrases;
    }

    public UUID getId()
    {
        return id;
    }

    public LocalDateTime getTimestamp()
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

    public List<KanjiPhrase> getKanjiPhrases()
    {
        return kanjiPhrases;
    }

    public List<KanjiPhrase> getHeadlineKanjiPhrases()
    {
        return headlineKanjiPhrases;
    }

    @Override
    public String toString()
    {
        return "Article{" +
            "id=" + id +
            ", timestamp=" + timestamp +
            ", url='" + url + '\'' +
            ", headline='" + headline + '\'' +
            ", content='" + content + '\'' +
            ", kanjiPhrases=" + kanjiPhrases +
            ", headlineKanjiPhrases=" + headlineKanjiPhrases +
            '}';
    }
}
