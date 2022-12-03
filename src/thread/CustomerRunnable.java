package thread;

import model.Car;
import model.carWash.AbstractCarWash;

public class CustomerRunnable implements Runnable {

	private final AbstractCarWash abstractCarWash;
	private final boolean interior;

	public CustomerRunnable(AbstractCarWash abstractCarWash, boolean interior) {
		this.abstractCarWash = abstractCarWash;
		this.interior = interior;
	}

	@Override
	public void run() {
		try {
			Car car = new Car();
			if(interior) abstractCarWash.cleanInterior(car);
			abstractCarWash.washCar(car);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
