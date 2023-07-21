import java.util.Random;
import java.util.Scanner;

public class MineSweeper {

	Scanner input = new Scanner(System.in);
	
	Random randomMineNumber = new Random();
	
	int row;
	int column;
	String[][] map;
	String[][] frame;
	int mineNumber;

	public void run() {
		System.out.println(" = Mayın Tarlası Oyununa Hoşgeldiniz = ");
		System.out.print("Satır Sayısı Giriniz : ");
		row = input.nextInt();
		System.out.print("Sütun Sayısı Giriniz : ");
		column = input.nextInt();

		mineNumber = (row * column) / 4;

		map = new String[row][column];
		frame = new String[row][column];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				frame[i][j] = "-";
				map[i][j] = "-";
			}
		}

		while (mineNumber > 0) {
			int rowMineNumber = randomMineNumber.nextInt(row);
			int columnMineNumber = randomMineNumber.nextInt(column);

			if (map[rowMineNumber][columnMineNumber].equals("-")) {
				map[rowMineNumber][columnMineNumber] = "*";
				mineNumber--;
			}

		}
		printFrame();

		playCheck();
	}

	public void playCheck() {
		
		boolean finish = false;
		
		while (!finish) {
			System.out.print("Satır Sayısı Giriniz : ");
			int selectedRow = input.nextInt();
			
			System.out.print("Sütun Sayısı Giriniz : ");
			int selectedColumn = input.nextInt();

			int mineNumber = 0;

			if (selectedRow < row && selectedColumn < column) {
				if (map[selectedRow][selectedColumn].equals("-") && frame[selectedRow][selectedColumn].equals("-")) {
					for (int i = selectedRow - 1; i < selectedRow + 2; i++) {
						for (int j = selectedColumn - 1; j < selectedColumn + 2; j++) {
							if (i >= 0 && j >= 0 && i < row && j < column && map[i][j].equals("*")) {
								mineNumber++;
								frame[selectedRow][selectedColumn] = Integer.toString(mineNumber);
							} else {
								frame[selectedRow][selectedColumn] = Integer.toString(mineNumber);
							}

						}
					}
					printFrame();
					if (!checkWin()) {
						System.out.println("Tebrikler! Kazandınız... :)");
						printMap();
						finish = true;
					}

				} else if (map[selectedRow][selectedColumn].equals("*")) {
					System.out.println("Kaybettiniz... ");
					printMap();
					finish = true;
				} else if (!frame[selectedRow][selectedColumn].equals("-")) {
					System.out.println("Aynı Sayı Girilemez! Farklı Bir Sayı Giriniz...");
				}

			} else {
				System.out.println("Yanlış Sayı Girdiniz. Tekrar Deneyiniz!");
			}
		}
	}

	public void printMap() {

		for (String[] row : map) {
			for (String column : row) {
				System.out.print(column + " ");
			}
			System.out.println();
		}
		System.out.println("====================");
	}

	public void printFrame() {
		for (String[] row : frame) {
			for (String column : row) {
				System.out.print(column + " ");
			}
			System.out.println();
		}
		System.out.println("====================");
	}

	boolean checkWin() {
		int emptyCell = 0;
		int minedCell = 0;
		for (int i = 0; i < frame.length; i++) {
			for (int j = 0; j < frame[i].length; j++) {
				if (frame[i][j].equals("-")) {
					emptyCell++;
				}
				if (map[i][j].equals("*")) {
					minedCell++;
				}
			}
		}
		if (emptyCell == minedCell) {
			return false;
		}
		return true;
	}

}
