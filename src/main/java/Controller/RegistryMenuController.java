package Controller;


import Model.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RegistryMenuController {

    public static String signin(String[] enteredUser) {
        String tempUsername = enteredUser[0];
        String tempNickName= enteredUser[1];
//        (String username,String nickname,String password,String email,String passwordRecoveryQuestion,int passType,int character)
        String tempPassword = enteredUser[2];
        String tempMail = enteredUser[3];
        String tempPasswordRecoveryQuestion = enteredUser[4];
        int tempPasswordRecoveryType = Integer.parseInt(enteredUser[5]);
        int tempCharacter = Integer.parseInt(enteredUser[6]);
        if (!isUsernameCorrect(tempUsername)) {
            return "username's format is invalid!";
        }
        else if (!isNicknameCorrect(tempNickName)) {
            return "nickname's format is invalid!";
        }
        else if (!isPasswordCorrect(tempPassword)) {
            return "password is weak!";
        }
        else if (!isMailValid(tempMail)) {
            return "Mail format is incorrect";
        }
        else if (isUserValid(tempUsername)) {
            return "username already exists!";
        }
        else {
            User user = new User(tempUsername, tempNickName, tempPassword,
                    tempMail, tempPasswordRecoveryQuestion, tempPasswordRecoveryType, tempCharacter);
            ApplicationData.addToUserArrayList(user);
            return "user successfully created!";
        }
    }
    public static String login(String[] enteredUser) {
        String tempUsername = enteredUser[0];
        String tempPassword = enteredUser[1];
        if (!isUsernameCorrect(tempUsername)) {
            return "username's format is invalid!";
        }
        else if (!isUserValid(tempUsername)) {
            return "username doesn't exist!";
        }
        else {
            for (int i = 0; i < ApplicationData.getUserArrayList().size(); i++) {
                if (ApplicationData.getUserArrayList().get(i).getUsername().equals(tempUsername)) {
                    if (!ApplicationData.getUserArrayList().get(i).getPassword().equals(tempPassword)) {
                        return "incorrect password!";
                    }
                    else {
                        ApplicationData.setHost(ApplicationData.getUserArrayList().get(i));
                        return "user successfully logged in!";
                    }
                }
            }
        }
        return null;
    }
    public static ArrayList<String> userList() {
        ArrayList<String> outcome = new ArrayList<>();
        outcome.add(ApplicationData.getUserArrayList().size() + " users have registered!\n");
        for (int i = 0; i < ApplicationData.getUserArrayList().size(); i++) {
            int j = i+1;
            outcome.add(j + " - " + ApplicationData.getUserArrayList().get(i).getNickname() + "\n");
        }
        return outcome;
    }
    public static boolean isUsernameCorrect(String tempUsername) {
        for (int i = 0; i < tempUsername.length(); i++) {
            if (!((tempUsername.charAt(i) >= 48 && tempUsername.charAt(i) < 58)
                    || (tempUsername.charAt(i) > 64 && tempUsername.charAt(i) < 91)
                    || (tempUsername.charAt(i) > 96 && tempUsername.charAt(i) < 123)
                    || tempUsername.charAt(i) == 32)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isNicknameCorrect(String tempUsername) {
        for (int i = 0; i < tempUsername.length(); i++) {
            if (!((tempUsername.charAt(i)>64 && tempUsername.charAt(i)<91)
                    || (tempUsername.charAt(i)>96 && tempUsername.charAt(i)<123)
                    || tempUsername.charAt(i) == 32)) {
                return false;
            }
        }
        return true;
    }
    public static boolean isUserValid(String tempUsername) {
        for (User user : ApplicationData.getUserArrayList()) {
            if (user.getUsername().equals(tempUsername))
                return true;
        }
        return false;
    }

    public static boolean isPasswordCorrect(String tempPassword) {
        boolean digLet=false,upLet=false,loLet=false,charLet=false,cor=true;
        if (tempPassword.length()<8 || tempPassword.length()>32)
            return false;
        for (int i = 0; i < tempPassword.length(); i++) {
            if (tempPassword.charAt(i) > 47 && tempPassword.charAt(i) < 58)
                digLet = true;
            else if (tempPassword.charAt(i) > 64 && tempPassword.charAt(i) < 91)
                upLet = true;
            else if (tempPassword.charAt(i) > 96 && tempPassword.charAt(i) < 123)
                loLet = true;
            else if (tempPassword.charAt(i)== 33 || tempPassword.charAt(i)==46
                    || (tempPassword.charAt(i)>35 && tempPassword.charAt(i)<39)
                    || (tempPassword.charAt(i)>39 && tempPassword.charAt(i)<43)
                    || tempPassword.charAt(i)==64 || tempPassword.charAt(i)==91
                    || tempPassword.charAt(i)==93 || tempPassword.charAt(i)==94
                    || tempPassword.charAt(i)==123 || tempPassword.charAt(i)==125)
                charLet = true;
            else
                cor = false;
        }
        if(!digLet || !upLet || !loLet || !charLet || !cor)
            return false;
        return true;
    }
    public static boolean isMailValid(String tempMail) {
        Pattern[] pattern = new Pattern[1];
        pattern[0] = Pattern.compile("(\\S+@\\S+\\.\\S+)");
        return tempMail.matches(String.valueOf(pattern[0]));
    }

}
