package com.instagramPostService.PostsService.repository;

import com.instagramPostService.PostsService.entity.Posts;
import com.instagramPostService.PostsService.entity.UserFeed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFeedRepository extends MongoRepository<UserFeed,String> {
}
