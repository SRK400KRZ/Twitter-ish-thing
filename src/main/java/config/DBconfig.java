package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBconfig {
    public String[] getDBinfo(String file_path) throws FileNotFoundException {
        Properties info = new Properties();
        FileInputStream file_stream = null;

        try{
            file_stream = new FileInputStream(file_path);
            info.load(file_stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = info.getProperty("url");
        String user = info.getProperty("user");
        String passwrord = info.getProperty("password");

        String[] db_info = {url, user, passwrord};

        return db_info;
    }
}
