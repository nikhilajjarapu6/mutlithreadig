
package basics;

import java.util.Scanner;

public class Copilot {
	private int num1;
	private int num2;

	public int getNum1() {
		return num1;
	}

	public void setNum1(int num1) {
		this.num1 = num1;
	}

	public int getNum2() {
		return num2;
	}

	public void setNum2(int num2) {
		this.num2 = num2;
	}

	public void add() {
		System.out.println("Addition: " + (num1 + num2));
	}

	public void subtract() {
		System.out.println("Subtraction: " + (num1 - num2));
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Copilot copilot = new Copilot();

		System.out.print("Enter first number: ");
		copilot.setNum1(scanner.nextInt());

		System.out.print("Enter second number: ");
		copilot.setNum2(scanner.nextInt());

		System.out.print("Choose operation (add/subtract): ");
		String operation = scanner.next();

		if (operation.equalsIgnoreCase("add")) {
			copilot.add();
		} else if (operation.equalsIgnoreCase("subtract")) {
			copilot.subtract();
		} else {
			System.out.println("Invalid operation.");
		}
	}
}
