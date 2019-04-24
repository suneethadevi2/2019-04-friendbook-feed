package com.example.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.FeedData;
import com.example.entity.Post;
import com.example.exception.FriendBookFeedException;
import com.example.service.FeedService;

@RestController
@RequestMapping("/feeds/v1")
public class FeedsController {

	@Autowired
	private FeedService feedService;
	
	static List<Post> posts = new ArrayList<>();
	static {
		for (int i = 0; i < 10; i++) {
			Post post = new Post();
			post.setUserMailId("abhilash - " + Integer.toString(i));
			post.setId(Integer.toString(i));
			post.setPostMessage("this is post number " + i);
			post.setLastUpdated(new Date().toString());
			posts.add(post);
		}
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getFeed")
	public FeedData getFeed(@RequestParam String email) {
		
		return null;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getAllFeeds")
	public List<Post> getAllFeeds() {
		List<Post> collect = posts.stream().collect(Collectors.toList());
		return collect;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getFeeds")
	public FeedData getFeeds(@RequestParam final String email,
			@RequestParam(required = false, defaultValue = "0") final int start,
			@RequestParam(required = false, defaultValue = "-1") final int count) {
		try {
			return feedService.getPostForUser(email, start, count);
		} catch (FriendBookFeedException e) {
			return new FeedData(email, new ArrayList<Post>(), new Date(), 0, -1);
		}
		// final List<Post> collect = posts.stream().filter(post ->
		// post.getEmailId().equalsIgnoreCase(email))
		// .collect(Collectors.toList());
		// return collect;

	}

}
