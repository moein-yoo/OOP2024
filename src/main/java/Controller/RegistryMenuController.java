package Controller;


import Model.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

public class RegistryMenuController {

    public static String signUp(String[] enteredUser) {
        String tempUsername = enteredUser[0];
        String tempNickName= enteredUser[1];
//        (String username,String nickname,String password,String email,String passwordRecoveryQuestion,int passType,int character)
        String tempPassword = enteredUser[2];
        String tempMail = enteredUser[3];
        String tempPasswordRecoveryQuestion = enteredUser[5];
        int tempPasswordRecoveryType = Integer.parseInt(enteredUser[4]);
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
                    tempMail, tempPasswordRecoveryQuestion, tempPasswordRecoveryType, "nothing");
            ApplicationData.addToUserArrayList(user);
            User.addNewUserToSQL(user);
            return "user successfully created!";
        }
    }
    public static String login(String[] enteredUser) {
        String tempUsername = enteredUser[0];
        String tempPassword = enteredUser[1];
        if (tempPassword.equals("admin") && tempUsername.equals("admin"))
            return "Admin user detected!, Logging into AdminMenu";
        else if (!isUserValid(tempUsername)) {
            return "username doesn't exist!";
        }
        else {
            for (int i = 0; i < ApplicationData.getUserArrayList().size(); i++) {
                if (ApplicationData.getUserArrayList().get(i).getUsername().equals(tempUsername)) {
                    if (!ApplicationData.getUserArrayList().get(i).getPassword().equals(tempPassword)) {
                        System.out.println("Password is not valid try again after 5 seconds!");
                        for (int j = 0; j < 5; j++) {
                            int finalI = i;
                            try {
                                Thread.sleep(1000); // Delay for 1 second
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }finally {
                                System.out.println(5-finalI + " seconds left for your next try");
                            }
                        }
                        return "Now you should do it from first!";
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
    public static String loginGraphic(String[] enteredUser) {
        String tempUsername = enteredUser[0];
        String tempPassword = enteredUser[1];
        if (tempPassword.equals("admin") && tempUsername.equals("admin"))
            return "Admin user detected!, Logging into AdminMenu";
        else if (!isUserValid(tempUsername)) {
            return "username doesn't exist!";
        }
        else {
            for (int i = 0; i < ApplicationData.getUserArrayList().size(); i++) {
                if (ApplicationData.getUserArrayList().get(i).getUsername().equals(tempUsername)) {
                    if (!ApplicationData.getUserArrayList().get(i).getPassword().equals(tempPassword)) {
                        return "Password is not valid try again after 5 seconds!";
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
    public static String loginGraphicForGuest(String[] enteredUser) {
        String tempUsername = enteredUser[0];
        String tempPassword = enteredUser[1];
        if (tempPassword.equals("admin") && tempUsername.equals("admin"))
            return "Admin user detected!, Logging into AdminMenu";
        else if (!isUserValid(tempUsername)) {
            return "username doesn't exist!";
        }
        else {
            for (int i = 0; i < ApplicationData.getUserArrayList().size(); i++) {
                if (ApplicationData.getUserArrayList().get(i).getUsername().equals(tempUsername)) {
                    if (!ApplicationData.getUserArrayList().get(i).getPassword().equals(tempPassword)) {
                        return "Password is not valid try again after 5 seconds!";
                    }
                    else {
                        ApplicationData.setGuest(ApplicationData.getUserArrayList().get(i));
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
        if (tempUsername.matches("[A-Za-z0-9_.]*"))
            return true;
        return false;
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
        if (tempPassword.matches("[A-Za-z0-9@!#$%^&*]*") && tempPassword.length()<=40 && tempPassword.length()>=8) {
            return true;
        }
        return false;
    }
    public static boolean isMailValid(String tempMail) {
        Pattern[] pattern = new Pattern[1];
        pattern[0] = Pattern.compile("(\\S+@\\S+\\.\\S+)");
        return tempMail.matches(String.valueOf(pattern[0]));
    }
    public static String randomPasswordMaker() {
        String string = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
        Random random = ApplicationData.getRandom();
        StringBuilder tempPassword = new StringBuilder();
        tempPassword.append(string.charAt(random.nextInt(26)));
        tempPassword.append(string.charAt(random.nextInt(26, 52)));
        tempPassword.append(string.charAt(random.nextInt(52, 62)));
        tempPassword.append(string.charAt(random.nextInt(62, 70)));
        for (int i = 0; i < 4; i++) {
            tempPassword.append(string.charAt(random.nextInt(70)));
        }
        return tempPassword.toString();
    }

}
