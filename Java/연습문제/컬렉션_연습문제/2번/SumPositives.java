package ch07;

import java.util.Scanner;
import java.util.Vector;

public class SumPositives {
    private static Vector<Integer> v= new Vector<Integer>();
    
    public static void read() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("숫자 입력(0입력하면 끝) : ");
            v.add(scanner.nextInt());
            if (v.contains(0)) {
                break;
            }
        }
    }
    public static void changeToZero() {
        for(int i = 0; i < v.size(); i++){
            if (v.get(i) < 0) {
                v.set(i, 0);
            }
        }
    }
    public static void showAll() {
       for(Integer a : v){
        System.out.print(a + " " );
        }
    }
    public static int add() {
        Integer sum = 0;
        for(Integer a : v){
            sum += a ; 
        }
        return sum;
    }
    
	public static void main(String[] args) {
		SumPositives sp = new SumPositives();
        sp.read();
        sp.changeToZero();
        System.out.print("음수를 0으로 바꾸면 ");
        sp.showAll();
        System.out.println("양수들의 합은 "+ sp.add());
	}
}

