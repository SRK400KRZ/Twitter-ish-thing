package dao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import dto.LoginUser;

import config.DBconfig;


public class SqlDao {
    public final String file_path = "/Users/oxepc/IdeaProjects/Twitter-ish-thing/DBconfig.properties";
    DBconfig config = new DBconfig();

    public List<LoginUser> check(String user, String password) throws IOException{
        String[] DbInfo = config.getDBinfo(file_path);

        String url = DbInfo[0];
        String db_user_name = DbInfo[1];
        String db_password = DbInfo[2];

        String sql = "select * from login_user_tb "
                + "where name = ? and password = ?";

        LoginUser login_user = new LoginUser();
        List<LoginUser> user_info = new ArrayList<LoginUser>();

        try {
            Connection conn = DriverManager.getConnection(url, db_user_name, db_password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                login_user.setId(rs.getInt("id"));
                login_user.setName(rs.getString("name"));
                login_user.setPassword(rs.getString("password"));
                user_info.add(login_user);
            }else{
                login_user.setName("No user");
                login_user.setPassword("unmatch password");
                user_info.add(login_user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user_info;
    }
}
