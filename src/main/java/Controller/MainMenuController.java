package Controller;

import Model.ApplicationData;
import Model.User;
import View.GameMenuView;
import View.HistoryMenuView;
import View.ProfileMenuView;
import View.ShopMenuView;

public class MainMenuController {
    public static boolean goToGameMenu() {
        return GameMenuView.run();
    }

    public static boolean goToHistory() {
        return HistoryMenuView.run();
    }

    public static boolean goToShopMenu() {
        return ShopMenuView.run();
    }

    public static boolean goToProfile() {
        return ProfileMenuView.run();
    }

    public static void logout() {
        for (User user : ApplicationData.getUserArrayList()) {
            if (user.getUsername().equals(ApplicationData.getHost().getUsername())) {
                user = ApplicationData.getHost();
                break;
            }
        }
    }
}
