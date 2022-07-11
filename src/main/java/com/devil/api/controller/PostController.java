package com.devil.api.controller;

import com.devil.api.request.PostCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    /**
     * 게시글 등록
     */
    @PostMapping("/posts")
    public Map<String, String> create(@RequestBody @Valid PostCreate params, BindingResult result) {
        // @Valid - param에 빈 값이 들어오면 컨트롤러를 요청하기 전에 스프링에서 검증한다.
        // BindingResult - 에러가 발생하면 그 에러에 관련된 모든 결과를 result에 담는다.
        // - error 필드에 에러에 대한 내용들이 들어있다. ex) 어떤 메세지로 에러를 알려줄 지 등등
        // - 클라이언트 개발자에게 title 에 빈 값이 들어갔다고 알려주고 싶을 때도 사용 가능.

        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField();
            String errorMessage = firstFieldError.getDefaultMessage();

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);

            return error;
        } // 이러한 에러 체크 방법은 모든 매서드마다 해주기엔 번거롭다.
        return Map.of();
    }
}
