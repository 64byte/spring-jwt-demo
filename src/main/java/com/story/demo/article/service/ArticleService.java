package com.story.demo.article.service;

import com.story.demo.article.dto.ArticleResponse;
import com.story.demo.article.entity.Article;
import com.story.demo.article.exception.ArticleNotFoundException;
import com.story.demo.article.repository.ArticleRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public ArticleResponse getArticleById(long articleId) throws Exception {
        return ArticleResponse.of(
                articleRepository.findById(articleId)
                        .orElseThrow(ArticleNotFoundException::new));
    }

}
