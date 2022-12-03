package model.carWash;

import basic.RandomGenerator;
import model.Car;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public abstract class AbstractCarWash {

	private final String name;
	// Amount of washing lines in the Car Wash park
	private final MutableInteger washLines;
	// Amount of interior cleaning boxes in the Car Wash park
	private final MutableInteger interiorCleaningBoxes;
	private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

	public AbstractCarWash(String name, int washLines, int interiorCleaningBoxes) {
		this.name = name;
		this.washLines = new MutableInteger(washLines, "Wash Lines");
		this.interiorCleaningBoxes = new MutableInteger(interiorCleaningBoxes, "Interior Cleaning Boxes");
	}

	/**
	 * The washing process of a single car
	 * @param car The car of the customer
	 * @throws InterruptedException Can throw an InterruptedException because threads are put to sleep to simulate the work performed
	 */
	public void washCar(Car car) throws InterruptedException {
		enter(this.washLines);
		long duration = RandomGenerator.generate(5, 10);
		Thread.sleep(duration * 1000);
		threadPrint(car + " wash complete, took " + duration + " minutes.");
		exit(this.washLines);
	}

	/**
	 * Enter the carWash
	 */
	protected abstract void enter(MutableInteger type);

	/**
	 * Exit the carWash
	 */
	protected abstract void exit(MutableInteger type);

	/**
	 * The interior cleaning process of a single car
	 * @param car The car of the customer
	 * @throws InterruptedException Can throw an InterruptedException because threads are put to sleep to simulate the work performed
	 */
	public void cleanInterior(Car car) throws InterruptedException {
		enter(this.interiorCleaningBoxes);
		long duration = RandomGenerator.generate(1, 3) * 5L;
		Thread.sleep(duration * 1000);
		threadPrint(car + " interior cleaning complete, took " + duration + " minutes.");
		exit(this.interiorCleaningBoxes);
	}

	/**
	 * Console print with a given message. Adds the id of the currently running thread and a timestamp to the passed message
	 * @param message The message to be printed on the console
	 */
	protected void threadPrint(String message) {
		String uglyTab = Thread.currentThread().getId() < 100 ? " " : "";
		System.out.println(dtf.format(LocalTime.now()) + " Thread Nr." + uglyTab + Thread.currentThread().getId() + ": \t" + message);
	}

	@Override
	public String toString() {
		return "CarWash{" +
				"name='" + name + '\'' +
				'}';
	}
}
