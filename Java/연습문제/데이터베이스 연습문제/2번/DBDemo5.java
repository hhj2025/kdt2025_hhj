
import java.sql.*;
import java.util.Scanner;

public class DBDemo5 {
    static Scanner scanner = new Scanner(System.in);
    static Connection con = makeConnection();

    public static void main(String[] args) throws SQLException {
        EnumMenu nMenu;
        DBDemo5 dbDemo5 = new DBDemo5();
        while (true) {
            nMenu = getMenu();
            if (nMenu == EnumMenu.MENU_EXIT) {
                System.out.println("프로그램 종료");
                con.close();
                break;
            }

            switch (nMenu) {
                case MENU_LIST:
                    dbDemo5.doList();
                    break;
                case MENU_INSERT:
                    dbDemo5.doInsert();
                    break;
                case MENU_DELETE:
                    dbDemo5.doDelete();
                    break;
                case MENU_UPDATE:
                    dbDemo5.doUpdate();
                    break;
                case MENU_FIND:
                    dbDemo5.doFind();
                    break;
            }
        }
    }

    public static Connection makeConnection() {
        String url = "jdbc:mysql://localhost/school_db?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8";
        String userName = "root";
        String userPass = "1234";
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("데이터베이스 연결중 ...");
            con = DriverManager.getConnection(url, userName, userPass);
            System.out.println("데이터베이스 연결 성공");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버를 찾지 못했습니다...");
        } catch (SQLException e) {
            System.out.println("데이터베이스 연결 실패");
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static EnumMenu getMenu() {
        int eNum;
        while (true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("1. 모든 학생 정보 보기");
            System.out.println("2. 전화번호 변경");
            System.out.println("3. 학생 추가");
            System.out.println("4. 학생 삭제");
            System.out.println("5. 전화번호로 학생 검색");
            System.out.println("0. 종료하기...");
            System.out.println("-----------------------------------------------------");
            System.out.print("메뉴를 입력하세요 : ");
            try {
                eNum = Integer.parseInt(scanner.nextLine());
                if (eNum < 6 && eNum >= 0) {
                    break;
                }
            } catch (Exception e) {}
            System.out.println("잘못 선택 하셨습니다. 숫자 다시 입력(0~5) : ");
        }
        return switch (eNum) {
            case 1 -> EnumMenu.MENU_LIST;
            case 2 -> EnumMenu.MENU_UPDATE;
            case 3 -> EnumMenu.MENU_INSERT;
            case 4 -> EnumMenu.MENU_DELETE;
            case 5 -> EnumMenu.MENU_FIND;
            default -> EnumMenu.MENU_EXIT;
        };
    }

    public void doList(){
        try {
            String sql = "SELECT * FROM student";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("학번: " + rs.getInt("stdno") + "\t" +
                        "이름: " + rs.getString("stdname") + "\t" +
                        "전화번호: " + rs.getString("phone") + "\t" +
                        "이메일: " + rs.getString("email"));
            }
            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doInsert(){
        try {
            String sql = "INSERT INTO student (stdno, stdname, phone, email) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(sql);

            System.out.print("학번: ");
            int stdno = Integer.parseInt(scanner.nextLine());
            System.out.print("이름: ");
            String stdname = scanner.nextLine();
            System.out.print("전화번호: ");
            String phone = scanner.nextLine();
            System.out.print("이메일: ");
            String email = scanner.nextLine();

            pstmt.setInt(1, stdno);
            pstmt.setString(2, stdname);
            pstmt.setString(3, phone);
            pstmt.setString(4, email);

            pstmt.execute();
            System.out.println("학생 추가 완료");

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doDelete(){
        try {
            String sql = "DELETE FROM student WHERE stdname = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            System.out.print("삭제할 학생 이름 : ");
            String name = scanner.nextLine();

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("삭제 성공");
            } else {
                System.out.println("해당 이름의 학생이 없습니다.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doUpdate(){
        try {
            String sql = "UPDATE student SET phone = ? WHERE stdname = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            System.out.print("변경할 이름 입력 : ");
            String name = scanner.nextLine();
            System.out.print("새 전화번호 입력 : ");
            String newPhone = scanner.nextLine();

            int result = pstmt.executeUpdate();
            if (result == 1) {
                System.out.println("전화번호 변경 성공");
            } else {
                System.out.println("해당 이름의 학생이 없습니다.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doFind(){
        try {
            String sql = "SELECT * FROM student WHERE phone = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            System.out.print("검색할 전화번호 : ");
            String phone = scanner.nextLine();
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("학번: " + rs.getInt("stdno") + "\t" +
                        "이름: " + rs.getString("stdname") + "\t" +
                        "전화번호: " + rs.getString("phone") + "\t" +
                        "이메일: " + rs.getString("email"));
            } else {
                System.out.println("해당 전화번호의 학생이 없습니다.");
            }

            pstmt.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

