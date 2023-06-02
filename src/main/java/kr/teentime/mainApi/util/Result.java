package kr.teentime.mainApi.util;

import lombok.*;
import org.springframework.http.ResponseEntity;

@Data @Builder
@AllArgsConstructor
public class Result<T> {

    private T data;
    private boolean hasError;

    public static ResponseEntity<Result<Object>> internalError() {
        return ResponseEntity.internalServerError()
                .body(Result.builder()
                        .data(null)
                        .hasError(true)
                        .build());
    }

    public static <T> ResponseEntity<Result<Object>> ok(T data) {
        return ResponseEntity.ok()
                .body(Result.builder()
                        .data(data)
                        .hasError(false)
                        .build());
    }

    public static <T> ResponseEntity<Result<Object>> error(T data, int status) {
        return ResponseEntity.status(status)
                .body(Result.builder()
                .data(data)
                .hasError(true)
                .build());
    }
}
