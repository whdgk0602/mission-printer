package mission.model;

import mission.utils.AsciiGenerator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Printer {
    private static final int MAX_INK = 1000;
    private int ink = MAX_INK;
    private final AsciiGenerator asciiGenerator;

    public Printer(AsciiGenerator asciiGenerator) {
        this.asciiGenerator = asciiGenerator;
    }

    public void print(String input, int paperSize) {
        consumeInk(input.length(), paperSize);
        String ascii = generateAscii(input);
        System.out.println(ascii);
        System.out.println("잉크 잔량: " + ink + "/" + MAX_INK);
    }

    public void checkInk() {
        System.out.println("잉크 잔량: " + ink + "/" + MAX_INK);
    }

    public void replaceInk() {
        ink = MAX_INK;
        System.out.println("잉크를 교체하였습니다.");
    }

    private void consumeInk(int inputLength, int paperSize) {
        int requiredInk = inputLength * paperSize * 5;
        if (ink < requiredInk) {
            throw new IllegalStateException("잉크가 부족하여 인쇄할 수 없습니다.");
        }
        ink -= requiredInk;
    }

    private String generateAscii(String input) {
        int numRows = asciiGenerator.getNumberAsciiDesign(0).size();

        return IntStream.range(0, numRows)
                .mapToObj(row -> input.chars()
                        .mapToObj(c -> {
                            int digit = Character.getNumericValue(c);
                            List<String> design = asciiGenerator.getNumberAsciiDesign(digit);
                            return design.get(row);
                        })
                        .collect(Collectors.joining(" ")))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}