package com.story.demo.article.dto;

import com.story.demo.article.entity.Article;
import com.story.demo.article.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ArticleResponse implements Serializable {

    private String title;

    private String content;

    public static ArticleResponse of(Article article) {
        return new ArticleResponse(article.getTitle(), article.getContent());
    }
}
