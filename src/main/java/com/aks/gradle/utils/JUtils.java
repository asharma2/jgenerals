package com.aks.gradle.utils;

import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class JUtils {

	private JUtils() {
	}

	public static final <T> List<T> sendGet(String url, Class<T> klass) {
		long s = System.currentTimeMillis();
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			String json = EntityUtils.toString(response.getEntity());
			ObjectMapper om = new ObjectMapper();
			return om.readValue(json, om.getTypeFactory().constructCollectionType(List.class, klass));
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("Executed in total time: " + (System.currentTimeMillis() - s));
		}
	}
}
