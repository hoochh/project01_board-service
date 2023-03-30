package com.lyk.boardservice.repository;

import com.lyk.boardservice.domain.Article;
import com.lyk.boardservice.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        // 선택적인 필드에 대한 검색 기능 작성
        // listing 하지 않은 프로퍼티는 검색에서 제외 한다: true
        bindings.excludeUnlistedProperties(true);
        // 원하는 필드를 추가
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        // Exact Match 룰 변경, 대소문자 구분 하지 않고 검색
        bindings.bind(root.title).first((StringExpression::containsIgnoreCase));
        bindings.bind(root.content).first((StringExpression::containsIgnoreCase));
        bindings.bind(root.hashtag).first((StringExpression::containsIgnoreCase));
        bindings.bind(root.createdAt).first((DateTimeExpression::eq));
        bindings.bind(root.createdBy).first((StringExpression::containsIgnoreCase));
    };
}
