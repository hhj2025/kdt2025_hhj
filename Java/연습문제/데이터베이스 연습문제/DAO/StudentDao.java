package DAOstudent;

import java.util.List;

public interface StudentDao {
    public int insert(Student student);
    public int updatePhone(String name, String newPhone);
    public int delete(String name);
    public List<Student> selectAll();
    public Student selectByPhone(String phone);
}