package javaaa;

public class Demo12 {

	public static void main(String[] args) {
		int days = 0;
		for (int year = 2000; year <= 2008; year++) {
			for (int month = 1; month <= 12; month++) {
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
					if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
						days = 28;
						System.err.println("leap month");
					} else
						days = 29;
					break;

				default:
					System.err.println("invalid month");
					break;
				}
				System.out.println("Days in year " + year + " for " + month + " month is : " + days);

			}
		}

	}

}
