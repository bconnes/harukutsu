package com.brandon.harukutsu.api;

import com.brandon.harukutsu.Kijiyomi;
import com.brandon.harukutsu.model.Article;
import com.brandon.harukutsu.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/16/2019
 */
@RequestMapping("api/v1/article")
@RestController
public class ArticleController
{
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService)
    {
        this.articleService = articleService;
    }

    @PostMapping
    public int addArticle(@RequestBody Article article)
    {
        return articleService.addArticle(article);
    }

    @GetMapping
    public List<Article> getAllPeople()
    {
        Kijiyomi kj = new Kijiyomi();
        return kj.readArticles();
//        return articleService.getAllArticles();
    }

    @GetMapping(path = "{id}")
    public Article getArticleById(@PathVariable("id") UUID id)
    {
        return articleService.getArticleById(id)
            .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public int deleteArticleById(@PathVariable("id") UUID id)
    {
        return articleService.deleteArticle(id);
    }

    @PutMapping(path = "{id}")
    public int updateArticleById(@PathVariable("id") UUID id, @RequestBody Article article)
    {
        return articleService.updateArticle(id, article);
    }
}
