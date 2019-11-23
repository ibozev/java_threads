package demo_1;


public class MoreThreads {

    public static void main(String[] args) {
	System.out.println("Main thread starting.");
	
	MyThread mt1 = new MyThread("Child #1");
	MyThread mt2 = new MyThread("Child #2");
	MyThread mt3 = new MyThread("Child #3");
	
	Thread thrd1 = new Thread(mt1);
	Thread thrd2 = new Thread(mt2);
	Thread thrd3 = new Thread(mt3);
	
	thrd1.start();
	thrd2.start();
	thrd3.start();

	do {
	    System.out.print(".");
	    try {
		Thread.sleep(100);
	    }
	    catch(InterruptedException exc) {
		System.out.println("Main thread exception.");
	    }
	} while (thrd1.isAlive() || thrd2.isAlive() || thrd3.isAlive());
	
	System.out.println("Main thread ending.");

    }

}
