package ch14;

public class Student {
    int id;
    String name;
    int kor, math, eng;

    public Student(int id, String name, int kor, int math, int eng) {
        this.id = id;
        this.name = name;
        this.kor = kor;
        this.math = math;
        this.eng = eng;
    }

    public double getAverage() {
        return (kor + math + eng) / 3.0;
    }
}
