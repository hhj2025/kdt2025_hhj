package DAOstudent;

import java.sql.Connection;

public class MainStudent01 {
    public static void main(String[] args) {
        Connection conn = DBHelper.getInstance().open();

        StudentDao dao = new StudentDaoImpl(conn);

        dao.insert(new Student(200, "유재석", "010-3626-1111", "rhu@gmail.com"));
        dao.insert(new Student(300, "나검사", "010-8888-9999", "naking@naver.com"));

        DBHelper.getInstance().close();
    }
}