package database.pratice;

import java.sql.*;
import java.util.Scanner;

public class DBDemo4 {
    public static Connection makeConnection() {
		String url = "jdbc:mysql://localhost/school_db?serverTimezone=Asia/Seoul";
		String userName="root";
		String userPass = "1234";
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			System.out.println("데이터베이스 연결중 ...");
			con = DriverManager.getConnection(url, userName, userPass);
			System.out.println("데이터베이스 연결 성공");
		}catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이버를 찾지 못했습니다...");
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결 실패");
            System.out.println(e.getMessage());
		}
		return con;
	}

    public static EnumMenu getMenu(){
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
            try{
                eNum = scanner.nextInt();
                if (eNum < 5 && eNum >= 0) {
                    break;
                }
            }catch(Exception e){

            }
            System.out.println("잘못 선택 하셨습니다. 숫자 다시 입력(0~4) : ");
        }
        switch (eNum){
			case 1 : return EnumMenu.MENU_LIST;
			case 2 : return EnumMenu.MENU_INSERT;
			case 3 : return EnumMenu.MENU_DELETE;
			case 4 : return EnumMenu.MENU_UPDATE;
			default :
					return EnumMenu.MENU_EXIT;
		}
    }
    public static void main(String[] args) {
        Connection con = makeConnection(); 
        EnumMenu nMenu ;
		
		nMenu = (EnumMenu)getMenu();
		if ( nMenu == EnumMenu.MENU_EXIT )
			System.exit(0);

        switch (nMenu ) {
            case MENU_LIST :
                try {
			    String sql ="select * from dept ";
						
			    PreparedStatement pstmt = con.prepareStatement(sql);
						
			    ResultSet rs = pstmt.executeQuery();
			
			    while(rs.next()) {
				    System.out.println("학과: " + rs.getInt(1) + "\t" + rs.getString("deptcnm") +"\t" + rs.getString("loc"));
			    }
			
			    con.close();
			    pstmt.close();
		        } 
                catch (SQLException e) {
		    	System.out.println(e.getMessage());
		        }
                break;
            
            case MENU_INSERT:
                try {
                    Scanner scanner = new Scanner(System.in);
                    StringBuilder sql = new StringBuilder();
                    sql.append("INSERT INTO dept (deptnb, deptcnm, loc )");
                    sql.append("VALUES (?, ?, ?)");
                    PreparedStatement pstmt = con.prepareStatement(sql.toString());
                    int a;
                    String b,c;
                    System.out.print("학과 번호 : ");
                    a = scanner.nextInt();
                    System.out.print("학과 이름 : ");
                    b = scanner.nextLine();
                    System.out.print("학과 위치 : ");
                    c = scanner.nextLine();
                    pstmt.setString(1, Integer.toString(a));
                    pstmt.setString(2, b);
                    pstmt.setString(3, c);
                    pstmt.execute();
                    con.close();
                    pstmt.close();
                } 
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case MENU_DELETE:
            try {
                    Scanner scanner = new Scanner(System.in);
                    StringBuilder sql = new StringBuilder();
                    sql.append("DELETE FROM dept WHERE deptnb = ?");
                    PreparedStatement pstmt = con.prepareStatement(sql.toString());
                    int a;
                    System.out.print("삭제 할 학과 번호 : ");
                    a = scanner.nextInt();
                    pstmt.setInt(1, a);

                    if (pstmt.executeUpdate() == 1) {
                        System.out.println("삭제 성공");
                    } else {
                        System.out.println("해당 학과 번호가 없습니다.");
                    }
                    pstmt.execute();
                    con.close();
                    pstmt.close();
                } 
                catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case MENU_UPDATE :
                
                break;
        }
    }
}
