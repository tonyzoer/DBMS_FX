package itlab.service.helpers;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper {
    public static List<String> getAllSavedDatabases() {
        String PATH = "/remote/dir/server/";
        String directoryName = PATH.concat("database/");
        List<String> dbs = new LinkedList<>();
        File folder = new File(directoryName);
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

}