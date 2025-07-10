
import java.sql.*;
import java.util.Scanner;

public class DBDemo4 {
    static Connection con = makeConnection();
    public static void main(String[] args) throws SQLException {
        EnumMenu nMenu;
        DBDemo4 dbDemo4 = new DBDemo4();
        while (true) {
            nMenu = getMenu();
            if (nMenu == EnumMenu.MENU_EXIT) {
                System.out.println("프로그램 종료");
                con.close();
                break;
            }

            switch (nMenu) {
                case MENU_LIST:
                    dbDemo4.doList();
                    break;
                case MENU_INSERT:
                    dbDemo4.doInsert();
                    break;
                case MENU_DELETE:
                    dbDemo4.doDelete();
                    break;
                case MENU_UPDATE:
                    dbDemo4.doUpdate();
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
        Scanner scanner = new Scanner(System.in);
        int eNum;
        while (true) {
            System.out.println("-----------------------------------------------------");
            System.out.println("1. 모든 학과 정보 보기");
            System.out.println("2. 학과 추가하기");
            System.out.println("3. 학과 삭제하기");
            System.out.println("4. 학과 정보 수정하기...(호실만 수정)");
            System.out.println("0. 종료하기...");
            System.out.println("-----------------------------------------------------");
            System.out.print("메뉴를 입력하세요 : ");
            try {
                eNum = Integer.parseInt(scanner.nextLine());
                if (eNum < 5 && eNum >= 0) {
                    break;
                }
            } catch (Exception e) {
            }
            System.out.println("잘못 선택 하셨습니다. 숫자 다시 입력(0~4) : ");
        }
        return switch (eNum) {
            case 1 -> EnumMenu.MENU_LIST;
            case 2 -> EnumMenu.MENU_INSERT;
            case 3 -> EnumMenu.MENU_DELETE;
            case 4 -> EnumMenu.MENU_UPDATE;
            default -> EnumMenu.MENU_EXIT;
        };
    }

    public void doList(){
        try {
            String sql = "select * from dept ";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("학과: " + rs.getInt(1) + "\t" + rs.getString("deptcnm") + "\t" + rs.getString("loc"));
            }

            pstmt.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doInsert(){
        try {
            Scanner scanner = new Scanner(System.in);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO dept (deptnb, deptcnm, loc )");
            sql.append("VALUES (?, ?, ?)");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int a;
            String b, c;
            System.out.print("학과 번호 : ");
            a = Integer.parseInt(scanner.next());
            System.out.print("학과 이름 : ");
            b = scanner.next();
            System.out.print("loc : ");
            c = scanner.next();

            pstmt.setInt(1, a);
            pstmt.setString(2, b);
            pstmt.setString(3, c);
            pstmt.execute();

            pstmt.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doDelete(){
        try {
            Scanner scanner = new Scanner(System.in);
            StringBuilder sql = new StringBuilder();
            sql.append("DELETE FROM dept WHERE deptnb = ?");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int a;
            while (true) {
                System.out.print("삭제 할 학과 번호 : ");
                a = Integer.parseInt(scanner.nextLine());
                pstmt.setInt(1, a);
                if (pstmt.executeUpdate() == 1) {
                    System.out.println("삭제 성공");
                    break;
                } else {
                    System.out.println("해당 학과 번호가 없습니다. 다시 입력하세요.");
                }
            }

            pstmt.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void doUpdate(){
        try {
            Scanner scanner = new Scanner(System.in);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE dept SET loc = ? WHERE deptnb = ?");
            PreparedStatement pstmt = con.prepareStatement(sql.toString());
            int a;
            String b;
            while (true) {
                System.out.print("loc : ");
                b = scanner.nextLine();
                System.out.print("교체 할 학과 번호 : ");
                a = Integer.parseInt(scanner.nextLine());
                pstmt.setString(1, b);
                pstmt.setInt(2, a);
                if (pstmt.executeUpdate() == 1) {
                    System.out.println("교체 성공");
                    break;
                } else {
                    System.out.println("해당 학과 번호가 없습니다. 다시 입력하세요.");
                }
            }

            pstmt.close();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}

