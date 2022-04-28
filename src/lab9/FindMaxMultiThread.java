package lab9;


public class FindMaxMultiThread {
	// this is an array of 90 elements
		// the max value of this array is 9999
		static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
				234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
				3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
				3, 4543, 234, 3, 454, 1, 2, 3 };
		public static void main(String[] args) {
			new FindMaxMultiThread().runThread();
		}
		public void printMax() {
			// this is a single threaded version
			int max = findMax(0, array.length - 1);
			System.out.println("the max value is " + max);
		}
		
		private int findMax(int begin, int end) {
			// you should NOT change this function
			int max = array[begin];
			for (int i = begin + 1; i <= end; i++) {
				if (array[i] > max) {
					max = array[i];
				}
			}
			return max;
		}
		public void runThread(){
			findMax task1 = new findMax(0,29);
			findMax task2 = new findMax(30,59);
			findMax task3 = new findMax(60,89);
			
			Thread t1 = new Thread(task1);
			Thread t2 = new Thread(task2);
			Thread t3 = new Thread(task3);
			t1.start();
			t2.start();
			t3.start();
			boolean isProcessing = true;
			while(isProcessing)
			{
				if(!t1.isAlive()&&!t2.isAlive()&&!t3.isAlive())
				{
					isProcessing =false;
					break;
				}
			}
			
			int max1 =task1.getMax();
			int max2 =task2.getMax();
			int max3 =task3.getMax();
			System.out.println(max1+"," + max2 +","+max3);
			int max = max1;
			if(max2>max)
			{
				max = max2;
			}
			if(max3>max)
			{
				max = max3;
			}
			System.out.println("the max value is " + max);
		}
		private class findMax implements Runnable{
			private int begin;
			private int end;
			private int max;
			public findMax(int begin,int end)
			{
				this.begin = begin;
				this.end = end;
			}
			@Override
			public void run(){
	            //things to do    
				int max = array[begin];
				for (int i = begin + 1; i <= end; i++) {
					if (array[i] > max) {
						max = array[i];
					}
				}
				this.max = max;
			}
			
			public int getMax()
			{
				return max;
			}
		}
		

}
