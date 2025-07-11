package DAOstudent;

import java.sql.*;
import java.util.*;

public class StudentDaoImpl implements StudentDao {
    private Connection conn;

    public StudentDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public int insert(Student student) {
        int result = 0;
        String sql = "INSERT INTO student (stdno, stdname, phone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getStdno());
            pstmt.setString(2, student.getStdname());
            pstmt.setString(3, student.getPhone());
            pstmt.setString(4, student.getEmail());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert 실패: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int updatePhone(String name, String newPhone) {
        int result = 0;
        String sql = "UPDATE student SET phone = ? WHERE stdname = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPhone);
            pstmt.setString(2, name);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Update 실패: " + e.getMessage());
        }
        return result;
    }

    @Override
    public int delete(String name) {
        int result = 0;
        String sql = "DELETE FROM student WHERE stdname = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Delete 실패: " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Student(
                    rs.getInt("stdno"),
                    rs.getString("stdname"),
                    rs.getString("phone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SelectAll 실패: " + e.getMessage());
        }
        return list;
    }

    @Override
    public Student selectByPhone(String phone) {
        Student result = null;
        String sql = "SELECT * FROM student WHERE phone = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phone);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result = new Student(
                    rs.getInt("stdno"),
                    rs.getString("stdname"),
                    rs.getString("phone"),
                    rs.getString("email")
                );
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("SelectByPhone 실패: " + e.getMessage());
        }
        return result;
    }
}