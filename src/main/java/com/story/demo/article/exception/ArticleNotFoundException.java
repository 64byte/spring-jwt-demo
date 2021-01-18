package com.story.demo.article.exception;

public class ArticleNotFoundException extends Exception{

    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
