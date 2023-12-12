package com.instagramPostService.PostsService.controller;

import com.instagramPostService.PostsService.dto.ActivityDto;
import com.instagramPostService.PostsService.dto.PostDto;
import com.instagramPostService.PostsService.entity.Comments;
import com.instagramPostService.PostsService.entity.Likes;
import com.instagramPostService.PostsService.entity.Posts;
import com.instagramPostService.PostsService.service.PostService;
import com.instagramPostService.PostsService.service.impl.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/insta/posts")
public class PostsController {

    @Autowired
    private PostService postService;


    @GetMapping("/user/{userId}")
    public List<Posts> findPostByUserId(@PathVariable String userId) {
        List<Posts> posts = postService.findPostByUserId(userId);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found for user with ID: " + userId);
        }

        return posts;
    }

//    @GetMapping("/getActivity")
//    public ResponseEntity<List<ActivityDto>> getActivityOfUser (@RequestParam String userId){
//        List<Posts> posts = postService.findPostByUserId(userId);
//    }

    @PutMapping("/like/{postId}")
    public ResponseEntity<String> addLikeInPost(@PathVariable String postId, @RequestBody Likes like) {
        postService.toggleLikeInPost(postId, like);
        return ResponseEntity.ok("Like added successfully to post with ID: " + postId);
    }


    @PutMapping("/comment/{postId}")
    public ResponseEntity<String> addCommentOnAPost(@PathVariable String postId, @RequestBody Comments comment) {

        boolean commentAdded = postService.addCommentOnAPost(postId, comment);

        if (commentAdded) {
            return ResponseEntity.ok("Comment added successfully to post with ID: " + postId);
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }

    @PostMapping("/addOrSave")
    public ResponseEntity<String> addOrSavePost(@RequestBody PostDto postDto) {
        try {
            if (isInvalid(postDto )) {
                throw new IllegalArgumentException("UserId and datatype are required.");
            }

            boolean isPostAddedOrSaved = postService.addOrSave(convertToPosts(postDto));

            if (isPostAddedOrSaved) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Post added or saved successfully.");
            } else {
                return ResponseEntity.badRequest().body("Failed to add or save the post.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Validation error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: " + e.getMessage());
        }
    }

    private boolean isInvalid(PostDto postDto) {
        return postDto == null || postDto.getUserId() == null || postDto.getDatatype() == null;
    }

    private Posts convertToPosts(PostDto postDto) {
        Posts post = new Posts();
        post.setPostId(postDto.getUserId());
        post.setUserId(postDto.getUserId());
        post.setUserEmail(postDto.getUserEmail());
        post.setUsername(postDto.getUsername());
        post.setTimestamp(postDto.getTimestamp());
        post.setDatatype(postDto.getDatatype());
        post.setData(postDto.getData());
        post.setCaption(postDto.getCaption());
        post.setCategory(postDto.getCategory());
        return post;
    }

}