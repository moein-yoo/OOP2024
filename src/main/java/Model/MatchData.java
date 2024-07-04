package Model;

import java.beans.Statement;
import java.util.ArrayList;
import java.sql.*;
public class MatchData {
    static ArrayList<MatchData> matchDatas;
    String Winner;
    String Loser;
    String Award;
    String Penalty;
    String Date;
    int WinnerLevel;
    int LoserLevel;
    static String url="jdbc:mysql//localhost:3306/project";
    static String username="root";
    static String password="soroush1384";
    static Connection connection;
    static java.sql.Statement statement;
    public MatchData(String Winner,String Loser,String Award,String Penalty,String Date,int WinnerLevel,int LoserLevel){
        this.Winner=Winner;
        this.Loser=Loser;
        this.Award=Award;
        this.Penalty=Penalty;
        this.Date=Date;
        this.WinnerLevel=WinnerLevel;
        this.LoserLevel=LoserLevel;
    }
    public static void initialize(){
        matchDatas=new ArrayList<>();
        try{
        connection=DriverManager.getConnection(url,username,password);
        statement=connection.createStatement();
        }
        catch (SQLException e){throw new RuntimeException(e);}
        System.out.println("Database connected");
        try{
            ResultSet resultSet=statement.executeQuery("SELECT * FROM MatchData");
            while(resultSet.next()){
                String win=resultSet.getString(1);
                String lose=resultSet.getString(2);
                String award=resultSet.getString(3);
                String penalty=resultSet.getString(4);
                String date=resultSet.getString(5);
                int winlev=resultSet.getInt(6);
                int loselev=resultSet.getInt(7);
                MatchData matchData=new MatchData(win,lose,award,penalty,date,winlev,loselev);
                matchDatas.add(matchData);
            }
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void addToList(MatchData matchData){
        matchDatas.add(matchData);
        String str1=matchData.getWinner();
        String str2=matchData.getLoser();
        String str3=matchData.getAward();
        String str4=matchData.getPenalty();
        String str5=matchData.getDate();
        int int1=matchData.getWinnerLevel();
        int int2=matchData.getLoserLevel();
        String query="INSERT INTO MatchData(Winner, Loser, Award , Penalty,Date , WinnerLevel , LoserLevel) VALUES ("+str1+","+str2+","+str3+","+str4+","+str5+","+ int1 +","+ int2+")";
        try{
            PreparedStatement stmt = connection.prepareStatement(query);
//
            stmt.setString(1, str1);
            stmt.setString(2, str2);
            stmt.setString(3, str3);
            stmt.setString(4, str4);
            stmt.setString(5, str5);
            stmt.setInt(6, int1);
            stmt.setInt(7, int2);

//            stmt.setDate(3, new Date());

            stmt.executeUpdate();
        }
        catch (SQLException e){throw new RuntimeException(e);}

    }
    public String getWinner(){return Winner;}

    public String getLoser() {
        return Loser;
    }
    public String getAward(){return Award;}
    public String getPenalty(){return Penalty;}
    public String getDate(){return Date;}
    public int getWinnerLevel(){return  WinnerLevel;}
    public int getLoserLevel(){return LoserLevel;}
}
