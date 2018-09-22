package com.aks.gradle.utils;

import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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

	public static final String sendPost(String url, String inputJson, Map<String, String> headers) {
		long s = System.currentTimeMillis();
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(url);
			StringEntity input = new StringEntity(inputJson);
			input.setContentType("application/json");
			headers.forEach((k, v) -> {
				httpPost.addHeader(k, v);
			});
			httpPost.setEntity(input);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String plainTxt = EntityUtils.toString(response.getEntity());
			return plainTxt;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("Executed in total time: " + (System.currentTimeMillis() - s));
		}
	}

	public static final <T> T sendPost(String url, String inputJson, Map<String, String> headers, Class<T> klass) {
		long s = System.currentTimeMillis();
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost(url);
			StringEntity input = new StringEntity(inputJson);
			input.setContentType("application/json");

			headers.forEach((k, v) -> {
				httpPost.addHeader(k, v);
			});
			httpPost.setEntity(input);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			String plainTxt = EntityUtils.toString(response.getEntity());
			ObjectMapper om = new ObjectMapper();
			return om.readValue(plainTxt, klass);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("Executed in total time: " + (System.currentTimeMillis() - s));
		}
	}
}
