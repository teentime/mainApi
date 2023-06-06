package kr.teentime.mainApi.controller;

import kr.teentime.mainApi.service.PostService;
import kr.teentime.mainApi.util.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/oijwd")
    public ResponseEntity test() {
        return Result.ok("wasd");
    }

    @GetMapping("/posts")
    public ResponseEntity post(@PageableDefault(page = 0, size = 20) Pageable pageable,
                               @RequestParam(required = false) String keyword,
                               @RequestParam(defaultValue = "", required = false) List<String> tags) {

        try {
            Page page = postService.pagingPost(pageable, keyword, tags);

            return Result.ok("aaaa");
//            return ResponseEntity.ok(page);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.internalError();
        }
    }
}
