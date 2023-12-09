package com.instagramPostService.PostsService.service.impl;

import com.instagramPostService.PostsService.entity.Comments;
import com.instagramPostService.PostsService.entity.Likes;
import com.instagramPostService.PostsService.entity.Posts;
import com.instagramPostService.PostsService.entity.UserFeed;
import com.instagramPostService.PostsService.feign.PostsToUserFeign;
import com.instagramPostService.PostsService.repository.PostRepository;
import com.instagramPostService.PostsService.repository.UserFeedRepository;
import com.instagramPostService.PostsService.service.PostService;
import com.instagramPostService.PostsService.service.UserFeedService;
import net.bytebuddy.build.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostsToUserFeign postsToUserFeign;


    @Autowired
    UserFeedService userFeedService;

    @Override
    public List<Posts> findPostByUserId(String userId) {
        List<Posts> posts = postRepository.findByUserId(userId);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found for user with ID: " + userId);
        }

        return posts;
    }

    @Override
    public boolean addLikeInPost(String postId, Likes like) {
        Optional<Posts> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Posts post = optionalPost.get();
            post.getLikesOnPost().add(like);
            postRepository.save(post);
            return true;
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }

    @Override
    public boolean removeLikeInPost(String postId, Likes like) {
        Optional<Posts> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Posts post = optionalPost.get();
            post.getLikesOnPost().remove(like);
            postRepository.save(post);
            return true;
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }
    @Override
    public boolean addCommentOnAPost(String postId, Comments comment) {
        Optional<Posts> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Posts post = optionalPost.get();
            post.getComments().add(comment);
            postRepository.save(post);
            return true;
        } else {
            throw new PostNotFoundException("Post not found with ID: " + postId);
        }
    }

    @Override
    public boolean addOrSave(Posts post) {
        try {
            if (post.getUserId() == null || post.getDatatype() == null) {
                throw new IllegalArgumentException("UserId and datatype are required.");
            }

            Posts savedPost = postRepository.save(post);
            List<String> userIds = postsToUserFeign.fetchFollowingList(post.getUserId()).getBody();
            for(String userId : userIds){
                userFeedService.addOrUpdateUserFeed(userId,savedPost.getPostId());
            }

            return savedPost != null;
        } catch (IllegalArgumentException e) {
            System.out.println("Validation error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
            return false;
        }
    }
}
