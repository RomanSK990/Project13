package databaseforhobbyobjects;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the game database!");
        boolean running = true;
        Collection collection = new Collection();

        while (running) {
            System.out.println("Choose an option:");
            System.out.println("1. Print game list");
            System.out.println("2. Add game");
            System.out.println("3. Remove game");
            System.out.println("4. Sort game by rating");
            System.out.println("5. Search game by name");
            System.out.println("6. Search game by release year");
            System.out.println("7. Show detailed information");
            System.out.println("8. Read list from the file");
            System.out.println("9. Save list to the file");
            System.out.println("0. Exit");
            int option = in.nextInt();
            in.nextLine();

            switch (option) {
                case 1:
                    collection.printList();
                    break;
                case 2:
                    Item newGame = new Item("Title", "description", 1234, 0.0);
                    System.out.println("Enter game name:");
                    String name = in.nextLine();
                    newGame.setTitle(name);

                    System.out.println("Enter description:");
                    String description = in.nextLine();
                    newGame.setDescription(description);

                    System.out.println("Enter release year:");
                    int year = in.nextInt();
                    in.nextLine();
                    newGame.setReleaseYear(year);

                    System.out.println("Enter rating from 0 to 100 (0.0 will be set if rating is not in this range):");
                    double rating = in.nextDouble();
                    in.nextLine();
                    newGame.setRating(rating);

                    collection.add(newGame);
                    break;
                case 3:
                    System.out.println("What index it has in a list?");
                    int index = in.nextInt();
                    collection.remove(index);
                    break;
                case 4:
                    collection.sort();
                    break;
                case 5:
                    System.out.println("Enter game name:");
                    String gameName = in.nextLine();
                    collection.searchByTitle(gameName);
                    break;
                case 6:
                    System.out.println("Enter release year:");
                    int releaseYear = in.nextInt();
                    collection.searchByReleaseYear(releaseYear);
                    break;
                case 7:
                    collection.printAll();
                    break;
                case 8:
                    collection.readFromFile();
                    break;
                case 9:
                    collection.saveToFile();
                    break;
                case 0:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }
}