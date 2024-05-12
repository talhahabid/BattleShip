/**
 * @ProgramName: battleShip.java
 * @author: Talhah
 * @Date: 26/10/2022
 * @Description: user plays battleship
 * program places boats in random direction and coordinates
 * user guesses row and column to sink ship
 * if user guesses correctly places an "H"	
 * if user misses places an "O"
 * program does not accept letters or numbers > 10
 */

package grade_12;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class battleShip {

	// static variables
	static String[][] user_grid = new String[10][10];
	static String[][] computer_grid = new String[10][10];

	/** Method that fills computer_grid and user_grid with " " */
	/** Also places random ships on computer_grid */
	public static void fillGrid() {

		// fills both grids with " "
		for (int i = 0; i < 10; i++) {
			Arrays.fill(user_grid[i], " ");
			Arrays.fill(computer_grid[i], " ");
		}

		// places random ships on computer_grid
		for (int i = 0; i < 4; i++) {
			placeShip(i + 2);
		}

	}

	/** Method that prints user_grid */
	public static void printGrid() {

		// prints grid
		for (int i = 0; i < user_grid.length; i++) {
			for (int j = 0; j < user_grid[i].length; j++) {
				System.out.print("[" + user_grid[i][j] + "]");
			}

			System.out.println();
		}

	}

	/** Method that places ships in random coordinates */
	public static void placeShip(int shipLength) {
		// makes random object
		Random random = new Random();

		// variables
		int y, x, direction;

		whileLoop: while (true) {

			// makes random coordinates and random direction
			y = random.nextInt(10);
			x = random.nextInt(10);
			direction = random.nextInt(2);

			// checks if the coordinates intersect other boats or if boat crosses the grid
			boolean check = checkPlace(y, x, shipLength, direction);
			for (int i = 0; i < shipLength; i++) {

				// if place is good, places ship given its direction
				if (check == true) {

					if (direction == 0) {
						computer_grid[y + i][x] = "B";

					} else {
						computer_grid[y][x + i] = "B";
					}

					// else if place not good repeats whileLoop
				} else {
					continue whileLoop;
				}

			}

			break;
		}

	}

	/**
	 * Method that checks if a battleShip is too long or intersecting another ship
	 */

	public static boolean checkPlace(int y, int x, int shipLength, int direction) {
		for (int i = 0; i < shipLength; i++) {

			// if direction is 0 checks if "B" is below its coordinates. if yes returns
			// false
			if (direction == 0) {
				if (computer_grid[y + i][x].equals("B") || (shipLength + y) >= 10) {
					return false;
				}
				// if direction is 1 checks if "B" is right to its coordinates. if yes returns
				// false
			} else {
				if (computer_grid[y][x + i].equals("B") || (shipLength + x) >= 10) {
					return false;
				}

			}

		}
		return true;
	}

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		// fills grid and places ships
		fillGrid();

		// variables
		int correct = 0;
		int counter = 0;

		// repeats until all ships are found
		while (correct != 14) {

			// tries code
			try {
				// prints user_grid
				printGrid();

				System.out.println("\n");

				// asks for row and stores it
				System.out.print("Enter Row: ");
				int x = input.nextInt() - 1;

				// asks for column and stores it
				System.out.print("Enter Column: ");
				int y = input.nextInt() - 1;

				// checks if user already entered this coordinate
				if (!user_grid[y][x].equals("H") && !user_grid[y][x].equals("O")) {

					// checks if user coordinates = to ships location
					if (computer_grid[y][x].equals("B")) {

						// prints "H" for hit on user_grid
						user_grid[y][x] = "H";
						correct++;
						
					} else {
						// prints "O" for miss on user_grid
						user_grid[y][x] = "O";
					}
					// counter goes up
					counter++;
					System.out.println();

					// if user already entered this coordinate
					// program does not count it as a guess and informs user
				} else {
					System.out.println("\nYou Have Already Entered This Coordinate!");
					continue;
				}
				// if user enters a letter or number > 10
			} catch (Exception e) {
				System.out.println("\nTry Again!!!\n************");
				input.nextLine();

			}

		}
		printGrid();
		System.out.println("\n***********");
		System.out.println("YOU WON!!!!");
		System.out.println("Tries: " + counter);

	}

}
