package mission.control;

import mission.utils.AsciiGenerator;
import mission.view.PrinterView;

public class PrinterController {
    private final Printer printer;
    private final PrinterView view;

    public PrinterController(Printer printer, PrinterView view) {
        this.printer = printer;
        this.view = view;
    }

    public void start() {
        view.displayMessage("프린터를 실행합니다.");
        while (true) {
            String input = view.getUserInput("사용할 기능을 입력해주세요. 1) 출력, 2) 잉크 잔량 확인, 3) 잉크 교체, 4) 프로그램 종료");

            if (input.equals("1")) {
                printer.print();
            }
            if (input.equals("2")) {
                printer.checkInk();
            }
            if (input.equals("3")) {
                printer.replaceInk();
            }
            if (input.equals("4")) {
                view.displayMessage("프로그램을 종료합니다.");
                break;
            }
        }
    }
}
