package com.instagramPostService.PostsService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;


@Document(collection = "UserFeed")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFeed {
    @Id
    @MongoId
    String userId;
    List<String> postIds = new ArrayList<>();
}
