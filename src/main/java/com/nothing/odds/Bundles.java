package com.nothing.odds;

import java.time.Duration;
import java.util.ResourceBundle;
import java.util.Scanner;

public enum Bundles {

	BIG_DATA(1, 10, Duration.ofHours(24)), FOREVER_DATA(2, 10, Duration.ofHours(24)),
	NEWCOMER_DATA(3, 10, Duration.ofHours(24)), EXPERIENCED_DATA(4, 10, Duration.ofHours(24));

	private int id;
	private double amount;
	private Duration bundleLimit;
	private String ages;

	@Override
	public String toString() {
		return this.name() + "," + this.id + "," + this.amount + "," + this.bundleLimit + "," + this.ages;
	}

	public static Bundles valueOf(Integer id) {

		Bundles theBundle;
		// get the integer value
		theBundle = switch (id) {

		case 1 -> BIG_DATA;
		case 2 -> FOREVER_DATA;
		case 3 -> NEWCOMER_DATA;
		case 4 -> EXPERIENCED_DATA;

		default -> throw new IllegalStateException("Bundles PACKAGE NOT FOUND");

		};

		return theBundle;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Duration getBundleLimit() {
		return bundleLimit;
	}

	public void setBundleLimit(Duration bundleLimit) {
		this.bundleLimit = bundleLimit;
	}

	public String getAges() {
		return ages;
	}

	// public String[] setAges([] aintges) {
	// this.ages = ages;
	// }

	private String setAges(int bundleId) {

		ResourceBundle rb = ResourceBundle.getBundle("bundles");

		String listOfAges = switch (bundleId) {

		case 1 -> {

			listOfAges = rb.getString("BIG_DATA_AGES");

			yield listOfAges;
		}

		case 2 -> {

			listOfAges = rb.getString("FOREVER_DATA_AGES");

			yield listOfAges;

		}

		case 3 -> {
			listOfAges = rb.getString("NEWCOMER_DATA_AGES");

			yield listOfAges;

		}

		case 4 -> {
			listOfAges = rb.getString("EXPERIENCED_DATA_AGES");

			yield listOfAges;
		}

		default -> {

			throw new IllegalStateException("Bundles PACKAGE NOT FOUND");

		}

		};

		return listOfAges;

	}

	private Bundles(int id, double amount, Duration bundleLimit) {
		this.id = id;
		this.amount = amount;
		this.bundleLimit = bundleLimit;

		this.ages = setAges(id);

	}

	public static void main(String args[]) {

		System.out.println("Please Select Bundle Package:");
		System.out.println("1. BIG_DATA");
		System.out.println("2. FOREVER_DATA");
		System.out.println("3. NEWCOMER_DATA");
		System.out.println("4. EXPERIENCED_DATA");

		ResourceBundle rb = ResourceBundle.getBundle("bundles");

		// get user input
		Scanner userScanner = new Scanner(System.in);
		Integer bundleType = userScanner.nextInt();

		try {

			Bundles instance;
			instance = Bundles.valueOf(bundleType);
			String[] content;
			String[] displayOrder;
			switch (instance) {

			case BIG_DATA:

				System.out.println("You have selected the bundle offer.");

				content = BIG_DATA.toString().split(",");

				displayOrder = rb.getString("DISPLAY_ORDER").split(",");

				for (int i = 0; i < displayOrder.length; i++) {

					System.out.print(displayOrder[i]);
					System.out.print(":");
					System.out.println(content[i]);

				}

				break;
			case FOREVER_DATA:

				System.out.println("You have selected the bundle offer.");
				content = FOREVER_DATA.toString().split(",");

				displayOrder = rb.getString("DISPLAY_ORDER").split(",");

				for (int i = 0; i < displayOrder.length; i++) {

					System.out.print(displayOrder[i]);
					System.out.print(":");
					System.out.println(content[i]);

				}
				break;
			case NEWCOMER_DATA:

				System.out.println("You have selected the bundle offer.");
				// System.out.println(NEWCOMER_DATA.toString());

				content = NEWCOMER_DATA.toString().split(",");

				displayOrder = rb.getString("DISPLAY_ORDER").split(",");

				for (int i = 0; i < displayOrder.length; i++) {

					System.out.print(displayOrder[i]);
					System.out.print(":");
					System.out.println(content[i]);

				}

				break;

			case EXPERIENCED_DATA:

				System.out.println("You have selected the bundle offer.");
				// System.out.println(EXPERIENCED_DATA.toString());

				content = EXPERIENCED_DATA.toString().split(",");

				displayOrder = rb.getString("DISPLAY_ORDER").split(",");

				for (int i = 0; i < displayOrder.length; i++) {

					System.out.print(displayOrder[i]);
					System.out.print(":");
					System.out.println(content[i]);

				}

				break;

			default:

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

	}

}
