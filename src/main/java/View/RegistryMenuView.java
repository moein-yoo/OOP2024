package View;

import Model.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistryMenuView extends Menu{
    public RegistryMenuView(ArrayList<User> users , User userInUse, int index) {
        super(users,userInUse,index);
        userArrayList = users;
        RegistryMenuView.userInUse = userInUse;
        userInUseIndex = index;
    }
    public void run() {
        Pattern[] patterns = new Pattern[5];
        patterns[0] = Pattern.compile("exit");
        patterns[1] = Pattern.compile("show current menu");
        //(String username,String nickname,String password,String email,String passwordRecoveryQuestion,int character)
        patterns[2] = Pattern.compile("register u (?<username>[\\S]+) n (?<nickname>[\\S\\s]+) p (?<password>[\\S]+) em (?<email>[\\S]+))");
        patterns[3] = Pattern.compile("login u (?<username>[\\S]+) p (?<password>[\\S]+)");
        patterns[4] = Pattern.compile("list of users");
        input = scanner.next();
        while (!input.equals("exit")){
            boolean ejra = false;
            input += scanner.nextLine();
            if(input.matches(String.valueOf(patterns[0]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[0]));
                matcher.find();
                return;
            }
            else if (input.matches(String.valueOf(patterns[1]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[0]));
                matcher.find();
                ejra = true;
                System.out.println("Register Menu");
            }
            else if (input.matches(String.valueOf(patterns[2]))) {
                Matcher matcher = getCommandMatcher(input,String.valueOf(patterns[2]));
                matcher.find();
                signin(matcher);
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[3]))) {
                Matcher matcher = getCommandMatcher(input,String.valueOf(patterns[3]));
                matcher.find();
                if (login(matcher)) {
                    MainMenu mainMenu = new MainMenu(userArrayList,userInUse,userInUseIndex);
                    if (!mainMenu.run())
                        return;
                }
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[4]))) {
                Matcher matcher = getCommandMatcher(input,String.valueOf(patterns[4]));
                matcher.find();
                userList(matcher);
                ejra = true;
            }
            if(!ejra) {
                System.out.println("invalid command");
            }
            input = scanner.next();
        }

    }
    public static void signin(Matcher matcher) {
        String tempUsername = matcher.group("username");
        String tempPassword = matcher.group("password");
        String tempNickname = matcher.group("nickname");
        if (!isUsernameCorrect(tempUsername)) {
            System.out.println("username's format is invalid!");
        }
        else if (!isNicknameCorrect(tempNickname)) {
            System.out.println("nickname's format is invalid!");
        }
        else if (!isPasswordCorrect(tempPassword)) {
            System.out.println("password is weak!");
        }
        else if (isUserValid(tempUsername)) {
            System.out.println("username already exists!");
        }
        else {
            User user = new User(tempUsername,tempPassword,tempNickname);
            userArrayList.add(user);
            System.out.println("user successfully created!");
        }
    }
    public static boolean login(Matcher matcher) {
        String tempUsername = matcher.group("username");
        String tempPassword = matcher.group("password");
        if (!isUsernameCorrect(tempUsername)) {
            System.out.println("username's format is invalid!");
        }
        else if (!isUserValid(tempUsername)) {
            System.out.println("username doesn't exist!");
        }
        else {
            for (int i = 0; i < userArrayList.size(); i++) {
                if (userArrayList.get(i).getUsername().equals(tempUsername)) {
                    if (!userArrayList.get(i).getPassword().equals(tempPassword)) {
                        System.out.println("incorrect password!");
                    }
                    else {
                        userInUse = userArrayList.get(i);
                        userInUseIndex = i;
                        System.out.println("user successfully logged in!");
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void userList(Matcher matcher) {
        System.out.printf("%d users have registered!\n",userArrayList.size());
        for (int i = 0; i < userArrayList.size(); i++) {
            System.out.printf("%d - %s\n",i+1,userArrayList.get(i).getNickname());
        }
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
        for (User user : userArrayList) {
            if (user.getUsername().equals(tempUsername))
                return true;
        }
        return false;
    }
}
