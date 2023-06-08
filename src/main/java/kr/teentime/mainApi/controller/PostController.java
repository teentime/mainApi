package kr.teentime.mainApi.controller;

import jakarta.websocket.server.PathParam;
import kr.teentime.mainApi.dto.PostUpdateDto;
import kr.teentime.mainApi.dto.PostWriteDto;
import kr.teentime.mainApi.exception.PostNotFoundException;
import kr.teentime.mainApi.service.PostService;
import kr.teentime.mainApi.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity post(@PageableDefault(page = 0, size = 20) Pageable pageable,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(defaultValue = "", required = false) List<String> tags) {

        try {
            Page page = postService.pagingPost(pageable, keyword, tags);

            return ResponseEntity.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.internalError();
        }
    }

    @PostMapping("/post/write")
    public ResponseEntity write(@RequestBody PostWriteDto postWriteDto) {
        try {
            postService.writePost(postWriteDto);

            return Result.ok(null);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.internalError();
        }
    }

    @PatchMapping("/post/update")
    public ResponseEntity update(@RequestBody PostUpdateDto updateDto) {
        try {
            postService.updatePost(updateDto);

            return Result.ok(null);
        } catch (PostNotFoundException e) {
            return Result.error(e.getMessage(), HttpStatus.NOT_FOUND.value());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.internalError();
        }
    }
}
