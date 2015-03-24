package com.thread;

public class MultiThread1 implements Runnable {

	private static int i = 500;

	@Override
	public void run() {
		while (i > 0) {
			i--;
			System.out.println(Thread.currentThread().getName() + ":" + i);
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		new Thread(new MultiThread1()).start();
		new Thread(new MultiThread1()).start();
		new Thread(new MultiThread1()).start();

	}

}
