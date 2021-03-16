package com.gbgs.profile.service;

import com.gbgs.profile.service.template.IoServiceTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;

/**
 * Author: santosh.kumar
 * Date: 1st NOV 2020
 * Comment: IoService is a service class which is used to execute IO operation
 */
@Service
public class IoService implements IoServiceTemplate {

    /**
     * Author: santosh.kumar
     * param directory
     * param searchFile
     * return List<String>
     */
    @Override
    public List<String> searchFile(File directory, String searchFile) {
        List<String> searchFileList = new ArrayList<>();
        File[] filesAndDirs = directory.listFiles();
        for (File file : filesAndDirs) {
            if (file.isFile() && (file.getName().equalsIgnoreCase(searchFile))) {
                searchFileList.add(file.getAbsolutePath());
            } else {
                searchFile(file, searchFile);
            }
        }
        return searchFileList;
    }

    /**
     * Author: santosh.kumar
     * param directory
     * param searchWord
     * return List<String>
     */
    @Override
    public List<String> searchWord(File directory, String searchWord) {
        Scanner scanFile;
        List<String> searchWordList = new ArrayList<>();
        File[] filesAndDirs = directory.listFiles();

        for (File file : filesAndDirs) {
            if (file.isFile()) {
                try {
                    scanFile = new Scanner(file);
                    while (null != scanFile.findWithinHorizon("(?i)\\b" + searchWord + "\\b", 0)) {
                        MatchResult mr = scanFile.match();
                        searchWordList.add("Word " + searchWord + " found in File: " + file + " at index " + mr.start());
                    }
                    scanFile.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                searchWord(file, searchWord);
            }
        }
        return searchWordList;
    }
}
