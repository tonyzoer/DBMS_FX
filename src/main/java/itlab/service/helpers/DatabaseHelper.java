package itlab.service.helpers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper {
    public static List<String> getAllSavedDatabases() {
        String directoryName = getFilePath() + "\\databases\\";
        List<String> dbs = new LinkedList<>();
        File folder = new File(directoryName);
        if (!folder.exists()){
            folder.mkdirs();
                    return dbs;
        }
        for (File fileEntry : folder.listFiles()
                ) {
            if (fileEntry.isFile()) {
                String filename = fileEntry.getName();

                String extension = "";
                int i = filename.lastIndexOf('.');
                if (i > 0) {
                    extension = filename.substring(i + 1);
                }
                if (extension.equals("db")) {
                    dbs.add(fileEntry.getName().substring(0,filename.lastIndexOf('.')));
                }
            }
        }
        return dbs;
    }
    private static String getFilePath() {
        return new File(ClassLoader.getSystemClassLoader().getResource(".").getPath()).getAbsolutePath();
    }

    public static void delete(String databaseName){
        String directoryName = getFilePath() + "\\databases\\";
        String filename = databaseName + ".db";
        File f = new File(directoryName + filename);
        try {
            FileUtils.forceDelete(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}