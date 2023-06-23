package com.kononenko.sergii.util;

import java.io.File;

public class FileSystemCreator {

    private static final String CURRENT_DIR_OF_THE_APP = "user.dir";

    public String createFolderIfNotExist(String companyName) {
        var currentDir = System.getProperty(CURRENT_DIR_OF_THE_APP);
        var folderPath = currentDir + File.separator + companyName;
        var folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        return folderPath + File.separator;
    }
}