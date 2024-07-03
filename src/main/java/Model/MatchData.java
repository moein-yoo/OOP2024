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
    static String url="jdbc:mysql//localHost:3306/matchdata";
    static String username="root";
    static String password="soroush1384";
    static Connection connection;
    static Statement statement;
    public static void initialize(){

    }
}
