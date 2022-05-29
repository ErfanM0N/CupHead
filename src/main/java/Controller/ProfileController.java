package Controller;

import application.App;
import model.Game;

public class ProfileController {

    public static void logout() {
        Game.getInstance().setLoginUser(null);
        App.changeMenu("SignupPage");
    }
}
