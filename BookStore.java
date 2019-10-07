/*
 * Aditya Yadav
 * Current version
 * Description: This program allows the user to navigate through a list of books, add their own books, or remove books. They can also purchase books.
 */
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class BookStore {
	// create all the needed variables and arrays
	static int choice, bookID, bookPurchase;
	static ArrayList<String> title;
	static ArrayList<Double> price;
	static ArrayList<Integer> ID;
	static Scanner sc = new Scanner(System.in);
	// the main class. all the spicy stuff happens here
	public static void main(String[] args) throws IOException {
		menu();
		while (true) {
			// users choice
			choice = sc.nextInt();
			if (choice == 1) { // choice 1 if user wishes to navigate around the book store
				boolean reset = true;
				read();
				for (int i = 0; i < ID.size(); i++) {
					System.out.println(// print the books
							"title: " + title.get(i) + "\nbook Id: " + ID.get(i) + "\nprice: " + price.get(i) + "\n");
				}
				while (reset) {// get the book ID the user is looking for
					System.out.println("Enter the book ID: ");
					bookID = sc.nextInt();
					if (ID.contains(bookID)) {
						//LINEAR SEARCH
						int z = linearSearch(ID, bookID);
						System.out.println(ID.get(z));
						System.out.println(title.get(z));
						System.out.println(price.get(z));
						System.out.println("Enter the number of books you wish to purchase: ");
						int numberOfBooks = sc.nextInt();
						System.out.println("Number of books bought: " + numberOfBooks);
						double cost = price.get(z) * numberOfBooks;
						System.out.println("Total cost: " + cost);
						System.out.println("Confirm purchase (Enter Yes or No)");
						String choice = sc.next();
						if (choice.equalsIgnoreCase("yes")) {
							System.out.println("Books have been purchased");
							menu();
							reset = false;
						}
					} else
						System.out.println("The book does not exist");
				}

			} // choice 2 if user wants to add a book
			if (choice == 2) {
				read();
				boolean repeat1 = true;
				int newBook = 0;
				while (repeat1) {
					System.out.println("Enter a book ID: ");
					newBook = sc.nextInt();
					if (ID.contains(newBook)) {
						System.out.println("This book ID already exists, please try again");
					} else
						repeat1 = false;
				}// getting info for new book
				System.out.println("Enter a new book title: ");
				String newTitle = sc.next();
				System.out.println("Enter the price for the book");
				double newPrice = sc.nextDouble();
				// adding new book to the array list
				ID.add(newBook);
				title.add(newTitle);
				price.add(newPrice);

				PrintWriter printWriter = new PrintWriter("BookData.txt");
				for (int i = 0; i < ID.size(); i++) {
					// updating textfile with new books
					printWriter.print(title.get(i) + ",");
					printWriter.print(ID.get(i) + ",");
					printWriter.print(price.get(i) + "\n");
				}
				printWriter.close();
				menu();

			}// choice 3 for removing a book
			if (choice == 3) {
				read();
				boolean repeat1 = true;
				while (repeat1) {// basically the same stuff as last choice option
					System.out.println("Enter a book ID: ");
					int removeBook = sc.nextInt();
					if (ID.contains(removeBook)) {
						int z = ID.indexOf(removeBook);
						// removing from array 
						title.remove(z);
						ID.remove(z);
						price.remove(z);
						PrintWriter printWriter = new PrintWriter("BookData.txt");
						// updating textfile with new array list
						for (int i = 0; i < ID.size(); i++) {
							printWriter.print(title.get(i) + ",");
							printWriter.print(ID.get(i) + ",");
							printWriter.print(price.get(i) + "\n");
						}
						printWriter.close();
						repeat1 = false;
					} else
						System.out.println("That book ID doesnt exist.");
				}
				menu();

			}

			if (choice == 4) {
				System.out.println("You are now exiting the program");
				System.exit(0);
			}
		}

	}
	//method for menu
	public static void menu() {
		System.out.println("1) Purchase Menu");
		System.out.println("2) Add a book to Inventory");
		System.out.println("3) Remove a Book from Inventory");
		System.out.println("4) Quit");
		System.out.println("Enter your choice");
	}
	// method for reading book 
	public static void read() throws FileNotFoundException {
		title = new ArrayList<>();
		price = new ArrayList<>();
		ID = new ArrayList<>();
		Scanner read = new Scanner(new File("BookData.txt"));
		int i = 0;
		while (read.hasNext()) {

			read.useDelimiter(",|\\n");
			title.add(read.next());
			ID.add(Integer.parseInt(read.next()));
			price.add(Double.parseDouble(read.next()));
			i++;
		}
		read.close();
	}
	// linear search method
	public static int linearSearch(ArrayList<Integer> ID, int bookID) {
		int position = 0;

		for (int index = 0; index < ID.size(); index++) {
			if (ID.get(index).equals(bookID)) {
				position = index;
			}
		}

		return position;

	}

}
