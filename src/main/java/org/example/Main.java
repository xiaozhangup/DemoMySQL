package org.example;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        String sql = "jdbc:mysql://127.0.0.1:49153/debug";
        String sql_user = "xiaozhangup";
        String sql_pwd = "xiaozhang123";
        var conn = DriverManager.getConnection(sql,sql_user,sql_pwd);

        Statement st = conn.createStatement();

        ResultSet re = conn.getMetaData().getTables(null, null, "student", null);
        if (!re.next()) {
            String command = "create table student ( "
                    + "id char,"
                    + "name char,"
                    + "score integer" + ")";
            st.executeUpdate(command);
        }

        String r1 = "insert into student values('0001', '小王', 80)";
        String r2 = "insert into student values('0002', '小李', 82)";
        String r3 = "insert into student values('0003', '小张', 90)";

        st.executeUpdate(r1);
        st.executeUpdate(r2);
        st.executeUpdate(r3);

        st.executeUpdate("delete from student where id=0001");

        st.executeUpdate("update student set name='LZX' where id=0003");

        ResultSet rs = st.executeQuery("select * from student where id=0002");
        while (rs.next()) {
            System.out.println(rs.getString("name"));
        }
    }
}