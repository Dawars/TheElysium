package mods.elysium;

import java.util.ArrayList;
import java.util.Random;

public class ShuffleBag {
	private Random random = new Random();
	private static ArrayList<String> data = new ArrayList<String>();

	private String currentItem;
	private int currentPosition = -1;

	public int Size() {
		return this.data.size();
	}

	public ShuffleBag(int initCapacity) {
	}
	public ShuffleBag() {
		new ShuffleBag(0);
	}

	public void Add(String item) {
		
		this.data.add(item);
		currentPosition = this.Size() - 1;
	}
	
	public void Add(String item, int amount) {
		for (int i = 0; i < amount; i++)
			this.data.add(item);

		currentPosition = this.Size() - 1;
	}

	public String Next() {
		if (currentPosition < 1) {
			currentPosition = this.Size() - 1;
			currentItem = data.get(0);

			return currentItem;
		}

		int pos = random.nextInt(currentPosition);

		currentItem = this.data.get(pos);
		this.data.add(pos, this.data.get(currentPosition));
		this.data.add(currentPosition, currentItem);
		currentPosition--;

		return currentItem;
	}
}