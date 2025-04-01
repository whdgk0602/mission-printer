package mission.view;

import mission.utils.AsciiGenerator;
import java.util.List;
import java.util.Scanner;

public class PrintView {
    private final Scanner scanner = new Scanner(System.in);

    public int showMenu() {
        System.out.println("사용할 기능을 입력해주세요. 1) 출력, 2) 잉크 잔량 확인, 3) 잉크 교체, 4) 프로그램 종료");
        System.out.print(">> ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String getInputText() {
        System.out.println("출력할 문자를 입력해주세요.");
        System.out.print(">> ");
        return scanner.nextLine();
    }

    public int getPaperSize() {
        System.out.println("용지 크기를 입력해주세요.");
        System.out.print(">> ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void displayAscii(String input, AsciiGenerator generator, int size) {
        StringBuilder output = new StringBuilder();
        String lineSeparator = System.lineSeparator();

        for (int row = 0; row < 5; row++) { // 기본 아스키 아트는 5줄
            for (int i = 0; i < size; i++) { // 세로 크기 확장
                for (char ch : input.toCharArray()) {
                    int num = Character.getNumericValue(ch);
                    if (num < 0 || num > 9) {
                        System.out.println("[ERROR] 유효하지 않은 문자: " + ch);
                        return;
                    }
                    List<String> ascii = generator.getNumberAsciiDesign(num);
                    String scaledLine = scaleLine(ascii.get(row), size);
                    output.append(scaledLine).append("  ");
                }
                output.append(lineSeparator);
            }
        }

        output.append(lineSeparator).append("출력이 완료되었습니다.").append(lineSeparator);
        System.out.println(output.toString());
    }

    /**
     * 가로 크기를 확장하는 메서드
     */
    private String scaleLine(String line, int size) {
        StringBuilder scaled = new StringBuilder();
        for (char ch : line.toCharArray()) {
            scaled.append(String.valueOf(ch).repeat(size)); // 가로 크기 확장
        }
        return scaled.toString();
    }


    public void displayInk(int inkLevel) {
        System.out.println("잉크 잔량 : " + inkLevel + "/1000");
    }

    public void displayInkLow() {
        System.out.println("\n잉크가 부족해 출력이 중단되었습니다.\n");
    }

    public void displayInkRefilled() {
        System.out.println("잉크를 교체하였습니다.\n");
    }
}
