package com.example.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.example.entity.FeedData;
import com.example.entity.Post;

@Service
public class FeedService {

	private final static Map<String, FeedData> cachedFeed = new ConcurrentHashMap<>();
	
	public List<Post> getPostForUser(String email)
	{
		List<Post> retrievedPostsForFeed = null;
		if(!cachedFeed.containsKey(email))
		{
			//TODO make rest call
		}
		else
		{
			FeedData feedData = cachedFeed.get(email);
			if(isFeedOld(feedData.getLastUpdated()))
			{
				//TODO make rest call
			}
			else
			{
				retrievedPostsForFeed = feedData.getPost();
			}
		}
		return retrievedPostsForFeed;
	}
	private boolean isFeedOld(Date lastUpdated) {
		return false;
	}
}
