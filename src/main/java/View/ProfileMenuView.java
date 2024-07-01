package View;

import Model.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuView extends Menu {
    public ProfileMenuView(ArrayList<User> users , User userInUse, int index) {
        super(users,userInUse,index);
        userArrayList = users;
        RegistryMenuView.userInUse = userInUse;
        userInUseIndex = index;
    }
    public static boolean run() {
        Pattern[] patterns = new Pattern[6];
        patterns[0] = Pattern.compile("exit");
        patterns[1] = Pattern.compile("show current menu");
        patterns[2] = Pattern.compile("change password (?<oldpassword>[\\S]+) (?<newpassword>[\\S]+)");
        patterns[3] = Pattern.compile("show information");
        patterns[4] = Pattern.compile("remove account (?<currentPassword>[\\S]+)");
        patterns[5] = Pattern.compile("back");
        input = scanner.next();
        while (!input.equals("exit")){
            boolean ejra = false;
            input += scanner.nextLine();
            if(input.matches(String.valueOf(patterns[0]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[0]));
                matcher.find();
                return false;
            }
            else if (input.matches(String.valueOf(patterns[1]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[1]));
                matcher.find();
                ejra = true;
                System.out.println("Profile Menu");
            }
            else if (input.matches(String.valueOf(patterns[2]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[2]));
                matcher.find();
                changePassword(matcher);
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[3]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[3]));
                matcher.find();
                System.out.printf("username : %s\n",userInUse.getUsername());
                System.out.printf("nickname : %s\n",userInUse.getNickname());
                System.out.printf("money : %d\n",userInUse.getWallet());
                System.out.printf("highscore : %d\n",userInUse.getScore());
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[4]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[4]));
                matcher.find();
                ejra = true;
                if(deleteAccount(matcher))
                    return true;
            }
            else if (input.matches(String.valueOf(patterns[5]))) {
                Matcher matcher = getCommandMatcher(input, String.valueOf(patterns[5]));
                matcher.find();
                return true;
            }
            if(!ejra) {
                System.out.println("invalid command");
            }
            input = scanner.next();
        }
        return false;
    }
    static void changePassword(Matcher matcher) {
        String tempOldPassword = matcher.group("oldpassword");
        String tempNewPassword = matcher.group("newpassword");
        if (!userInUse.getPassword().equals(tempOldPassword)) {
            System.out.println("password is incorrect!");
        }
        else if (!isPasswordCorrect(tempNewPassword)) {
            System.out.println("password is weak!");
        }
        else {
            System.out.println("password changed!");
            userInUse.setPassword(tempNewPassword);
        }
    }
    static boolean deleteAccount(Matcher matcher) {
        String tempPassword = matcher.group("currentPassword");
        if (userInUse.getPassword().equals(tempPassword)) {
            userArrayList.remove(userInUse);
            System.out.println("account deleted!");
            return true;
        }
        else {
            System.out.println("password is incorrect!");
            return false;
        }
    }
}
