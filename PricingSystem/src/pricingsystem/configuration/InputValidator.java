package pricingsystem.configuration;



import java.util.Scanner;
import java.util.regex.Pattern;

public class InputValidator {

	private Scanner scanner;

	// F�r die User-Eingabe. Gibt eine Zahl von 1-max zur�ck.
	public int getSingleNumber(int max) {
		String output = "";
		try {
			scanner = new Scanner(System.in);
			System.out.print("Input: ");
			String input = null;
			input = scanner.next();

			boolean valid = false;
			if (input != null && input.length() == 1) {
				valid = validateSingleNumericalInput(input, max);
			}
			if (valid)
				output = input;
			else {
				System.out.println("Invalid Input. Try again!");
				return getSingleNumber(max);
			}
		} catch (NumberFormatException e) {
			System.out.println("something went horribly wrong. try again");
			getSingleNumber(max);
		}
		return Integer.valueOf(output);
	}

	private boolean validateSingleNumericalInput(String input, int max) {
		String reg = "[1" + "-" + String.valueOf(max) + "]";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(input).matches();
	}

	// F�r die User-Eingabe einer Email-Adresse
	public String getEmail() {
		String output = "";
		try {
			scanner = new Scanner(System.in);
			System.out.print("Input:");
			String input = null;
			input = scanner.next();

			boolean valid = false;
			if (input != null) {
				valid = validateEmail(input);
			}
			if (valid)
				output = input;
			else {
				System.out.println("Invalid Input. Try again!");
				return getEmail();
			}
		} catch (NumberFormatException e) {
			System.out.println("something went horribly wrong. try again");
			return getEmail();
		}
		return output;
	}

	private boolean validateEmail(String input) {
		String reg = "^[a-zA-Z0-9.a-zA-Z0-9.!#$%&'*+-/=?^_`{|}~]+@[a-zA-Z0-9]+\\.[a-zA-Z]+";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(input).matches();
	}

	public double getNumber() {
		String output = "";
		try {
			scanner = new Scanner(System.in);
			System.out.print("Input:");
			String input = null;
			input = scanner.next();

			boolean valid = false;
			if (input != null) {
				valid = validateNumber(input);
			}
			if (valid)
				output = input;
			else {
				System.out.println("Invalid Input. Try again!");
				return getNumber();
			}
		} catch (NumberFormatException e) {
			System.out.println("something went horribly wrong. try again");
			getNumber();
		}
		return Double.valueOf(output);
	}

	private boolean validateNumber(String input) {
		String reg = "^(0|([1-9][0-9]*))(\\\\.[0-9]+)?$";
		Pattern pattern = Pattern.compile(reg);
		return pattern.matcher(input).matches();
	}

	public String getString() {
		String output = "";
		try {
			scanner = new Scanner(System.in);
			System.out.print("Input:");
			String input = null;
			input = scanner.next();
			if (input != null && input.length() <= 16) {
				output = input;
			} else {
				System.out.println("Invalid Input. Try again!");
				return getString();
			}
		} catch (NumberFormatException e) {
			System.out.println("something went horribly wrong. try again");
			return getString();
		}
		return output;
	}

}