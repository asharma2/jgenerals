package com.aks.gradle.concurrency;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LearningCyclicBarrier {

	public static class Worker implements Runnable {
		String name;
		CyclicBarrier barrier;
		int runCount;

		public Worker(String name, CyclicBarrier barrier, int runCount) {
			this.name = name;
			this.barrier = barrier;
			this.runCount = runCount;
		}

		@Override
		public void run() {
			try {
				for (int i = 1; i <= runCount; i++) {
					System.out.println("Thread: " + name + ", Run count: " + i);
					barrier.await();
				}
			} catch (Exception e) {
				System.err.println("Exception while running the cyclic barrier" + e);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int runCount = 5;
		Lock lock = new ReentrantLock();
		CyclicBarrier barrier = new CyclicBarrier(2, () -> {
			System.out.println("=============== Execution Done ===============");
			try {
				lock.lock();
			} catch (Exception e) {
				System.err.println(e);
			} finally {
				lock.unlock();
			}
		});
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(new Worker("T1", barrier, runCount));
		service.submit(new Worker("T2", barrier, runCount));
		System.out.println("============= Executed =============");
		service.shutdown();
	}
}
