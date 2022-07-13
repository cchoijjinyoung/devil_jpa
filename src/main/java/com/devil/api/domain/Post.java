package com.devil.api.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob // db 에서는 long text 형태로 저장되기 위함.
    private String content;

    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
