package com.aks.gradle.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.aks.gradle.utils.JUtils;

public class LearningCompletableFutures {

	static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
	static final String COMMENTS_URL = "https://jsonplaceholder.typicode.com/comments";
	static final String ALBUM_URLS = "https://jsonplaceholder.typicode.com/albums";

	public static enum Responses {
		Posts, Comments, Albums
	}

	public static interface JResponse {
		Responses responses();
	}

	/**
	 * {@link Albums}
	 * 
	 * @author atul_sharma
	 *
	 */

	public static class Albums implements JResponse {

		int userId;
		int id;
		String title;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@Override
		public Responses responses() {
			return Responses.Albums;
		}

		@Override
		public String toString() {
			return "Albums [userId=" + userId + ", id=" + id + ", title=" + title + "]";
		}

	}

	/**
	 * {@link Comments}
	 * 
	 * @author atul_sharma
	 *
	 */
	public static class Comments implements JResponse {
		int postId;
		int id;
		String name;
		String email;
		String body;

		public int getPostId() {
			return postId;
		}

		public void setPostId(int postId) {
			this.postId = postId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		@Override
		public Responses responses() {
			return Responses.Comments;
		}

		@Override
		public String toString() {
			return "Comments [postId=" + postId + ", id=" + id + ", name=" + name + ", email=" + email + ", body=" + body + "]";
		}

	}

	/**
	 * 
	 * @author atul_sharma
	 *
	 */
	public static class Posts implements JResponse {
		int userId;
		int id;
		String title;
		String body;

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		@Override
		public Responses responses() {
			return Responses.Posts;
		}

		@Override
		public String toString() {
			return "Posts [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
		}

	}

	public static class ApiResponseCache {

		List<Posts> posts = new LinkedList<>();
		List<Comments> comments = new LinkedList<>();
		List<Albums> albums = new LinkedList<>();

		public List<Posts> getPosts() {
			return posts;
		}

		public ApiResponseCache setPosts(List<Posts> posts) {
			this.posts = posts;
			return this;
		}

		public List<Comments> getComments() {
			return comments;
		}

		public ApiResponseCache setComments(List<Comments> comments) {
			this.comments = comments;
			return this;
		}

		public List<Albums> getAlbums() {
			return albums;
		}

		public ApiResponseCache setAlbums(List<Albums> albums) {
			this.albums = albums;
			return this;
		}

		@Override
		public String toString() {
			return "ApiResponseCache [posts=" + posts.size() + ", comments=" + comments.size() + ", albums=" + albums.size() + "]";
		}

	}

	/**
	 * main program
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		System.out.println("===========================================");
		CompletableFuture<String> c1 = CompletableFuture.completedFuture("Completed ..");
		System.out.println(c1.isDone());
		System.out.println(c1.get());
		System.out.println("===========================================");
		System.out.println("Running the posts using CF");
		ApiResponseCache cache = new ApiResponseCache();
		CompletableFuture<?> cf = CompletableFuture.completedFuture(getPostsAsync()).thenAcceptAsync(p -> setPostsSilently(cache, p));
		cf.join();
		System.out.println(cache);
		System.out.println("Running the posts/comments/albums in parallel and using the results");
		CompletableFuture<?> cf2 = CompletableFuture.allOf(
		        CompletableFuture.completedFuture(getPostsAsync()).thenAcceptAsync(p -> setPostsSilently(cache, p)),
		        CompletableFuture.completedFuture(getAlbumsAsync()).thenAcceptAsync(p -> setAlbumsSilently(cache, p)),
		        CompletableFuture.completedFuture(getCommentsAsync()).thenAcceptAsync(p -> setCommentsSilently(cache, p)));
		cf2.join();
		System.out.println(cache);

		System.out.println("Combine and compose");
		CompletableFuture<?> cf3 = CompletableFuture.supplyAsync(() -> "Hello").thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
		cf3.join();
		System.out.println(cf3.get());
		Object obj = CompletableFuture.supplyAsync(() -> {
			throw new RuntimeException("1");
		}).whenComplete((i, err) -> {
			System.out.println("hello");
			throw new RuntimeException("2" + err.getMessage());
		}).exceptionally(e -> {
			System.err.println("Error greeting: " + e.getMessage());
			return null;
		}).join();
		System.out.println(obj);
		System.out.println("Done");
	}

	private static ApiResponseCache setAlbumsSilently(ApiResponseCache cache, CompletableFuture<List<Albums>> p) {
		try {
			return cache.setAlbums(p.get());
		} catch (Exception e) {
			System.err.println(e);
		}
		return cache;
	}

	private static ApiResponseCache setCommentsSilently(ApiResponseCache cache, CompletableFuture<List<Comments>> p) {
		try {
			return cache.setComments(p.get());
		} catch (Exception e) {
			System.err.println(e);
		}
		return cache;
	}

	private static ApiResponseCache setPostsSilently(ApiResponseCache cache, CompletableFuture<List<Posts>> p) {
		try {
			return cache.setPosts(p.get());
		} catch (Exception e) {
			System.err.println(e);
		}
		return cache;
	}

	public static CompletableFuture<List<Posts>> getPostsAsync() throws Exception {
		return CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("Going to fetch the posts data started");
				return JUtils.sendGet(POSTS_URL, Posts.class);
			} finally {
				System.out.println("Going to fetch the posts data completed");
			}
		});
	}

	public static CompletableFuture<List<Comments>> getCommentsAsync() throws Exception {
		return CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("Going to fetch the comments data started");
				return JUtils.sendGet(COMMENTS_URL, Comments.class);
			} finally {
				System.out.println("Going to fetch the comments data completed");
			}
		});
	}

	public static CompletableFuture<List<Albums>> getAlbumsAsync() throws Exception {
		return CompletableFuture.supplyAsync(() -> {
			try {
				System.out.println("Going to fetch the albums data started");
				return JUtils.sendGet(ALBUM_URLS, Albums.class);
			} finally {
				System.out.println("Going to fetch the albums data completed");
			}
		});
	}
}
