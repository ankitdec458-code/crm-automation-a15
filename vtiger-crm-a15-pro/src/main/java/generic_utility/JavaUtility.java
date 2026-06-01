package generic_utility;

public class JavaUtility {
	public static int generateRandomNumber() {
		double ranNum = Math.random() * 10000;
		int random = (int) ranNum;
		return random;
	}
}
