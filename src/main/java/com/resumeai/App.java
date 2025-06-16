package com.resumeai;

public class App {
    public static void main(String[] args) throws Exception {
        String resume = ResumeReader.readResume("resume.txt");
        String skills = SkillExtractor.extractSkills(resume);
        System.out.println("Extracted Skills:\n" + skills);
    }
}
