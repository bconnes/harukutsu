package com.brandon.harukutsu.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author bconnes
 * @since 11/13/2019
 */
@Entity
@Data
@AllArgsConstructor
public class Article extends EntityWithUUID
{
    private final LocalDateTime createdTime;
    private final String url;
    private final String headline;
    private final String content;

    @OneToMany
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_article_to_article_phrases"))
    private final List<KanjiPhrase> articleKanjiPhrases;

    @OneToMany
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_article_to_headline_phrases"))
    private final List<KanjiPhrase> headlineKanjiPhrases;

    @Override
    public String toString()
    {
        return "Article{" +
            ", createdTime=" + createdTime +
            ", url='" + url + '\'' +
            ", headline='" + headline + '\'' +
            ", content='" + content + '\'' +
            ", articleKanjiPhrases=" + articleKanjiPhrases +
            ", headlineKanjiPhrases=" + headlineKanjiPhrases +
            '}';
    }
}
