package pool;

public class Pool {
    public static double maxCapacity;
    private double currentCapacity;
    
    public Pool(double capacity) {
	maxCapacity = capacity;
    }
    
    synchronized void setCapacity(double volume) {
	currentCapacity += volume;
    }
    
    synchronized double getCapacity() {
	return currentCapacity;
    }
}
