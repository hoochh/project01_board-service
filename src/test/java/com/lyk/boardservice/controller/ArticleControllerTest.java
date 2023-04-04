package com.lyk.boardservice.controller;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {
    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) {
        this.mvc = mvc;
    }

    @DisplayName("[view][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingNothingArticlesView_thenReturnsArticlesView() throws Exception {
        // Given

        // When & then
        mvc.perform(get("/articles"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/index")) // 뷰 이름 검사 테스트 추가
                .andExpect(MockMvcResultMatchers.model().attributeExists("articles")); // model에 name에 해당하는 key 값으로 전달된 attribute가 존재하는지 체크
    }

    //@Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 상세 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingNothingArticleView_thenReturnsArticleView() throws Exception {
        // Given

        // When & then
        mvc.perform(get("/articles/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/detail"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("article")) // model에 name에 해당하는 key 값으로 전달된 attribute가 존재하는지 체크
                .andExpect(MockMvcResultMatchers.model().attributeExists("articleComments"));
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 검색 전용 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingNothingArticleSearchView_thenReturnsArticleSearchView() throws Exception {
        // Given

        // When & then
        mvc.perform(get("/articles/search"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search"));
    }

    @Disabled("구현 중")
    @DisplayName("[view][GET] 게시글 해시태그 검색 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingNothingArticleHashtagSearchView_thenReturnsArticleHashtagSearchView() throws Exception {
        // Given

        // When & then
        mvc.perform(get("/articles/search-hashtag"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
                .andExpect(view().name("articles/search-hashtag"));
    }
}
