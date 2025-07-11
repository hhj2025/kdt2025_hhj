package DAOstudent;

public class Student {
    private int stdno;
    private String stdname;
    private String phone;
    private String email;

    public Student(int stdno, String stdname, String phone, String email) {
        this.stdno = stdno;
        this.stdname = stdname;
        this.phone = phone;
        this.email = email;
    }

    public int getStdno() { return stdno; }
    public String getStdname() { return stdname; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    public void setPhone(String phone) { this.phone = phone; }

    @Override
    public String toString() {
        return String.format("학번: %d, 이름: %s, 전화번호: %s, 이메일: %s", stdno, stdname, phone, email);
    }
}