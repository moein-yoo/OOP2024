package View;

import Controller.HistoryMenuController;
import Model.ApplicationData;
import Model.MatchData;
import Model.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HistoryMenuView extends Menu{
    public static boolean run(){
        ArrayList<MatchData> allMatches= HistoryMenuController.getAllMatches();
        HistoryMenuController.sortOnDate(allMatches,false);
        HistoryMenuController.showAll(allMatches,1);
        Scanner scan=ApplicationData.getScanner();
        String command;
        Pattern exit=Pattern.compile("exit");
        Pattern goToN=Pattern.compile("go to page (\\d+)");
        Pattern nextPage=Pattern.compile("go to next page");
        Pattern prevPage=Pattern.compile("go to previous page");
        Pattern sort=Pattern.compile("sort on (\\s+) order:(\\s+)");
        int currentPage=1;
        while(true){
            command=scan.nextLine();

            Matcher exitm=exit.matcher(command);
            Matcher goToNm= goToN.matcher(command);
            Matcher nextPagem=nextPage.matcher(command);
            Matcher prevPagem= prevPage.matcher(command);
            Matcher sortm= sort.matcher(command);


            boolean exitb=exitm.find();
            boolean goToNb=goToNm.find();
            boolean nextPageb=nextPagem.find();
            boolean prevPageb=prevPagem.find();
            boolean sortb= sortm.find();
            if(exitb)
                break;
            if(goToNb){
               if(HistoryMenuController.showAll(allMatches,Integer.parseInt(goToNm.group(1))))
                  currentPage=Integer.parseInt(goToNm.group(1));
            }
            if(prevPageb){
                if(currentPage==1)
                    System.out.println("You are at first page already");
                else{
                    currentPage-=1;
                    HistoryMenuController.showAll(allMatches,currentPage);
                }
            }
            if(nextPageb){
                int totalPages=allMatches.size()-(allMatches.size()%10);
                totalPages/=10;
                if(allMatches.size()%10!=0)
                    totalPages+=1;
                if(currentPage==totalPages)
                    System.out.println("You are already at last page");
                else{
                    currentPage+=1;
                    HistoryMenuController.showAll(allMatches,currentPage);
                }
            }
            if(sortb){
                if(sortm.group(1).equalsIgnoreCase("date")){
                    if(sortm.group(2).equalsIgnoreCase("ascending"))
                      HistoryMenuController.sortOnDate(allMatches,true);
                    if(sortm.group(2).equalsIgnoreCase("descending"))
                        HistoryMenuController.sortOnDate(allMatches,false);
                    HistoryMenuController.showAll(allMatches,currentPage);
                }
                if(sortm.group(1).equalsIgnoreCase("win or lose")){
                    if(sortm.group(2).equalsIgnoreCase("ascending")){
                        HistoryMenuController.sortOnWin(allMatches,true);
                    }
                    if(sortm.group(2).equalsIgnoreCase("descending")){
                        HistoryMenuController.sortOnWin(allMatches,false);
                    }
                    HistoryMenuController.showAll(allMatches,currentPage);
                }
                if(sortm.group(1).equalsIgnoreCase("name")){
                    if(sortm.group(2).equalsIgnoreCase("ascending"))
                        HistoryMenuController.sortOnName(allMatches,true);
                    if(sortm.group(2).equalsIgnoreCase("descending"))
                        HistoryMenuController.sortOnName(allMatches,false);
                    HistoryMenuController.showAll(allMatches,currentPage);
                }
                if(sortm.group(1).equalsIgnoreCase("level")){
                    if(sortm.group(2).equalsIgnoreCase("ascending"))
                        HistoryMenuController.sortOnLevel(allMatches,true);
                    if(sortm.group(2).equalsIgnoreCase("descending"))
                        HistoryMenuController.sortOnLevel(allMatches,false);
                    HistoryMenuController.showAll(allMatches,currentPage);
                }

            }
        }
        return false;
    }
}
