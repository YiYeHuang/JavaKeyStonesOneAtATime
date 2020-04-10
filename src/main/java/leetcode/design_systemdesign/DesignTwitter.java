package leetcode.design_systemdesign;

import leetcode.tag.type.Design;
import leetcode.tag.type.HashTableTag;
import leetcode.tag.type.Heap;

import java.util.*;

/*
355. Design Twitter

Design a simplified version of Twitter where users can post tweets,
follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed.
Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
Each item in the news feed must be posted by users who the user followed or by the user herself.
Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
 */

@Design
@Heap
@HashTableTag
public class DesignTwitter {
    private static int timestamp = 0;

    static class Tweet {
        public int id;
        public int time;
        public Tweet next;

        public Tweet(int id) {
            this.id = id;
            timestamp++;
            this.time = timestamp;
        }
    }

    static class User {
        public int id;
        public Set<Integer> following;
        public Tweet userTweets;

        public User(int id) {
            this.id = id;
            this.following = new HashSet<>();
            follow(id);
        }

        public void post(int tweetId) {
            Tweet t = new Tweet(id);
            t.next=userTweets;
            userTweets=t;
        }

        public void follow(int other) {
            following.add(other);
        }

        public void unfollow(int other) {
            following.remove(other);
        }
    }

    Map<Integer, User> userMap = new HashMap<>();

    /** Initialize your data structure here. */
    public DesignTwitter() {

    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            User newUser = new User(userId);
            newUser.post(tweetId);
            userMap.put(userId, newUser);
        } else {
            userMap.get(userId).post(tweetId);
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new LinkedList<>();

        if (!userMap.containsKey(userId)) return result;

        PriorityQueue<Tweet> tweetsHeap = new PriorityQueue<>((a, b) -> b.time - a.time);
        for (int following : userMap.get(userId).following) {
            Tweet t = userMap.get(following).userTweets;
            if (t != null) {
                tweetsHeap.add(t);
            }
        }

        int number = 0;
        while (!tweetsHeap.isEmpty() && number <10) {
            Tweet t = tweetsHeap.poll();
            result.add(t.id);
            if (t.next != null) {
                tweetsHeap.add(t.next);
            }
            number++;
        }

        return result;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            User follower = new User(followerId);
            userMap.put(followerId, follower);
        }
        if (!userMap.containsKey(followeeId)) {
            User followee = new User(followeeId);
            userMap.put(followeeId, followee);
        }

        userMap.get(followerId).follow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId) || followerId==followeeId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }

    public static void main(String[] args) {
        DesignTwitter t = new DesignTwitter();
        t.postTweet(1, 5);
        t.postTweet(1, 3);
        t.getNewsFeed(1);
    }
}
