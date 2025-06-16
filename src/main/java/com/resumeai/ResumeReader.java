package com.resumeai;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ResumeReader {
    public static String readResume(String filepath) throws Exception {
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }
}
