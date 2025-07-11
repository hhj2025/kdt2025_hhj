package DAOstudent;

import java.sql.Connection;
import java.util.List;

public class MainStudent02 {
    public static void main(String[] args) {
        Connection conn = DBHelper.getInstance().open();
        StudentDao dao = new StudentDaoImpl(conn);

        System.out.println("전체 목록 출력:");
        List<Student> list = dao.selectAll();
        list.forEach(System.out::println);

        System.out.println("\n전화번호 수정:");
        dao.updatePhone("오짱구", "010-6666-7878");

        System.out.println("\n이순신 삭제:");
        dao.delete("이순신");

        System.out.println("\n전화번호 검색:");
        Student s = dao.selectByPhone("010-3626-1111");
        if (s != null) {
            System.out.println(s);
        } else {
            System.out.println("검색 결과 없음");
        }

        DBHelper.getInstance().close();
    }
}