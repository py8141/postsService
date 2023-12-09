package com.instagramPostService.PostsService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "UserFeed")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserFeed {
    String userId;
    List<String> postIds;
}
