package com.instagramPostService.PostsService.dto;

import com.instagramPostService.PostsService.entity.Comments;
import com.instagramPostService.PostsService.entity.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    String postId ;
    String userId;
    String username;
    String userEmail;
    Date timestamp;
    String datatype;
    String data;
    String caption;
    String category;
    List<Likes> likesOnPost = new ArrayList<>();
    List<Comments> comments = new ArrayList<>();
}
