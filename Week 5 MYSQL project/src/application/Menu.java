package application;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import dao.CardsDao;
import entity.Cards;

public class Menu {

	private CardsDao cardsDao = new CardsDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList("Show Cards", "Show a Card", "Add Card", "Update Card", "Delete Card");

	public void start() {
		String selection = "";

		do {
			printMenu();
			selection = scanner.nextLine();

			try {
				if (selection.equals("1")) {
					showCards();
				} else if (selection.equals("2")) {
					showCard();
				} else if (selection.equals("3")) {
					addCard();
				} else if (selection.equals("4")) {
					updateCard();
				} else if (selection.equals("5")) {
					deleteCard();
				} 
					
			} catch (SQLException e) {
				e.printStackTrace();
			}

			System.out.println("Press enter to continue....\nPress -1 to exit");
			scanner.nextLine();
		} while (!selection.equals("-1"));
	}

	private void printMenu() {
		System.out.println("Select an Option:\n---------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ") " + options.get(i));

		}
		
		System.out.println("----------------------\nPress -1 to exit");
	}

	private void showCards() throws SQLException {
		List<Cards> cards = cardsDao.getCards();
		for (Cards card : cards) {
			System.out.println(card.getCardsId() + " :" + card.getName());
		}
	}

	private void showCard() throws SQLException {
		System.out.print("Enter card id:");
		int id = Integer.parseInt(scanner.nextLine());
		Cards card = cardsDao.getCardsById(id);
		System.out.println(card.getCardsId() + ": " + card.getName() + ": " + card.getType() + ": "
				+ card.getDescription() + ": " + card.getStats());

	}

	private void addCard() throws SQLException {
		System.out.print("Enter name of card:");
		String name = scanner.nextLine();
		System.out.print("Enter type of card:");
		String type = scanner.nextLine();
		System.out.print("Enter description of card:");
		String description = scanner.nextLine();
		System.out.print("Enter stats of card:");
		String stats = scanner.nextLine();
		cardsDao.addCard(name, type, description, stats);
	}

	private void updateCard() throws SQLException {
		System.out.print("Enter card name:");
		String name = scanner.nextLine();
		System.out.print("Enter type:");
		String type = scanner.nextLine();
		System.out.print("Enter description:");
		String description = scanner.nextLine();
		System.out.print("Enter stats:");
		String stats = scanner.nextLine();
		System.out.print("Enter card id to update:");
		int id = Integer.parseInt(scanner.nextLine());
		cardsDao.updateCardById(name, type, description, stats, id);
	}

	private void deleteCard() throws SQLException {
		System.out.print("Enter card id to delete:");
		int id = Integer.parseInt(scanner.nextLine());
		cardsDao.deleteCardById(id);
	}
}
