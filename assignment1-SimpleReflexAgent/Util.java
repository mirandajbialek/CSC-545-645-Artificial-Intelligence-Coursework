package mibi.assignment1;

import java.util.Random;

public class Util {

	private static Random random = new Random();

	public static boolean randomBoolean() {
		return random.nextInt(2) == 1;
	}

}