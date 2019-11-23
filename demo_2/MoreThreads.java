package demo_2;

class MyThread implements Runnable {
    int count;
    Thread thrd;
    
    MyThread(String name) {
	count = 0;
	thrd = new Thread(this, name);
	thrd.start();
    }
    
    public void run() {
	System.out.println(thrd.getName() + " starting.");
	try {
	    do {
		Thread.sleep(500);
		System.out.println("In " + thrd.getName() + ", count is " + count);
		count++;
	    } while (count < 10);
	}
	catch(InterruptedException exc) {
	    System.out.println(thrd.getName() + " interrupted.");
	}
	System.out.println(thrd.getName() + " terminating.");
    }
}
public class MoreThreads {

    public static void main(String[] args) {
	System.out.println("Main thread starting.");
	
	MyThread mt1 = new MyThread("Child #1");
	MyThread mt2 = new MyThread("Child #2");
	MyThread mt3 = new MyThread("Child #3");
	
	do {
	    System.out.print(".");
	    try {
		Thread.sleep(100);
	    }
	    catch(InterruptedException exc) {
		System.out.println("Main thread exception.");
	    }
	} while (mt1.thrd.isAlive() || mt2.thrd.isAlive() || mt3.thrd.isAlive());
	
	System.out.println("Main thread ending.");

    }

}
