package main;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Menu menu = new Menu(scanner);
		while (true) {
			menu.printMainMenu();
			int selectedMenu = menu.getUserSelect();
			menu.excuteMenu(selectedMenu);
		}

	}

}
