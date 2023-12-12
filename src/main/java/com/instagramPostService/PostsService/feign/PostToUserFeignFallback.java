package com.instagramPostService.PostsService.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostToUserFeignFallback implements FallbackFactory<PostsToUserFeign> {
    @Override
    public PostsToUserFeign create(Throwable throwable) {
        return new PostsToUserFeign() {

            @Override
            public ResponseEntity<List<String>> getUserFollowers(String userId) {
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
            }
        };
    }
}
