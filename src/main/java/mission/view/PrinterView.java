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

    public void displayInkStatus(int ink, int maxInk) {
        System.out.println("잉크 잔량: " + ink + "/" + maxInk);
    }

    public void displayInkReplaced() {
        System.out.println("잉크를 교체하였습니다.");
    }

    public void displayInkLow() {
        System.out.println("[ERROR] 잉크가 부족합니다. 인쇄할 수 없습니다.");
    }

    public void displayAscii(String ascii) {
        System.out.println(ascii);
    }
}
