package com.instagramPostService.PostsService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Document(collection = "Posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Posts {
    @Id
    String postId = UUID.randomUUID().toString();;
    String userId;
    String username;
    Date timestamp;
    String datatype;
    String data;
    String caption;
    String category;
    List<Likes> likesOnPost = new ArrayList<>();
    List<Comments> comments = new ArrayList<>();

}
