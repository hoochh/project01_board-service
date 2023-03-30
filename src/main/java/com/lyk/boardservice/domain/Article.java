package com.lyk.boardservice.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter // Getter 생성
@ToString   //
@Table(indexes = {  // 테이블 매핑, 단일 컬럼에 대한 인덱스 생성
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity // Entity 매핑
public class Article extends AuditingFields {
    @Id // Primary Key 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL의 Autoincrease 는 Identity
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;   // 제목
    @Setter
    @Column(nullable = false, length = 10000)
    private String content; // 본문

    @Setter
    private String hashtag; // 해시태그

    // ArticleComment에 대하여 @OneToMany 연관관계 매핑을 해준다
    // Article에 연관되어 있는 ArticleComments는 중복을 허용하지 않으며 컬렉션으로 구성되어 있게 된다
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @ToString.Exclude // Article과 Article 모두에 존재하는 @String 애노테이션에 의해 순환 참조 발생하여 메모리 이슈 발생할 수 있음 -> Exclude 처리 해준다
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>(); // 곧바로 생성자 호출하여 메모리에 할당

    // 자동 생성, 수정 되어야 하는 부분(생성일시, 수정일시, 생성자, 수정자 등)에 대하여 Auditing 기능 적용 필요

    protected Article() {}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    // 해당 객체를 리스트에 담아 컬랙션으로 사용하고자 할 때
    // 생성, 리스트 및 컬렉션 중복 요소 제거, 정렬 시 비교 필요 -> 동일성, 동등성 검사를 위해 equals&hashCode 구현 필요
    // 엔티티에서는 Lombok을 사용하지 않고 구현해야 한다(전체 필드가 아닌 id(primary key) 만 비교하면 되기 때문에)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;  // o가 Article인지 체크 후 검사
        return id != null && id.equals(article.id);  // 엔티티가 영속화되지 않은 경우 id = null일 수밖에 없다.
        // 따라서 영속화 되지 않은 엔티티는 영속성 검사에서 탈락하게 된다 = 모두 다른 데이터로 취급할 것이다.
        // 또한 id.equals(article.id) 조건에 의해 id 값이 같으면 동일한 데이터로 취급된다.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
