package com.devil.api.request;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@ToString
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.") // 스프링에서 데이터를 검증해준다. (@Valid가 붙은 파라미터)
    private String title;

    @NotBlank
    private String content;
}
