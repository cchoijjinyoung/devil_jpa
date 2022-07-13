package com.devil.api.response;

import lombok.Builder;
import lombok.Getter;

/**
 * 서비스 정책에 맞는 클래스를 따로 만들어준다. response
 * Entity가 노출되지 않게 껍질을 씌워주는 느낌.
 */

@Getter
@Builder
public class PostResponse {

    private Long id;

    private String title;

    private String content;

}
