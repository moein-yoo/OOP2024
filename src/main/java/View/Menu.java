package View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu {
    public static Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input);
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

}
