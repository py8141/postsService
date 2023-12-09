package com.instagramPostService.PostsService.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "posts-to-user-feign" , url = "http://localhost:8099" , fallbackFactory =  PostToUserFeignFallback.class)

public interface PostsToUserFeign {

    @RequestMapping(method = RequestMethod.GET , value = "/getUserFollowers")
    ResponseEntity<List<String>> fetchFollowingList(@RequestParam String userId);
}

