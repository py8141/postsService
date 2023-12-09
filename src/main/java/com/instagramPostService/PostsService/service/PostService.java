package com.instagramPostService.PostsService.service;

import com.instagramPostService.PostsService.entity.Comments;
import com.instagramPostService.PostsService.entity.Likes;
import com.instagramPostService.PostsService.entity.Posts;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostService {

    List<Posts> findPostByUserId(String userId);
    boolean addLikeInPost(String postId, Likes like);
    boolean removeLikeInPost(String postId, Likes like);
    boolean addCommentOnAPost(String postId, Comments comment);
    boolean addOrSave(Posts post);
}
