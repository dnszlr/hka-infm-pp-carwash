package model.carWash;

public class MutableInteger {

	private int value;
	private final String type;

	public MutableInteger(int value, String type) {
		this.value = value;
		this.type = type;
	}

	public int get() {
		return value;
	}

	public void increment() {
		this.value++;
	}

	public void decrement() {
		this.value--;
	}

	public boolean isFull() {
		return this.value == 0;
	}

	@Override
	public String toString() {
		return type;
	}
}
