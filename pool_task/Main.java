package pool;

public class Main {

    public static void main(String[] args) {
	Pool pool = new Pool(50);
	MyThread mt1 = new MyThread("Pour #1", 2.00, pool);
	MyThread mt2 = new MyThread("Pour #2", 1.00, pool);
	MyThread mt3 = new MyThread("Drain #3", -0.5, pool);
	int seconds = 0;
	
	while (true) {
	    try {
		mt3.mySuspend();
		while (true) {
		    if (pool.getCapacity() > Pool.maxCapacity){
			mt2.mySuspend();
			mt3.myResume();
			System.out.println(mt3.thrd.getName() + " starting.");
		    }
		    else if (pool.getCapacity() <= 0.9 * Pool.maxCapacity) {
			mt2.myResume();
			mt3.mySuspend();
			System.out.println(mt2.thrd.getName() + " starting.");
		    }
		    else if (pool.getCapacity() >= 0.9 * Pool.maxCapacity) {
			mt1.myStop();
		    } 
		    seconds++;
		    Thread.sleep(1000);
		    System.out.println("Time: " + seconds + " capacity: " + pool.getCapacity());
		}
	    } catch (InterruptedException ie) {
		System.out.println("Thread interrupted.");
	    }
	}
    }
}
