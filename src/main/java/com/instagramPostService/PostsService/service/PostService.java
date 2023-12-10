package com.instagramPostService.PostsService.service;

import com.instagramPostService.PostsService.entity.Comments;
import com.instagramPostService.PostsService.entity.Likes;
import com.instagramPostService.PostsService.entity.Posts;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    List<Posts> findByPostIdIn(List<String> postId);
    List<Posts> findPostByUserId(String userId);
    boolean toggleLikeInPost(String postId, Likes like);
    boolean addCommentOnAPost(String postId, Comments comment);
    boolean addOrSave(Posts post);
}
