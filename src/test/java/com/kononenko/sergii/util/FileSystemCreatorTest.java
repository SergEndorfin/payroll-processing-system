package com.kononenko.sergii.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemCreatorTest {

    FileSystemCreator fileSystemCreator = new FileSystemCreator();
    String folderName = "test_test";
    String currentDir = System.getProperty("user.dir");
    String folderPath = currentDir + File.separator + folderName;
    File folder;


    @BeforeEach
    void init() {
        folder = new File(folderPath);
        if (folder.exists()) {
            folder.delete();
        }
    }

    @AfterEach
    void clear() {
        folder.delete();
    }

    @Test
    void whenCreateFolderIfNotExist_thenFolderCreated() {
        var expectedPath = currentDir + File.separator + folderName + File.separator;
        String folderPathWithSeparator = fileSystemCreator.createFolderIfNotExist(folderName);
        assertTrue(folder.exists());
        assertEquals(expectedPath, folderPathWithSeparator);
    }

}