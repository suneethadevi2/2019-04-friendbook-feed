package com.example.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Post;
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
			post.setEmailId("abhilash - " + Integer.toString(i));
			post.setId(Integer.toString(i));
			post.setPostText("this is post number " + i);
			post.setTimeStamp(new Date());
			posts.add(post);
		}
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getFeeds")
	public List<Post> getFeeds(@RequestParam String email) {
		List<Post> collect = posts.stream().filter(post -> post.getEmailId().equalsIgnoreCase(email))
				.collect(Collectors.toList());
		return collect;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getAllFeeds")
	public List<Post> getAllFeeds() {
		List<Post> collect = posts.stream().collect(Collectors.toList());
		return collect;
	}

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/getFeeds")
	public List<Post> getFeeds(@RequestParam final String email,
			@RequestParam(required = false, defaultValue = "0") final int start,
			@RequestParam(required = false, defaultValue = "-1") final int count) {
		return feedService.getPostForUser(email, start, count);
		// final List<Post> collect = posts.stream().filter(post ->
		// post.getEmailId().equalsIgnoreCase(email))
		// .collect(Collectors.toList());
		// return collect;

	}

}
