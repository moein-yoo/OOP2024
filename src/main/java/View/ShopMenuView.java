package View;

import Model.ApplicationData;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopMenuView {
    public static boolean run(){
        Scanner scan= ApplicationData.getScanner();
        Pattern buyCard=Pattern.compile("buy card (\\w+)");
        Pattern upgradeCard=Pattern.compile("upgrade card (\\w+)");
        Pattern exit=Pattern.compile("exit");
        Pattern showCardsIdonthave=Pattern.compile("show unpossessed cards");
        Pattern showupgrades=Pattern.compile("show upgrades");
        String command;

        while(true){
            command=scan.nextLine();
            Matcher upgradem=upgradeCard.matcher(command);
            Matcher buym= buyCard.matcher(command);
            Matcher exitm= exit.matcher(command);

            boolean upgradeb=upgradem.find();
            boolean buyb=buym.find();
            boolean exitb= exitm.find();
            if(exitb)
                break;

        }
        return false;
    }
}
