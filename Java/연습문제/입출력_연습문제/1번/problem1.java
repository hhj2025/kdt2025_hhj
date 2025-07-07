package ch14;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class problem1 {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/data.txt")));
            String line;
             
            while ((line = reader.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, ",");

                int id = Integer.parseInt(st.nextToken().trim());
                String name = st.nextToken().trim();
                int kor = Integer.parseInt(st.nextToken().trim());
                int math = Integer.parseInt(st.nextToken().trim());
                int eng = Integer.parseInt(st.nextToken().trim());

                students.add(new Student(id, name, kor, math, eng));
            }
            reader.close();
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        Student topStudent = null;
        double maxAvg = -1;

        int totalKor = 0;

        for (Student s : students) {
            double avg = s.getAverage();
            if (avg > maxAvg) {
                maxAvg = avg;
                topStudent = s;
            }

            totalKor += s.kor;
        }

        // 출력
        System.out.println("📌 최고 평균을 받은 학생: " + topStudent.name + " (" + String.format("%.2f", maxAvg) + ")");
        System.out.println("📘 국어 점수 전체 평균: " + (totalKor / (double) students.size()));
    }
}

