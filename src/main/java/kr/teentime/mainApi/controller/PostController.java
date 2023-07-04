package kr.teentime.mainApi.controller;

import kr.teentime.mainApi.dto.PagingDto;
import kr.teentime.mainApi.dto.post.PostPagingDto;
import kr.teentime.mainApi.dto.post.PostUpdateDto;
import kr.teentime.mainApi.dto.post.PostWriteDto;
import kr.teentime.mainApi.exception.ClubNotFoundException;
import kr.teentime.mainApi.exception.PostNotFoundException;
import kr.teentime.mainApi.service.PostService;
import kr.teentime.mainApi.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/posts/{clubName}")
    public ResponseEntity post(@PageableDefault(page = 0, size = 20) Pageable pageable,
                               @RequestParam(defaultValue = "", required = false) String keyword,
                               @PathVariable("clubName") String clubName) {

        PagingDto<PostPagingDto> page = postService.pagingPost(pageable, keyword, clubName);

        return ResponseEntity.ok(page);
    }

    @PostMapping("/post/write")
    public ResponseEntity write(@RequestBody PostWriteDto postWriteDto) {
        try {
            Long postId = postService.writePost(postWriteDto);

            return Result.ok(postId);
        } catch (ClubNotFoundException e) {
            return Result.error(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @PatchMapping("/post/update")
    public ResponseEntity update(@RequestBody PostUpdateDto updateDto) {
        try {
            postService.updatePost(updateDto);

            return Result.ok(null);
        } catch (PostNotFoundException e) {
            return Result.error(e.getMessage(), HttpStatus.NOT_FOUND.value());
        }
    }

    @DeleteMapping("/post/delete/{postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Long postId) {
        try {
            postService.deletePost(postId);
            return Result.ok("success");
        } catch (PostNotFoundException e) {
            return Result.error("not a user's post",
                    HttpStatus.NOT_FOUND.value());
        } catch (IllegalAccessError e) {
            return Result.error(e.getMessage(),
                    HttpStatus.FORBIDDEN.value());
        }
    }
}
