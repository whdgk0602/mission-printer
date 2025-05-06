package mission;

import mission.model.Printer;
import mission.control.PrinterController;
import mission.utils.AsciiGenerator;
import mission.view.PrinterView;

public class Application {
    public static void main(String[] args) {
        AsciiGenerator asciiGenerator = new AsciiGenerator();
        Printer printer = new Printer(asciiGenerator);
        PrinterView view = new PrinterView();
        PrinterController controller = new PrinterController(printer, view);

        controller.start();
    }
}