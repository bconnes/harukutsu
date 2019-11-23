package com.brandon.harukutsu.dao.impl;

import com.brandon.harukutsu.dao.ArticleDao;
import com.brandon.harukutsu.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/16/2019
 */
@Repository("article")
public class ArticleDaoImpl implements ArticleDao
{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ArticleDaoImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertArticle(UUID id, Article article)
    {
//        jdbcTemplate.getDataSource().connec
        final String sql = "INSERT INTO Article (id, created_time, url, headline, content) VALUES (?, ?)";

        Object[] params = new Object[]{id, ""}; // todo
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public List<Article> selectAllArticles()
    {
        final String sql = "SELECT id, name FROM Article";

        return jdbcTemplate.query(sql, (resultSet, i) -> {
            UUID id = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return null; // todo
        });
    }

    @Override
    public Optional<Article> selectArticleById(UUID id)
    {
        final String sql = "SELECT id, name FROM Article WHERE id = ?";

        Article article = jdbcTemplate.queryForObject(sql, new Object[]{id}, (resultSet, i) -> {
            UUID articleId = UUID.fromString(resultSet.getString("id"));
            String name = resultSet.getString("name");
            return null; // todo
        });

        return Optional.ofNullable(article);
    }

    @Override
    public int deleteArticleById(UUID id)
    {
        final String sql = "DELETE FROM Article WHERE id = ?";

        Object[] params = new Object[]{id};
        return jdbcTemplate.update(sql, params);
    }

    @Override
    public int updateArticleById(UUID id, Article article)
    {
        final String sql = "UPDATE Article SET name = ? WHERE id = ?";

        Object[] params = new Object[]{"", id}; // todo
        return jdbcTemplate.update(sql, params);
    }

    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }
}
