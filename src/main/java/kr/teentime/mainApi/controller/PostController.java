package kr.teentime.mainApi.controller;

import jakarta.websocket.server.PathParam;
import kr.teentime.mainApi.dto.PostUpdateDto;
import kr.teentime.mainApi.dto.PostWriteDto;
import kr.teentime.mainApi.exception.NotFoundClubException;
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

    @GetMapping("/posts/{clubName}")
    public ResponseEntity post(@PageableDefault(page = 0, size = 20) Pageable pageable,
                               @RequestParam(defaultValue = "", required = false) String keyword,
                               @PathVariable("clubName") String clubName) {

        try {
            Page page = postService.pagingPost(pageable, keyword, clubName);

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
        } catch (NotFoundClubException e) {
            return Result.error(e.getMessage(), HttpStatus.NOT_FOUND.value());
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

    @DeleteMapping("/post/delete/{postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Long postId) {
        try {
            postService.deletePost(postId);
            return Result.ok("success");
        } catch (PostNotFoundException e) {
            return Result.error("not a user's post",
                    HttpStatus.FORBIDDEN.value());
        } catch (Exception e) {
            return Result.internalError();
        }
    }
}
