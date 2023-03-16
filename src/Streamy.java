
import Models.Controller;
import Models.Model;
import GUI.customSwing.View;

import java.io.*;

public class Streamy {
    public static void main(String[] args) throws IOException {
        Model model = new Model();
        Controller controller = new Controller(model);
        new View(model, controller);
    }
}
