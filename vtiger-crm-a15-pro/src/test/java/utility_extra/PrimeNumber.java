package utility_extra;

public class PrimeNumber {
	public static void main(String[] args) {
		int a = 3;
		int b = 5;
		int c = 9;
		int d = 13;
		int e = 15;
		int f = 19;

		isPrime(a);
		isPrime(b);
		isPrime(c);
		isPrime(d);
		isPrime(e);
		isPrime(f);
	}
	
	public static boolean isPrime(int num) {
		boolean flag = false;
		
		for (int i = 2; i < num; i++) {
			if (num%i == 0) {
				System.out.println(num + "prime number nhi hai");
				break;
			}else {
				System.out.println(num + "prime number hai");
			}
		}

		return flag;
	}
}
