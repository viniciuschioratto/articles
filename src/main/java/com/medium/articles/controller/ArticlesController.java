package com.medium.articles.controller;

import com.medium.articles.entity.Articles;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/articles")
public class ArticlesController {

    @PostMapping
    public ResponseEntity<Articles> createArticle(@RequestBody Articles articles) {
        log.info("Article to be created: {}.", articles.toString());

        return ResponseEntity.ok(articles);
    }

    @GetMapping("{id}")
    public ResponseEntity<Articles> getAnArticleById(@PathVariable("id") UUID id) {
        log.info("Get an article by id: {}.", id);

        Articles articles = Articles.builder()
                .id(id)
                .title("Spring boot application")
                .description("This article is about how to use spring boot.")
                .build();

        return ResponseEntity.ok(articles);
    }

    @PutMapping("{id}")
    public ResponseEntity<Articles> updateArticle(@PathVariable("id") UUID id, @RequestBody Articles articles) {
        log.info("Article to be updated: {}.", articles.toString());
        log.info("Id about article to be updated: {}.", id);

        return ResponseEntity.ok(articles);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAnArticleById(@PathVariable("id") UUID id) {
        log.info("Article id to be deleted: {}.", id);

        return ResponseEntity.ok("Article deleted");
    }
}
