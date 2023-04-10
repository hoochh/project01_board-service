package com.lyk.boardservice.service;

import com.lyk.boardservice.domain.ArticleComment;
import com.lyk.boardservice.dto.ArticleCommentDto;
import com.lyk.boardservice.repository.ArticleCommentRepository;
import com.lyk.boardservice.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComment(Long articleId) {
        return List.of();
    }

    public void saveArticleComment(ArticleCommentDto dto) {
    }

}