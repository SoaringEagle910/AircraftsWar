package edu.hitsz.Score;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ShowScoreBoard {
    private static ArrayList<Record> records = new ArrayList<>();

    public static int getRecordSize() {
        return records.size();
    }

    public static ArrayList<Record> getRecords() {
        return records;
    }

    public static void show() {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("src/records/records.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] recordData = line.split(",");
                Record record = new Record(Integer.parseInt(recordData[0]), recordData[1], Integer.parseInt(recordData[2]), LocalDateTime.parse(recordData[3]));
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(records, Comparator.comparingInt(Record::getMark).reversed());


    }

    public static void addOne(String name, int mark) {
        records.clear();
        try (BufferedReader br = new BufferedReader(new FileReader("src/records/records.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] recordData = line.split(",");
                Record record = new Record(Integer.parseInt(recordData[0]), recordData[1], Integer.parseInt(recordData[2]), LocalDateTime.parse(recordData[3]));
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Record newRecord = new Record(1, name, mark, LocalDateTime.now());
        records.add(newRecord);

        Collections.sort(records, Comparator.comparingInt(Record::getMark).reversed());

        int rank = 1;
        for (Record record : records) {
            record.setRank(rank);
            rank++;
        }



        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/records/records.txt"))) {
            for (Record record : records) {
                bw.write(record.getRank() + "," + record.getName() + "," + record.getMark() + "," + record.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("**************************************************");
        System.out.println("                     得分排行榜                     ");
        System.out.println("**************************************************");
        for (Record record : records) {
            System.out.println("第" + record.getRank() + "名:" + record.getName() + "," + record.getMark() + "," + record.getTime().format(DateTimeFormatter.ofPattern("MM-dd HH:mm")));
        }
    }
    public static void deleteOne(int index){
        records.remove(index);
        int rank = 1;
        for (Record record : records) {
            record.setRank(rank);
            rank++;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/records/records.txt"))) {
            for (Record record : records) {
                bw.write(record.getRank() + "," + record.getName() + "," + record.getMark() + "," + record.getTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
