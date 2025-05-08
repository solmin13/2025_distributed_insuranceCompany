package main;

public class Main {
	public static void main(String[] args) {
		java.util.Scanner scanner = new java.util.Scanner(System.in);
		Menu menu = new Menu(scanner);
		while (true) {
			menu.printMainMenu();
			int selectedMenu = menu.getUserSelect();
			menu.excuteMenu(selectedMenu);
		}

	}

}
