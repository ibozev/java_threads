package comunication;

class TickTock {
    
    synchronized void tick(boolean running) {
	if (!running) {
	    notify();
	    return;
	}
	
	System.out.print("Tick ");	
	notify();
	try {
	    wait();
	}
	catch(InterruptedException exc) {
	    System.out.println("Thread interrrupted.");
	}
    }	
    
    synchronized void tock(boolean running) {
	if (!running) {
	    notify();
	    return;
	}
	
	System.out.println("Tock");	
	notify();
	try {
	    wait();
	}
	catch(InterruptedException exc) {
	    System.out.println("Thread interrrupted.");
	}
    }	
}

class MyThread implements Runnable{
    Thread thrd;
    TickTock obj;
    
    MyThread(String name, TickTock tt) {
	thrd = new Thread(this, name);
	obj = tt;
	thrd.start();
    }
    
    @Override
    public void run() {
	if (thrd.getName().compareTo("Tick") == 0) {
	    for (int i = 0; i < 5; i++) 
		obj.tick(true);
	    obj.tick(false);
	}
	else {
	    for (int i = 0; i < 5; i++) 
		obj.tock(true);
	    obj.tock(false);
	}
    }
}

public class ThreadCom {

    public static void main(String[] args) {
	TickTock tt = new TickTock();
	MyThread mt1 = new MyThread("Tick", tt);
	MyThread mt2 = new MyThread("Tock", tt);
	
	try {
	    mt1.thrd.join();
	    mt2.thrd.join();
	}
	catch(InterruptedException exc) {
	    System.out.println("Main thread interrupted.");
	}
    }

}
