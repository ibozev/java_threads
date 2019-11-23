package demo_1;

class MyThread implements Runnable{
    int count;
    String thrdName;
    
    MyThread(String name) {
	count = 0;
	thrdName = name;
    }
    
    public void run() {
	System.out.println(thrdName + " starting.");
	try {
	    do {
		Thread.sleep(500);
		System.out.println("In " + thrdName + ", count is " + count);
		count++;
	    } while (count < 10);
	}
	catch(InterruptedException exc) {
	    System.out.println(thrdName + " interrupted.");
	}
	System.out.println(thrdName + " terminating.");
    }
}

public class UseThreads {

    public static void main(String[] args) {
	System.out.println("Main thread starting.");
	
	MyThread mt = new MyThread("Child #1");
	Thread newThrd = new Thread(mt);
	
	newThrd.start();
	
	do {
	    System.out.print(".");
	    try {
		Thread.sleep(100);
	    }
	    catch(InterruptedException exc) {
		System.out.println("Main thread exception.");
	    }
	} while (mt.count != 10);
	
	System.out.println("Main thread ending.");
    }

}
