package global;

public class Worker {
	
	private int workerId;
	private String storedWorkerName;
	
	public Worker( int workerId, String storedWorkerName) {
		this.workerId = workerId;
		this.storedWorkerName = storedWorkerName;
	}
	
	public void setStoredWorkerName(String storedWorkerName) {
		this.storedWorkerName = storedWorkerName;
	}
	
	public void setWorkerId(int workerId) {
		this.workerId = workerId;
	}
	
	public String getStoredWorkerName() {
		return storedWorkerName;
	}
	
	public int getWorkerId() {
		return workerId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return storedWorkerName;
	}

}
