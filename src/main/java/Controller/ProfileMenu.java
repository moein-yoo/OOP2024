package Controller;

import Model.ApplicationData;
import Model.User;
import View.Menu;

import java.util.regex.Matcher;

import static View.Menu.isPasswordCorrect;

public class ProfileMenu {

    static String  changePassword(Matcher matcher, User user) {
        String tempOldPassword = matcher.group("oldpassword");
        String tempNewPassword = matcher.group("newpassword");
        if (!user.getPassword().equals(tempOldPassword)) {
            return "password is incorrect!";
        }
        else if (!isPasswordCorrect(tempNewPassword)) {
            return "password is weak!";
        }
        else {
            System.out.println("password changed!");
            user.setPassword(tempNewPassword);
        }
        return null;
    }
    static String  deleteAccount(Matcher matcher, User user) {
        String tempPassword = matcher.group("currentPassword");
        if (user.getPassword().equals(tempPassword)) {
            ApplicationData.removeFromUserArrayList(user);
            return "account deleted!";
        }
        else {
            return "password is incorrect!";
        }
    }
}
