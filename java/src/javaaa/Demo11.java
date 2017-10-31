package javaaa;

public class Demo11 {

	public static void main(String[] args) {

		int month = 2;
		int year = 2000;
		int days = 0;

		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			days = 31;
			break;

		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;

		case 2:
			// if (year %4 == 0 && !(year %100 == 0) || year %400 == 0)
			if (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0))
				days = 28;
			else
				days = 29;
			break;

		default:
			System.out.println("invalid month");
			break;
		}
		System.out.println(days);

	}
}
