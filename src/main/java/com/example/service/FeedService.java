package com.example.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.example.entity.FeedData;
import com.example.entity.Post;
import com.example.exception.FriendBookFeedException;

@Service
public class FeedService {

	private final static Map<String, FeedData> cachedFeed = new ConcurrentHashMap<>();
	private final static long TIME_TO_LIVE = 60000; // 1 min

	public FeedData getPostForUser(final String email, final int start, final int count) throws FriendBookFeedException {
		FeedData feedData = cachedFeed.get(email);
		List<Post> retrievedPostsForFeed = new ArrayList<>();
		if (feedData == null || isFeedOld(feedData.getLastUpdated())) {
			try {
				retrievedPostsForFeed = new ArrayList<>(); // TODO make rest call

				if (CollectionUtils.isEmpty(retrievedPostsForFeed)) {
					return cachedFeed.get(email);
				}
				feedData = getFeedData(email, retrievedPostsForFeed, start, count);

				updateCache(email, feedData, start);
			} 
			catch (final Exception e)
            {
                System.out.print(" Exception occured while fetching posts " + e);

                if (feedData != null)
                {
                    return feedData;
                }

                throw new FriendBookFeedException(500, "Couldn't find any posts at this moment. Please try later!!");
            }

		}

		return feedData;
	}

	/**
	 * @param email
	 * @param feedData
	 * @param start
	 */
	private void updateCache(final String email, final FeedData feedData, final int start) {
		if (start == 1) {
			if (cachedFeed.containsKey(email)) {
				// replace cachedFeed for email
				cachedFeed.put(email, feedData);
			}
		} else {
			// Need to check again
		}
	}

	/**
	 * @param email
	 * @param retrievedPostsForFeed
	 * @param start
	 * @param count
	 * @return
	 */
	private FeedData getFeedData(final String email, final List<Post> retrievedPostsForFeed, final int start,
			final int count) {
		return new FeedData(email, retrievedPostsForFeed, new Date(), start, count);
	}

	private boolean isFeedOld(final Date lastUpdated) {
		final Date currentTime = new Date();
		final long timeDiff = currentTime.getTime() - lastUpdated.getTime();
		if (timeDiff > TIME_TO_LIVE) {
			return true;
		}
		return false;
	}

}

