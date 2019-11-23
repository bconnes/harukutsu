package com.brandon.harukutsu.service;

import com.brandon.harukutsu.dao.ArticleDao;
import com.brandon.harukutsu.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/16/2019
 */
@Service
public class ArticleService
{
    private final ArticleDao articleDao;

    @Autowired
    public ArticleService(@Qualifier("article") ArticleDao articleDao)
    {
        this.articleDao = articleDao;
    }

    public int addArticle(Article article)
    {
        return articleDao.insertArticle(article);
    }

    public List<Article> getAllArticles()
    {
        return articleDao.selectAllArticles();
    }

    public Optional<Article> getArticleById(UUID id)
    {
        return articleDao.selectArticleById(id);
    }

    public int deleteArticle(UUID id)
    {
        return articleDao.deleteArticleById(id);
    }

    public int updateArticle(UUID id, Article article)
    {
        return articleDao.updateArticleById(id, article);
    }
}
