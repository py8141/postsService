package com.instagramPostService.PostsService.service;

import java.util.List;

public interface UserFeedService {
    public void addOrUpdateUserFeed(String userId, String postId);
    public List<String> getListOfPostIds (String userId);
}
