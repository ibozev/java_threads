package pool;

public class MyThread implements Runnable{
    Pool pool;
    Thread thrd;
    double flowVolume;
    private boolean stopped = false;
    private boolean suspended = false;
    
    public MyThread(String name, double updVol, Pool obj) {
	thrd = new Thread(this, name);
	flowVolume = updVol;
	pool = obj;
	thrd.start();
    }
 
    public void run() {
	try {
	    while (true) {
		synchronized (this) {
		    while (suspended) {
			System.out.println(thrd.getName() + " waiting.");
			wait();
		    }
		    if (stopped) {
			System.out.println(thrd.getName() + " is stoped.");
			break;
		    }
		}
		pool.setCapacity(flowVolume);

		Thread.sleep(1000);
//		System.out.println(thrd.getName() + " changed to " + pool.getCapacity());
	    }
	} catch (InterruptedException exc) {
	    System.out.println(thrd.getName() + " interrupted.");
	}
    }
    
    synchronized void myStop() {
	stopped = true;
	suspended = false;
//	notify();
    }

    synchronized void mySuspend() {
	suspended = true;
    }

    synchronized void myResume() {
	suspended = false;
	notify();
    }
}
