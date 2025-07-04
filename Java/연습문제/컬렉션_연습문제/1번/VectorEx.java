package ch07;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class VectorEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vector<Integer> v = new Vector<>();
        
        while (true) {
            System.out.print("정수입력(-1이면 입력 끝) : ");
            v.add(scanner.nextInt());
            if(v.contains(-1)){
                break;
            }
        }

        v.remove((Integer)(-1));
        
        Collections.sort(v);

        System.out.println("제일 작은수 : " + v.firstElement());
    }
}
