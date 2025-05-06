package mission.view;

import java.util.Scanner;

public class PrinterView {
    private final Scanner scanner = new Scanner(System.in);

    public String getUserInput(String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.out.println("[ERROR] " + message);
    }
}