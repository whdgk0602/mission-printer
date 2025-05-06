package mission.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AsciiGenerator {
    private final List<List<String>> data;

    static private final List<String> fileNames = List.of(
            "zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine"
    );

    public AsciiGenerator() {
        data = new ArrayList<>();
        fileNames.forEach(this::loadAsciiFile);
    }

    private void loadAsciiFile(String fileName) {
        try {
            Path path = Paths.get(Objects.requireNonNull(
                    getClass().getClassLoader().getResource("numberAsciiDesign/" + fileName + ".txt")
            ).toURI());
            String content = Files.readString(path);

            List<String> lines = List.of(content.split("\\R"));
            data.add(lines);
        } catch (URISyntaxException | IOException e) {
            throw new IllegalStateException("[ERROR] 아스키 아트를 로드하지 못했습니다.");
        }
    }

    public List<String> getNumberAsciiDesign(int number) {
        if (number < 0 || number >= data.size()) {
            throw new IllegalStateException("[ERROR] 범위를 초과했습니다.");
        }
        return data.get(number);
    }
}