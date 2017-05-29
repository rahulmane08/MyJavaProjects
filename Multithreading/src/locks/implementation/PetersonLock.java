package locks.implementation;

public class PetersonLock implements Lock {

	private boolean[] flag = new boolean[2];
	private int victim;

	@Override
	public void lock() {
		int i = (int) Thread.currentThread().getId(); // either 0 or 1
		int j = 1 - i;
		flag[i] = true;
		victim = i;
		while (flag[j] && victim == i) {
		}
		; // spin

	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub

	}

}
