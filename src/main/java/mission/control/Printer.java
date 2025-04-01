package mission.control;

import mission.utils.AsciiGenerator;
import mission.view.PrinterView;

import java.util.List;

public class Printer {
    private static final int MAX_INK = 1000;
    private int ink = MAX_INK;
    private final AsciiGenerator asciiGenerator;
    private final PrinterView view;

    public Printer(AsciiGenerator asciiGenerator, PrinterView view) {
        this.asciiGenerator = asciiGenerator;
        this.view = view;
    }

    public void print() {
        String input = view.getUserInput("출력할 문자를 입력해주세요.");
        int paperSize = Integer.parseInt(view.getUserInput("용지 크기를 입력해주세요."));

        if (!canPrint(input.length(), paperSize)) {
            view.displayInkLow();
            return;
        }

        printAscii(input, paperSize);
        view.displayInkStatus(ink, MAX_INK);
    }

    public void checkInk() {
        view.displayInkStatus(ink, MAX_INK);
    }

    public void replaceInk() {
        ink = MAX_INK;
        view.displayInkReplaced();
    }

    private boolean canPrint(int inputLength, int paperSize) {
        int requiredInk = inputLength * paperSize * 5;
        if (ink < requiredInk) return false;
        ink -= requiredInk;
        return true;
    }

    private void printAscii(String input, int paperSize) {
        StringBuilder output = new StringBuilder();
        int numRows = asciiGenerator.getNumberAsciiDesign(0).size();

        for (int row = 0; row < numRows; row++) {
            for (char c : input.toCharArray()) {
                int digit = Character.getNumericValue(c);
                List<String> asciiLines = asciiGenerator.getNumberAsciiDesign(digit);
                output.append(asciiLines.get(row)).append(" ");
            }
            output.append(System.lineSeparator());
        }
        view.displayAscii(output.toString());
    }
}
