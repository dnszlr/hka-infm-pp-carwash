package model.counter;

public class IntegerCounter extends AbstractCounter{

	private int value;

	public IntegerCounter(int value, Type type) {
		super(type);
		this.value = value;
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
}
