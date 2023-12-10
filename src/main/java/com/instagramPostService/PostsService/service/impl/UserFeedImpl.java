package com.instagramPostService.PostsService.service.impl;

import com.instagramPostService.PostsService.entity.Posts;
import com.instagramPostService.PostsService.entity.UserFeed;
import com.instagramPostService.PostsService.repository.UserFeedRepository;
import com.instagramPostService.PostsService.service.UserFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserFeedImpl implements UserFeedService {

    @Autowired
    UserFeedRepository userFeedRepository;

    public void addOrUpdateUserFeed(String userId, String postId) {
        Optional<UserFeed> optionalUserFeed = userFeedRepository.findById(userId);

        if (optionalUserFeed.isPresent()) {
            UserFeed userFeed = optionalUserFeed.get();
            userFeed.getPostIds().add(postId);
            userFeedRepository.save(userFeed);
        } else {
            UserFeed newUserFeed = new UserFeed();
            newUserFeed.setUserId(userId);
            newUserFeed.getPostIds().add(postId);
            userFeedRepository.save(newUserFeed);
        }

    }

    public List<String> getListOfPostIds (String userId){
        List<String> postId = userFeedRepository.findById(userId).get().getPostIds();

        return  postId;
    }
}
