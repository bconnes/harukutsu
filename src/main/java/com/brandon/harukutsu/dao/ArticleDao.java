package com.brandon.harukutsu.dao;

import com.brandon.harukutsu.model.Article;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/16/2019
 */

public interface ArticleDao
{
    int insertArticle(UUID id, Article article);

    default int insertArticle(Article article)
    {
        UUID id = UUID.randomUUID();
        return insertArticle(id, article);
    }

    List<Article> selectAllArticles();

    Optional<Article> selectArticleById(UUID id);

    int deleteArticleById(UUID id);

    int updateArticleById(UUID id, Article article);
}
