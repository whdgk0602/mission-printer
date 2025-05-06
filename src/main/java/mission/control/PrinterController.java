package mission.control;

import mission.model.Printer;
import mission.view.PrinterView;

public class PrinterController {
    private final Printer printer;
    private final PrinterView view;

    public static final String PRINT_OPTION = "1";
    public static final String CHECK_INK_OPTION = "2";
    public static final String REPLACE_INK_OPTION = "3";
    public static final String EXIT_OPTION = "4";

    public PrinterController(Printer printer, PrinterView view) {
        this.printer = printer;
        this.view = view;
    }

    public void start() {
        view.displayMessage("프린터를 실행합니다.");
        while (true) {
            String input = view.getUserInput("사용할 기능을 입력해주세요. 1) 출력, 2) 잉크 잔량 확인, 3) 잉크 교체, 4) 프로그램 종료");

            switch (input) {
                case PRINT_OPTION -> handlePrint();
                case CHECK_INK_OPTION -> printer.checkInk();
                case REPLACE_INK_OPTION -> printer.replaceInk();
                case EXIT_OPTION -> {
                    view.displayMessage("프로그램을 종료합니다.");
                    return;
                }
                default -> view.displayMessage("올바른 번호를 입력해주세요.");
            }
        }
    }

    private void handlePrint() {
        String input = view.getUserInput("출력할 문자를 입력해주세요.");
        int paperSize = getPaperSize();

        try {
            printer.print(input, paperSize);
        } catch (IllegalStateException e) {
            view.displayError(e.getMessage());
        }
    }

    private int getPaperSize() {
        while (true) {
            String paperSizeInput = view.getUserInput("용지 크기를 입력해주세요.");
            try {
                return Integer.parseInt(paperSizeInput);
            } catch (NumberFormatException e) {
                view.displayMessage("숫자만 입력 가능합니다.");
            }
        }
    }
}