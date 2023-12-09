package com.instagramPostService.PostsService.dto;

import com.instagramPostService.PostsService.entity.Comments;
import com.instagramPostService.PostsService.entity.Likes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class PostDto {
    String postId ;
    String userId;
    String username;
    Date timestamp;
    String datatype;
    String date;
    String caption;
    String category;
    List<Likes> likesOnPost = new ArrayList<>();
    List<Comments> comments = new ArrayList<>();
}
