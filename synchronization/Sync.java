package synchronization;

class SumArray {
    private int sum;
    
    synchronized int sumArray(int[] nums) {
	sum = 0;
	
	for (int i = 0; i < nums.length; i++) {
	    sum += nums[i];
	    System.out.println("Running total for " + Thread.currentThread().getName() + " is " + sum);
	    
	    try {
		Thread.sleep(10);
	    }
	    catch(InterruptedException exc) {
		System.out.println("Main thread interrupted.");
	    }
	}
	return sum;
    }
}

class MyThread implements Runnable{
    Thread thrd;
    static SumArray sa = new SumArray();
    int[] a;
    
    MyThread(String name, int[] nums) {
	thrd = new Thread(this, name);
	thrd.start();
	a = nums;
    }
    
    public void run() {
	int sum;
	
	System.out.println(thrd.getName() + " starting.");
	
//	synchronized(sa) {
//	    sum = sa.sumArray(a);
//	}
	
	sum = sa.sumArray(a);
	System.out.println("Sum for " + thrd.getName() + " is " + sum);
	
	System.out.println(thrd.getName() + " terminating.");
    }
}

public class Sync {

    public static void main(String[] args) {
	int[] a = {1, 2, 3, 4, };
	
	MyThread mt1 = new MyThread("Child #1", a);
	MyThread mt2 = new MyThread("Child #2", a);
	
	try {
	    mt1.thrd.join();
	    mt2.thrd.join();
	}
	catch(InterruptedException esc) {
	    System.out.println("Main thread interrupted.");
	}
	
    }

}
