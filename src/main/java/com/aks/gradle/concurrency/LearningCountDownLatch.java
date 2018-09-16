package com.aks.gradle.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LearningCountDownLatch {

	public static class Worker implements Runnable {
		String name;
		CountDownLatch cdl;

		public Worker(String name, CountDownLatch cdl) {
			super();
			this.name = name;
			this.cdl = cdl;
		}

		@Override
		public void run() {
			System.out.println("Thread: " + name + " executed");
			cdl.countDown();
		}
	}

	public static void main(String[] args) throws Exception {
		CountDownLatch cdl = new CountDownLatch(2);
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(new Worker("T1", cdl));
		service.submit(new Worker("T2", cdl));
		cdl.await();
		service.shutdown();
	}
}
