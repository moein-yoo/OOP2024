package View;

import Controller.RegistryMenuController;
import Model.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistryMenuView extends Menu{
    public static void run() {
        Scanner scanner = ApplicationData.getScanner();
        String input;
        Pattern[] patterns = new Pattern[10];
        patterns[0] = Pattern.compile("exit");
        patterns[1] = Pattern.compile("show current menu");
        //(String username,String nickname,String password,String email,String passwordRecoveryQuestion,int character)
        patterns[2] = Pattern.compile("register u (?<username>[\\S]+) n (?<nickname>[\\S\\s]+) p (?<password>[\\S]+) em (?<email>[\\S]+) t (?<type>[\\d])");
        patterns[5] = Pattern.compile("ans (?<answer>[\\S\\s]+)");
        patterns[3] = Pattern.compile("login u (?<username>[\\S]+) p (?<password>[\\S]+)");
        patterns[6] = Pattern.compile("login u (?<username>[\\S]+) passForget");
        patterns[4] = Pattern.compile("list of users");
        input = scanner.nextLine();
        while (!input.equals("exit")){
            boolean ejra = false;
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
                ejra = true;
                String[] entered = new String[6];
                entered[0] = matcher.group("username");
                entered[1] = matcher.group("nickname");
                entered[2] = matcher.group("password");
                entered[3] = matcher.group("email");
                entered[4] = matcher.group("type");
                if (Integer.parseInt(entered[4]) == 1) {
                    System.out.println("Where is your hometown?");
                } else if (Integer.parseInt(entered[4]) == 2) {
                    System.out.println("Who is your first school teacher?");
                }
                else System.out.println("Who is your love?!");
                input = scanner.nextLine();
                if (input.matches(String.valueOf(patterns[5]))) {
                    matcher = getCommandMatcher(input,String.valueOf(patterns[5]));
                    matcher.find();
                    entered[5] = matcher.group("answer");
                    System.out.println(RegistryMenuController.signUp(entered));
                }else {
                    ejra = false;
                }
            }
            else if (input.matches(String.valueOf(patterns[3]))) {
                Matcher matcher = getCommandMatcher(input,String.valueOf(patterns[3]));
                matcher.find();
                String[] entered = new String[2];
                entered[0] = matcher.group("username");
                entered[1] = matcher.group("password");
                String outcome = RegistryMenuController.login(entered);
                System.out.println(outcome);
                if (outcome.contains("Admin")) {
                    if (!AdminMenuView.run())
                        return;
                }
                if (outcome.contains("successfully")) {
                    String captcha = captchaAsciiArtChecker();
                    System.out.println(captcha);
                    if (captcha.contains("Not"))
                        if (!MainMenuView.run())
                            return;
                }
                ejra = true;
            }
            else if (input.matches(String.valueOf(patterns[6]))) {
                Matcher matcher = getCommandMatcher(input,String.valueOf(patterns[6]));
                matcher.find();
                String entered;
                entered = matcher.group("username");
                if (RegistryMenuController.isUserValid(entered)) {
                    for (User user : ApplicationData.getUserArrayList()) {
                        if (user.getUsername().equals(entered)) {
                            if (user.getPasswordRecoveryType()==1)
                                System.out.println("Where is your hometown?");
                            else if (user.getPasswordRecoveryType()==2)
                                System.out.println("Who is your first school teacher?");
                            else System.out.println("Who is your love?!");
                            input = scanner.nextLine();
                            if (input.equalsIgnoreCase(user.getPasswordRecoveryQuestion())) {
                                System.out.println("Correct so you logged in");
                                ApplicationData.setHost(user);
                                if (!MainMenuView.run())
                                    return;
                            }
                            else {
                                System.out.println("Incorrect Answer, It's better to visit a doctor!");
                            }
                        }
                    }
                }
                ejra = true;

            }
            else if (input.matches(String.valueOf(patterns[4]))) {
                Matcher matcher = getCommandMatcher(input,String.valueOf(patterns[4]));
                matcher.find();
                ArrayList<String> outcome = RegistryMenuController.userList();
                for (String s : outcome) {
                    System.out.print(s);
                }
                ejra = true;
            }
            if(!ejra) {
                System.out.println("invalid command");
            }
            input = scanner.nextLine();
        }
    }

    public static String captchaChecker() {
        int a = ApplicationData.getRandom().nextInt(5000);
        int b = ApplicationData.getRandom().nextInt(5000);
        int result = 0;
        String[] sign = {"plus", "minus", "divide"};
        int index = ApplicationData.getRandom().nextInt(3);
        if (index == 0) {
            result = a + b;
        }
        if (index == 1) {
            result = a - b;
        }
        if (index == 2) {
            result = a*b;
        }
        System.out.println("Now it's time to captcha");
        System.out.println(a + " " + sign[index] + " " + b + " =");
        Scanner scanner = ApplicationData.getScanner();
        String input = scanner.nextLine();
        if (Integer.parseInt(input)==result) {
            return "Not a robot:)";
        }
        else {
            return "It seems you are a robot:(";
        }
    }
    public static String captchaAsciiArtChecker() {
        String[] ascii_art = new String[10];
        ascii_art[0] = "                        ,     ,\n" +
                "                        |\\---/|\n" +
                "                       /  , , |\n" +
                "                  __.-'|  / \\ /\n" +
                "         __ ___.-'        ._O|\n" +
                "      .-'  '        :      _/\n" +
                "     / ,    .        .     |\n" +
                "    :  ;    :        :   _/\n" +
                "    |  |   .'     __:   /\n" +
                "    |  :   /'----'| \\  |\n" +
                "    \\  |\\  |      | /| |\n" +
                "     '.'| /       || \\ |\n" +
                "     | /|.'       '.l \\\\_\n" +
                "     || ||             '-'\n" +
                "     '-''-' \n";//wolf
        ascii_art[1] = "        _\n" +
                "    .--' |\n" +
                "   /___^ |     .--.\n" +
                "       ) |    /    \\\n" +
                "      /  |  /`      '.\n" +
                "     |   '-'    /     \\\n" +
                "     \\         |      |\\\n" +
                "      \\    /   \\      /\\|\n" +
                "       \\  /'----`\\   /\n" +
                "       |||       \\\\ |\n" +
                "       ((|        ((|\n" +
                "       |||        |||\n" +
                "jgs   //_(       //_( \n";//camel
        ascii_art[2] = "                                    _\n" +
                "                               _.-~~.)\n" +
                "         _.--~~~~~---....__  .' . .,'\n" +
                "       ,'. . . . . . . . . .~- ._ (\n" +
                "      ( .. .g. . . . . . . . . . .~-._\n" +
                "   .~__.-~    ~`. . . . . . . . . . . -.\n" +
                "   `----..._      ~-=~~-. . . . . . . . ~-.\n" +
                "             ~-._   `-._ ~=_~~--. . . . . .~.\n" +
                "              | .~-.._  ~--._-.    ~-. . . . ~-.\n" +
                "               \\ .(   ~~--.._~'       `. . . . .~-.                ,\n" +
                "                `._\\         ~~--.._    `. . . . . ~-.    .- .   ,'/\n" +
                "_  . _ . -~\\        _ ..  _          ~~--.`_. . . . . ~-_     ,-','`  .\n" +
                "             ` ._           ~                ~--. . . . .~=.-'. /. `\n" +
                "       - . -~            -. _ . - ~ - _   - ~     ~--..__~ _,. /   \\  - ~\n" +
                "              . __ ..                   ~-               ~~_. (  `\n" +
                ")`. _ _               `-       ..  - .    . - ~ ~ .    \\    ~-` ` `  `. _\n" +
                "      _      _ \n";//dolphin
        ascii_art[3] = "                            _\n" +
                "                          .' `'.__\n" +
                "                         /      \\ `'\"-,\n" +
                "        .-''''--...__..-/ .     |      \\\n" +
                "      .'               ; :'     '.  a   |\n" +
                "     /                 | :.       \\     =\\\n" +
                "    ;                   \\':.      /  ,-.__;.-;`\n" +
                "   /|     .              '--._   /-.7`._..-;`\n" +
                "  ; |       '                |`-'      \\  =|\n" +
                "  |/\\        .   -' /     /  ;         |  =/\n" +
                "  (( ;.       ,_  .:|     | /     /\\   | =|\n" +
                "   ) / `\\     | `\"\"`;     / |    | /   / =/\n" +
                "     | ::|    |      \\    \\ \\    \\ `--' =/\n" +
                "    /  '/\\    /       )    |/     `-...-`\n" +
                "   /    | |  `\\    /-'    /;\n" +
                "   \\  ,,/ |    \\   D    .'  \\\n" +
                "jgs `\"\"`   \\  nnh  D_.-'L__nnh\n" +
                "            `\"\"\"` \n";//elephant
        ascii_art[4] = "       o                 o\n" +
                "                  o\n" +
                "         o   ______      o\n" +
                "           _/  (   \\_\n" +
                " _       _/  (       \\_  O\n" +
                "| \\_   _/  (   (    0  \\\n" +
                "|== \\_/  (   (          |\n" +
                "|=== _ (   (   (        |\n" +
                "|==_/ \\_ (   (          |\n" +
                "|_/     \\_ (   (    \\__/\n" +
                "          \\_ (      _/\n" +
                "            |  |___/\n" +
                "           /__/ \n";//fish
        ascii_art[5] = "                                 |\\    /|\n" +
                "                              ___| \\,,/_/\n" +
                "                           ---__/ \\/    \\\n" +
                "                          __--/     (D)  \\\n" +
                "                          _ -/    (_      \\\n" +
                "                         // /       \\_ /  -\\\n" +
                "   __-------_____--___--/           / \\_ O o)\n" +
                "  /                                 /   \\__/\n" +
                " /                                 /\n" +
                "||          )                   \\_/\\\n" +
                "||         /              _      /  |\n" +
                "| |      /--______      ___\\    /\\  :\n" +
                "| /   __-  - _/   ------    |  |   \\ \\\n" +
                " |   -  -   /                | |     \\ )\n" +
                " |  |   -  |                 | )     | |\n" +
                "  | |    | |                 | |    | |\n" +
                "  | |    < |                 | |   |_/\n" +
                "  < |    /__\\                <  \\\n" +
                "  /__\\                       /___\\ \n";//horse
        ascii_art[6] = "                         ___\n" +
                "                     .-'`     `'.\n" +
                "              __    /  .-. .-.   \\\n" +
                "           .'`__`'.| /  ()|  ()\\  \\\n" +
                "          / /`   `\\\\ |_ .-.-. _|  ;  __\n" +
                "          ||     .-'`  (/`|`\\) `-./'`__`'.\n" +
                "          \\ \\. .'                 `.`  `\\ \\\n" +
                "           `-./  _______            \\    ||\n" +
                "              | |\\      ''''---.__   |_./ /\n" +
                "              ' \\ `'---..________/|  /.-'`\n" +
                "               `.`._            _/  /\n" +
                "                 `-._'-._____.-' _.`\n" +
                "                  _,-''.__...--'`\n" +
                "              _.-'_.    ,-. _ `'-._\n" +
                "           .-' ,-' /   /   \\\\`'-._ `'.\n" +
                "         <`  ,'   /   /     \\\\    / /\n" +
                "          `.  \\  ;   ;       ;'  / /_\n" +
                "    __   (`\\`. \\ |   |       ||.' // )\n" +
                " .'`_ `\\(`'.`.\\_\\|   |    o  |/_,'/.' )\n" +
                "/ .' `; |`-._ ` /;    \\     / \\   _.-'\n" +
                "| |  (_/  (_..-' _\\    `'--' | `-.._)\n" +
                "; \\        _.'_.' / /'.___.; \\\n" +
                " \\ '-.__.-'_.'   ; '        \\ \\\n" +
                "  `-.,__.-'      | ;         ; '\n" +
                "                 | |         | |\n" +
                "                 | |         / /mx\n" +
                "               .-' '.      ,' `-._\n" +
                "             /`    _ `.   /  _    `.\n" +
                "            '-/ / / `\\_) (_/` \\  .`,)\n" +
                "             | || |            | | |\n" +
                "             `-'\\_'            (_/-'\n";//monkey
        ascii_art[7] = "      .-.         .--''-.\n" +
                "    .'   '.     /'       `.\n" +
                "    '.     '. ,'          |\n" +
                " o    '.o   ,'        _.-'\n" +
                "  \\.--./'. /.:. :._:.'\n" +
                " .'    '._-': ': ': ': ':\n" +
                ":(#) (#) :  ': ': ': ': ':>-\n" +
                " ' ____ .'_.:' :' :' :' :'\n" +
                "  '\\__/'/ | | :' :' :'\n" +
                "        \\  \\ \\\n" +
                "        '  ' '    \n";//bee
        ascii_art[8] = "                              __\n" +
                "                     /\\    .-\" /\n" +
                "                    /  ; .'  .' \n" +
                "                   :   :/  .'   \n" +
                "                    \\  ;-.'     \n" +
                "       .--\"\"\"\"--..__/     `.    \n" +
                "     .'           .'    `o  \\   \n" +
                "    /                    `   ;  \n" +
                "   :                  \\      :  \n" +
                " .-;        -.         `.__.-'  \n" +
                ":  ;          \\     ,   ;       \n" +
                "'._:           ;   :   (        \n" +
                "    \\/  .__    ;    \\   `-.     \n" +
                " bug ;     \"-,/_..--\"`-..__)    \n" +
                "     '\"\"--.._: \n";//rabbit
        ascii_art[9] = "           ;               ,           \n" +
                "         ,;                 '.         \n" +
                "        ;:                   :;        \n" +
                "       ::                     ::       \n" +
                "       ::                     ::       \n" +
                "       ':                     :        \n" +
                "        :.                    :        \n" +
                "     ;' ::                   ::  '     \n" +
                "    .'  ';                   ;'  '.    \n" +
                "   ::    :;                 ;:    ::   \n" +
                "   ;      :;.             ,;:     ::   \n" +
                "   :;      :;:           ,;\"      ::   \n" +
                "   ::.      ':;  ..,.;  ;:'     ,.;:   \n" +
                "    \"'\"...   '::,::::: ;:   .;.;\"\"'    \n" +
                "        '\"\"\"....;:::::;,;.;\"\"\"         \n" +
                "    .:::.....'\"':::::::'\",...;::::;.   \n" +
                "   ;:' '\"\"'\"\";.,;:::::;.'\"\"\"\"\"\"  ':;   \n" +
                "  ::'         ;::;:::;::..         :;  \n" +
                " ::         ,;:::::::::::;:..       :: \n" +
                " ;'     ,;;:;::::::::::::::;\";..    ':.\n" +
                "::     ;:\"  ::::::\"\"\"'::::::  \":     ::\n" +
                " :.    ::   ::::::;  :::::::   :     ; \n" +
                "  ;    ::   :::::::  :::::::   :    ;  \n" +
                "   '   ::   ::::::....:::::'  ,:   '   \n" +
                "    '  ::    :::::::::::::\"   ::       \n" +
                "       ::     ':::::::::\"'    ::       \n" +
                "       ':       \"\"\"\"\"\"\"'      ::       \n" +
                "        ::                   ;:        \n" +
                "        ':;                 ;:\"        \n" +
                "-hrr-     ';              ,;'          \n" +
                "            \"'           '\"            \n" +
                "              ' \n";//spider
        int index = ApplicationData.getRandom().nextInt(10);
        System.out.println(ascii_art[index]);
        Scanner scanner = ApplicationData.getScanner();
        String input = scanner.nextLine();
        //wolf//camel//dolphin//elephant//fish//horse//monkey//bee//rabbit//spider
        if (index == 0 && input.equalsIgnoreCase("wolf"))
            return "Not a robot:)";
        else if (index == 1 && input.equalsIgnoreCase("camel"))
            return "Not a robot:)";
        else if (index == 2 && input.equalsIgnoreCase("dolphin"))
            return "Not a robot:)";
        else if (index == 3 && input.equalsIgnoreCase("elephant"))
            return "Not a robot:)";
        else if (index == 4 && input.equalsIgnoreCase("fish"))
            return "Not a robot:)";
        else if (index == 5 && input.equalsIgnoreCase("horse"))
            return "Not a robot:)";
        else if (index == 6 && input.equalsIgnoreCase("monkey"))
            return "Not a robot:)";
        else if (index == 7 && input.equalsIgnoreCase("bee"))
            return "Not a robot:)";
        else if (index == 8 && input.equalsIgnoreCase("rabbit"))
            return "Not a robot:)";
        else if (index == 9 && input.equalsIgnoreCase("spider"))
            return "Not a robot:)";
        else {
            return "It seems you are a robot:(";
        }
    }
}
