package com.instagramPostService.PostsService.controller;

import com.instagramPostService.PostsService.entity.Posts;
import com.instagramPostService.PostsService.entity.UserFeed;
import com.instagramPostService.PostsService.service.PostService;
import com.instagramPostService.PostsService.service.UserFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class UserFeedController {

    @Autowired
    UserFeedService userFeedService;

    @Autowired
    PostService postService;

    @GetMapping("/byUserId/{userId}")
    ResponseEntity<List<Posts>> getPostbyUserId (@PathVariable String userId){
        List<String> postsForUsr =  userFeedService.getListOfPostIds(userId);
        List<Posts> posts = postService.findByPostIdIn(postsForUsr);
        return ResponseEntity.ok(posts);
    }

}
