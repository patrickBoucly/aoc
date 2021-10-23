package advent_of_code.main.a2020;

public class Day25 {

	public static void main(String[] args) {

		// long startTime = System.currentTimeMillis();

		System.out.println("solve1 :" + getPart1Solution());
		System.out.println("solve172 :" + getPart2Solution());

//		long endTime = System.currentTimeMillis();

		// System.out.println("That took " + (endTime - startTime) + " milliseconds");

	}
	
	
	public static long getPart1Solution() {
		final InputData inputData = getInput();

		long value = 1;
		long loop = 0;
		while (value != inputData.doorkey && value != inputData.cardkey) {
			value *= 7;
			value %= 20201227;
			loop++;
		}

		final long subj = value == inputData.doorkey ? inputData.cardkey : inputData.doorkey;

		value = 1;
		for (long i = 0; i < loop; i++) {
			value *= subj;
			value %= 20201227;
		}

		return value;
	}

	public static Object getPart2Solution() {
		return "*";
	}

	private static InputData getInput() {

		return new InputData(Long.parseLong("12320657"), Long.parseLong("9659666"));
	}

	private static class InputData {
		public final long doorkey;
		public final long cardkey;

		public InputData(long doorkey, long cardkey) {
			this.doorkey = doorkey;
			this.cardkey = cardkey;
		}
	}
}