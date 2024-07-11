package Controller;

import Model.ApplicationData;
import Model.MatchData;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HistoryMenuController {
    public static ArrayList<MatchData> getWinnerMatches(){
        ArrayList<MatchData> ans=new ArrayList<>();
        try {
            String query="SELECT * FROM MatchData WHERE Winner= ?";
            PreparedStatement preparedStatement=MatchData.getConnection().prepareStatement(query);
            preparedStatement.setString(1, ApplicationData.getHost().getUsername());
            preparedStatement.executeUpdate();
            ResultSet resultSet=MatchData.getStatement().executeQuery(preparedStatement.toString());
            while (resultSet.next()){
                String win=resultSet.getString(1);
                String lose=resultSet.getString(2);
                String award=resultSet.getString(3);
                String penalty=resultSet.getString(4);
                Timestamp date=resultSet.getTimestamp(7);
                int winlev=resultSet.getInt(5);
                int loselev=resultSet.getInt(6);
                MatchData matchData=new MatchData(win,lose,award,penalty,date,winlev,loselev);
                ans.add(matchData);
            }
        }
         catch (SQLException e){throw new RuntimeException(e);}

        return ans;
    }
    public static ArrayList<MatchData> getLoserMatches(){
        ArrayList<MatchData> ans=new ArrayList<>();
        try {
            String query="SELECT * FROM MatchData WHERE Loser= ?";
            PreparedStatement preparedStatement=MatchData.getConnection().prepareStatement(query);
            preparedStatement.setString(1, ApplicationData.getHost().getUsername());
            preparedStatement.executeUpdate();
            ResultSet resultSet=MatchData.getStatement().executeQuery(preparedStatement.toString());
            while (resultSet.next()){
                String win=resultSet.getString(1);
                String lose=resultSet.getString(2);
                String award=resultSet.getString(3);
                String penalty=resultSet.getString(4);
                Timestamp date=resultSet.getTimestamp(7);
                int winlev=resultSet.getInt(5);
                int loselev=resultSet.getInt(6);
                MatchData matchData=new MatchData(win,lose,award,penalty,date,winlev,loselev);
                ans.add(matchData);
            }
        }
        catch (SQLException e){e.printStackTrace();}

        return ans;
    }
    public static ArrayList<MatchData> getAllMatches(){
        ArrayList<MatchData> ans=new ArrayList<>();
        ans.addAll(getLoserMatches());
        ans.addAll(getWinnerMatches());
        return ans;
    }
    public static String showResult(MatchData matchData, User user){
        if(matchData.getWinner().equals(user.getNickname())){
            String str="Result: Winner, Opponent: " + matchData.getLoser()+" Your level: "+matchData.getWinnerLevel()+" Opponent level: "+matchData.getLoserLevel()+" \nYour Reward: "+matchData.getAward()+"Match Date: "+matchData.getDate().toString();
            return str;
        }
        else{
            String str="Result: Loser, Opponent: " + matchData.getWinner()+" Your level: "+matchData.getLoserLevel()+" Opponent level: "+matchData.getWinnerLevel()+" \nYour Penalty: "+matchData.getPenalty()+"Match Date: "+matchData.getDate().toString();
            return str;
        }
    }
    public static void sortOnDate(ArrayList<MatchData> matchData,boolean ascending){
        Collections.sort(matchData, new Comparator<MatchData>() {
            @Override
            public int compare(MatchData o1, MatchData o2) {
                if(ascending)
                    return o1.getDate().compareTo(o2.getDate());
                else{
                    return o2.getDate().compareTo(o1.getDate());
                }
            }
        });
    }
    public static void sortOnName(ArrayList<MatchData> matchData,boolean ascending){
        User user=ApplicationData.getHost();
        Collections.sort(matchData, new Comparator<MatchData>() {
            @Override
            public int compare(MatchData o1, MatchData o2) {
                if(ascending){
                    if(o1.getWinner().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                      return o1.getLoser().compareTo(o2.getLoser());
                    if(o1.getLoser().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return o1.getWinner().compareTo(o2.getWinner());
                    if(o1.getLoser().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                        return o1.getWinner().compareTo(o2.getLoser());
                    if(o1.getWinner().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return o1.getLoser().compareTo(o2.getWinner());
                    return 1;
                }
                else{
                    if(o1.getWinner().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                        return o2.getLoser().compareTo(o1.getLoser());
                    if(o1.getLoser().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return o2.getWinner().compareTo(o1.getWinner());
                    if(o1.getLoser().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                        return o2.getWinner().compareTo(o1.getLoser());
                    if(o1.getWinner().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return o2.getLoser().compareTo(o1.getWinner());
                    return 1;
                }
            }
        });
    }
    public static void sortOnWin(ArrayList<MatchData> matchData,boolean ascending) {
        User user = ApplicationData.getHost();
        Collections.sort(matchData, new Comparator<MatchData>() {
            @Override
            public int compare(MatchData o1, MatchData o2) {
                if (ascending) {
                    if (o1.getLoser().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                        return 1;
                    if (o1.getWinner().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return -1;
                    return 1;
                }
                else {
                    if (o1.getLoser().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                        return -1;
                    if (o1.getWinner().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return 1;
                    return 1;
                }
            }
        });
    }
    public static void sortOnLevel(ArrayList<MatchData> matchData,boolean ascending){
        User user=ApplicationData.getHost();
        Collections.sort(matchData, new Comparator<MatchData>() {
            @Override
            public int compare(MatchData o1, MatchData o2) {
                if(ascending){
                    if(o1.getWinner().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                        return o1.getLoserLevel()-o2.getLoserLevel();
                    if(o1.getLoser().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return o1.getWinnerLevel()-(o2.getWinnerLevel());
                    if(o1.getLoser().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                        return o1.getWinnerLevel()-(o2.getLoserLevel());
                    if(o1.getWinner().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return o1.getLoserLevel()-(o2.getWinnerLevel());
                    return 1;
                }
                else{
                    if(o1.getWinner().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                        return o2.getLoserLevel()-(o1.getLoserLevel());
                    if(o1.getLoser().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return o2.getWinnerLevel()-(o1.getWinnerLevel());
                    if(o1.getLoser().equals(user.getNickname()) && o2.getWinner().equals(user.getNickname()))
                        return o2.getWinnerLevel()-(o1.getLoserLevel());
                    if(o1.getWinner().equals(user.getNickname()) && o2.getLoser().equals(user.getNickname()))
                        return o2.getLoserLevel()-(o1.getWinnerLevel());
                    return 1;
                }
            }
        });
    }
    public static boolean showAll(ArrayList<MatchData> matchDataArr,int pageNum){
        if(matchDataArr.size()<=(pageNum-1)*10){
            if(pageNum==1){
                System.out.println("You have not played any matches");
                return true;
            }
            else{
                System.out.println("Invalid Page");
                return false;
            }
        }
            for(int a=10*(pageNum-1);a<10*pageNum;a++){
                int b=a+1;
                System.out.println(b+". "+showResult(matchDataArr.get(a),ApplicationData.getHost()));
            }

        int totalPages=matchDataArr.size()-(matchDataArr.size()%10);
        totalPages/=10;
        if(matchDataArr.size()%10!=0)
            totalPages+=1;
        System.out.println("page "+pageNum+" of "+totalPages);
        return true;
    }
}
