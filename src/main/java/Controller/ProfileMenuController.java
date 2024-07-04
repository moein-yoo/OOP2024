package Controller;

import Model.ApplicationData;
import Model.User;

import java.util.regex.Matcher;

import static View.Menu.isPasswordCorrect;

public class ProfileMenuController {

    public static  String changePassword(Matcher matcher, User user) {
        String tempOldPassword = matcher.group("oldpassword");
        String tempNewPassword = matcher.group("newpassword");
        if (!user.getPassword().equals(tempOldPassword)) {
            return "oldPassword is incorrect!";
        }
        else if (!isPasswordCorrect(tempNewPassword)) {
            return "newPassword is weak!";
        }
        else {
            System.out.println("password changed!");
            user.setPassword(tempNewPassword);
        }
        return null;
    }
    public static boolean  deleteAccount(Matcher matcher, User user) {
        String tempPassword = matcher.group("currentPassword");
        if (user.getPassword().equals(tempPassword)) {
            ApplicationData.removeFromUserArrayList(user);
            System.out.println("account deleted!");
            return true;
        }
        else {
            System.out.println("password is incorrect!");
            return false;
        }
    }

    public static String changeUsername(String enteredUser) {
        if (!RegistryMenuController.isUsernameCorrect(enteredUser)) {
            return "username's format is invalid!";
        }
        else if (RegistryMenuController.isUserValid(enteredUser)) {
            return "username already exists!";
        }
        else {
            ApplicationData.getHost().setUsername(enteredUser);
            return "username changed to" + enteredUser + " successfully!";
        }
    }
    public static String changeNickname(String enteredUser) {
        if (!RegistryMenuController.isNicknameCorrect(enteredUser)) {
            return "nickname's format is invalid!";
        }
        else {
            ApplicationData.getHost().setUsername(enteredUser);
            return "nickname changed to " + enteredUser + " successfully!";
        }
    }
    public static String changeEmail(String enteredUser) {
        if (!RegistryMenuController.isMailValid(enteredUser)) {
            return "email's format is incorrect";
        }
        else {
            ApplicationData.getHost().setEmail(enteredUser);
            return "email changed to " + enteredUser + "successfully!";
        }
    }
}
