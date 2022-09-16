package utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class credsReader {

    //reader for test data
    public static String fileReader(String key) throws IOException{
        File credsFile = new File("src\\test\\java\\resources\\url.properties");
        FileReader fileReader = new FileReader(credsFile);
        Properties properties = new Properties();

        properties.load(fileReader);
        return properties.getProperty(key);
    }
}
