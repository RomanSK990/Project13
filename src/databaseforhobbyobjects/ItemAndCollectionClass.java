package databaseforhobbyobjects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

class Item {
    private String title;
    private String description;
    private int releaseYear;
    private double rating;

    public Item(String title, String description, int releaseYear, double rating) {
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.rating = rating;
    }

    public String getDescription() {
        return "Title: " + title +
               "\nRelease year: "  + releaseYear +
               "\nRating: " + rating +
               "\nDescription: " + description;
    }

    public String getTitle() {
        return title;
    }

    public String getGameDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public int getReleaseYear() {
        return releaseYear;
    }
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        if (rating >= 0 && rating <= 100) {
            this.rating = rating;
        }
    }
    public String overallRating() {
        if (rating >= 90) return "Awesome";
        if (rating >= 80) return "Better";
        if (rating >= 70) return "Good";
        if (rating >= 60) return "Average";
        if (rating >= 50) return "Low";
        return "Worst";
    }
}
class Collection {
    private Item[] items = new Item[0];

    public void add(Item item) {
        items = Arrays.copyOf(items, items.length + 1);
        items[items.length - 1] = item;
    }
    public void remove(int i) {
        if (i < 0 || i >= items.length) {
            System.out.println("Invalid index");
            return;
        }
        Item[] newItems = new Item[items.length - 1];

        for (int j = 0, a = 0; j < items.length; j++) {
            if (j != i) {
                newItems[a] = items[j];
                a++;
            }
        }
        items = newItems;
    }
    public void printOne(int i) {
        if (i < 0 || i >= items.length) {
            System.out.println("Invalid index");
            return;
        }
        System.out.println(items[i].getDescription());
        System.out.println(items[i].overallRating());
    }
    public void printAll() {
        for (int i = 0; i < items.length; i++) {
            printOne(i);
        }
    }
    public void printList() {
        for (int i = 0; i < items.length; i++) {
            System.out.println("Index: " + i);
            System.out.println("Game: " + items[i].getTitle() + " (" + items[i].getReleaseYear() + ") " + "Rating - " + items[i].getRating());
            System.out.println("Overall rating: " + items[i].overallRating());
        }
    }
    public void sort() {
        System.out.println("Sorted games by rating");
        for (int i = 0; i < items.length - 1; i++) {
            for (int j = 0; j < items.length - i - 1; j++) {
                if (items[j].getRating() < items[j + 1].getRating()) {
                    Item temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }
        printList();
    }
    public void searchByReleaseYear(int year) {
        boolean found = false;
        System.out.println("Searching for release year: " + year);
        for (int i = 0; i < items.length; i++) {
            if (items[i].getReleaseYear() == year) {
                System.out.println("Index: " + i);
                System.out.println(items[i].getTitle());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matches found");
        }
    }
    public void searchByTitle(String title) {
        boolean found = false;
        System.out.println("Searching for title: " + title);
        for (int i = 0; i < items.length; i++) {
            if (items[i].getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println("Index: " + i);
                System.out.println(items[i].getDescription());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matches found");
        }
    }
    public void readFromFile() {
        Path file = Path.of("games.txt");
        if (!Files.exists(file)) {
            System.out.println("File not found: " + file);
            return;
        }
        try {
            List<String> lines = Files.readAllLines(file, StandardCharsets.UTF_8);
            for (int i = 0; i < lines.size(); i++) {
                System.out.println(lines.get(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveToFile() {
        List<String> lines = new ArrayList<>();
        for (int i = 0; i < items.length; i++) {
            String line = ("Game: " + items[i].getTitle() + " (" + items[i].getReleaseYear() + ") Description: " + items[i].getGameDescription() + " Rating - " + items[i].getRating() + " Overall rating:" + items[i].overallRating());
            lines.add(line);
        }
        Path file = Path.of("games.txt");
        try {
            Files.write(file, lines);
            System.out.println("Saved games to " + file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
