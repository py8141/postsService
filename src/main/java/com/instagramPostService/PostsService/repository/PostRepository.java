package com.instagramPostService.PostsService.repository;

import com.instagramPostService.PostsService.entity.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Posts,String> {

    List<Posts> findByUserId(String userId);

}
