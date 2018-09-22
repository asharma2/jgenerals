package com.aks.gradle.performance;

import java.util.HashMap;
import java.util.Map;

import com.aks.gradle.utils.JUtils;
import com.aks.gradle.utils.JsonUtils;

public class LaunchWatchlistPerformance {

	public static void main(String[] args) throws Exception {
		String url = "http://nj12mhf0119.mhf.mhc:9500/scorewatchlist/getResultsMyWatchlists";
		String watchlistId = "939169";
		String json = JsonUtils.getMyWatchlistJson(watchlistId);
		Map<String, String> headers = new HashMap<>();
		headers.put("userName", "CAPerfTest26@capitaliq.com");
		headers.put("userId", "749199409");
		String response = JUtils.sendPost(url, json, headers);
		System.out.println("Response: " + response);
	}
}
