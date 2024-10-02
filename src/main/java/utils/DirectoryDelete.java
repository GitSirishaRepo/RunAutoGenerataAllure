package utils;

import java.io.File;
import java.util.Objects;

public class DirectoryDelete {


    public  void deleteDirectory(File file)
    {


        // store all the paths of files and folders present
        // inside directory
        for (File subfile : Objects.requireNonNull(file.listFiles())) {

            // if it is a subfolder,e.g Rohan and Ritik,
            //  recursively call function to empty subfolder
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }

            // delete files and empty subfolders
            subfile.delete();
        }
    }

}
