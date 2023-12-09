package com.instagramPostService.PostsService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    String userId;
    String postId;
    Data timestamp;
    String comment;
}
