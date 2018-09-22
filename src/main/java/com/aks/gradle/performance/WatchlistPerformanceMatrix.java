package com.aks.gradle.performance;

public class WatchlistPerformanceMatrix {

	String env;
	String watchlistId;
	long duration;

	public WatchlistPerformanceMatrix(String env, String watchlistId, long duration) {
		this.env = env;
		this.watchlistId = watchlistId;
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "WatchlistPerformanceMatrix [env=" + env + ", watchlistId=" + watchlistId + ", duration=" + duration + "]";
	}

}
