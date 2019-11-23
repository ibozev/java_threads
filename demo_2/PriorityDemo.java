package demo_2;

class Priority implements Runnable {
    int count;
    Thread thrd;
    
    static boolean stop = false;
    static String currName;
    
    Priority(String name) {
	thrd = new Thread(this, name);
	count = 0;
	currName = name;
    }
    
    public void run() {
	System.out.println(thrd.getName() + " starting.");
	
	do {
	    count++;
	    
	    if(currName != thrd.getName()) {
		currName = thrd.getName();
		System.out.println("In " + currName);
	    }
	} while(stop == false && count < 1000);
	
	stop = true;
	
	System.out.println(thrd.getName() + " teerminating.");
    }
}

public class PriorityDemo {

    public static void main(String[] args) {
	Priority mt1 = new Priority("High Priority");
	Priority mt2 = new Priority("Low Priority");
	
	mt1.thrd.setPriority(Thread.MAX_PRIORITY);
	mt2.thrd.setPriority(Thread.MIN_PRIORITY);
	
	mt1.thrd.start();
	mt2.thrd.start();
	
	try {
	    mt1.thrd.join();
	    mt2.thrd.join();
	}
	catch(InterruptedException exc) {
	    System.out.println("Main thread interrupted.");
	}
	
	System.out.println("\n" + "High priority thread counted to " + mt1.count);
	System.out.println("Low priority thread counted to " + mt2.count);
    }

}
