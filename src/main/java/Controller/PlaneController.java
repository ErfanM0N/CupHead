package Controller;

import javafx.scene.input.KeyEvent;

public class PlaneController {
    private static PlaneController instance;

    public static PlaneController getInstance() {
        if (instance == null)
            instance = new PlaneController();
        return instance;
    }

    private PlaneController() {
    }


}
