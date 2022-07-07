package com.devil.api.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BoardCreate {

    private String title;

    private String content;
}
