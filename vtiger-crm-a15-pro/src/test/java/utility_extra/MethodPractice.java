package utility_extra;

public class MethodPractice {
//	public void add(int num1 , int num2) {
//		int num3 = num1 + num2;
//		System.out.println(num3);
//	}

	int num1 ;

	public int add(int num1) {
		int num2 = num1; // this points to the current class object
		this.num1 = num1;
		return num2;
	}

	public int sub() {
		return num1;
	}
	
	public static void main(String[] args) {
		MethodPractice mp = new MethodPractice();
		int num1 = 2;

		System.out.println(mp.add(num1));
		
		System.out.println(mp.sub());
	}
}
