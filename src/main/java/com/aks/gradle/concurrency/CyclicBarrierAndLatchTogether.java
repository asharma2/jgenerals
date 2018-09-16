package com.aks.gradle.concurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierAndLatchTogether {

	public static class Worker implements Runnable {
		String name;
		int runCycle;
		CyclicBarrier barrier;

		public Worker(String name, int runCycle, CyclicBarrier barrier) {
			super();
			this.name = name;
			this.runCycle = runCycle;
			this.barrier = barrier;
		}

		@Override
		public void run() {
			try {
				for (int i = 1; i <= runCycle; i++) {
					System.out.println("Thread: " + name + ", Runcycle: " + i);
					TimeUnit.SECONDS.sleep(1);
					barrier.await();
				}
			} catch (Exception e) {
				System.err.println("Exception while running the process: " + e.getMessage());
			}
		}
	}

	public static void main(String[] args) throws Exception {
		int count = 5;
		CountDownLatch signal = new CountDownLatch(count);
		CyclicBarrier barrier = new CyclicBarrier(2, () -> {
			System.out.println("============== Processed ==============");
			signal.countDown();
		});
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(new Worker("T1", count, barrier));
		service.submit(new Worker("T2", count, barrier));
		signal.await();
		service.shutdown();
	}
}
