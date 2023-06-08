package kr.teentime.mainApi.exception;

import lombok.Getter;

@Getter
public class PostNotFoundException extends Throwable {
    private final String message = "post not found";
}
