package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int numRows;
        int recordIssueTypeSubtask;


        if (args.length == 1) {
            try {
                numRows = Integer.parseInt(args[0]);
            } catch (Exception e) {
                numRows = 500;
            }
        } else {
            numRows = 500;
        }

        String filepath = "/Users/shawyanjafari/Desktop/test.csv";
        long documentId;
        long[] issuetype_id = {1, 2, 3, 4, 5, 6};
        String[] summary = {
                "Summary #1",
                "Summary #2",
                "Summary #3",
                "Summary #4",
                "Summary #5",
                "Summary #6"};

        String[] issueTypeDescription = {
                "Issue Type Description 1",
                "Issue Type Description 2",
                "Issue Type Description 3",
                "Issue Type Description 4",
                "Issue Type Description 5",
                "Issue Type Description 6"};

        String[] issueTypeName = {
                "Bug",
                "Risk",
                "Incident",
                "Problem",
                "Task",
                "Change"};

        String[] assigneeName = {
                "Debbie",
                "Richard",
                "Ann Bird",
                "Maria",
                "Paula",
                "Linda",
                "Jeff",
                "Matt",
                "Stephen",
        };



        for (int x = 0; x < numRows; x++) {
            //create records
            int randomNumber = generateRandomWithRange(0,5);
            int randomNumber2 = generateRandomWithRange(0,8);

            saveRecord(
                    (x+1), //documentId
                    summary[randomNumber],
                    issuetype_id[randomNumber],
                    issueTypeDescription[randomNumber],
                    issueTypeName[randomNumber],
                    generateRandomWithRange(0,1),
                    assigneeName[randomNumber2],
                    filepath);
        }
        addEmptyLine(filepath);
    }

    protected static int generateRandomWithRange(int min, int max) {
        max++;
        int range = Math.abs(min - max);
        int rand = ((int) ((Math.random() * range) + min));

        return rand;
    }

    public static void saveRecord(long id, String recordSummary, long recordIssueTypeId, String recordIssueDesc, String recordIssueType, int recordIssueTypeSubtask, String recordAssignee, String filepath) {
        try
        {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(id+","+recordSummary+","+recordIssueTypeId+","+recordIssueDesc+","+recordIssueType+","+recordIssueTypeSubtask+","+recordAssignee);
            pw.flush();
            pw.close();
        }
        catch(Exception E)
        {
            System.out.println("Can't save record.");
        }
    }

    public static void addEmptyLine(String filepath) {
        try
        {
            FileWriter fw = new FileWriter(filepath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(""+","+""+","+""+","+""+","+""+","+""+",");
            pw.flush();
            pw.close();
        }
        catch(Exception E)
        {
            System.out.println("Can't add empty line.");
        }
    }
}
