package lab9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RacyCounter {

	int counter = 0;
	final static int ITERATIONS = 10000000; /// do not change this

	public static void main(String[] args) throws Throwable {
		new RacyCounter().runTest();
	}
// do not change this method
	public void runTest() {
		ReentrantLock lock = new ReentrantLock();
		Thread t1 = new Thread(new MyTask(lock));
		Thread t2 = new Thread(new MyTask(lock));
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		if (counter == ITERATIONS * 2)
			System.out.println("CONGRATULATIONS! your code is thread safe");
		else
			System.out.println("there is a race condition the counter is " + counter + " but it should be " + ITERATIONS * 2);

	}

	private class MyTask implements Runnable{
		ReentrantLock lock;
		public MyTask(ReentrantLock lock)
		{
			this.lock = lock;
		}
		@Override
		public void run(){
			synchronized(lock){
			for (int i = 0; i < ITERATIONS; i++) {
				//add synchronization here
				
				int temp = counter;
				counter = temp +1;
			}
			}
		}
	}
}
