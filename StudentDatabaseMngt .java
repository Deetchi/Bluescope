import java.sql.*;
import java.util.*;


public class StudentDatabaseMngt {

    public static void insert(List<Map<String,Object>> studentDatas) {
        Connection conn=null;
        String query = "INSERT INTO studentsData (name,age,degree,field,yop) VALUES (?, ?, ?,?,?)";
        PreparedStatement preparedStat=null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3303/studentDb?user=deetchi&password=harini");
            preparedStat = conn.prepareStatement(query);

            for (Map<String, Object> student : studentDatas) {
                String name = (String) student.get("name");
                int age = (int) student.get("age");
                String degree=(String) student.get("degree");
                String field=(String)student.get("field");
                int yop=(int)student.get("yop");

                preparedStat.setString(1, name);
                preparedStat.setInt(2, age);
                preparedStat.setString(3,degree);
                preparedStat.setString(4,field);
                preparedStat.setInt(5,yop);
                preparedStat.executeUpdate();
                System.out.println("inserted!!!!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static List<Map<String, Object>> getAllStudents() {
        List<Map<String,Object>> studentDetails = new ArrayList<>();
        Connection conn=null;
        PreparedStatement preparedStat=null;
        String query = "SELECT * FROM studentsData";

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3303/studentDb?user=deetchi&password=harini");
            preparedStat = conn.prepareStatement(query);
            ResultSet result = preparedStat.executeQuery();

            while (result.next()) {
                String name = result.getString("name");
                int age = result.getInt("age");
                String degree = result.getString("degree");
                String field = result.getString("field");
                int yop=result.getInt("yop");

                Map<String,Object> std = new HashMap<>();
                std.put("name",name);
                std.put("age",age);
                std.put("degree",degree);
                std.put("field",field);
                std.put("yop",yop);
                studentDetails.add(std);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentDetails;
    }

    public static void main(String[] args) {
        Map<String,Object> student1=new HashMap<>();
        student1.put("name","Deetchi");
        student1.put("age",22);
        student1.put("degree","BE");
        student1.put("field","CSE");
        student1.put("yop",2022);

        Map<String,Object> student2=new HashMap<>();
        student2.put("name","Nithish");
        student2.put("age",20);
        student2.put("degree","Bsc");
        student2.put("field","Multimedia");
        student2.put("yop",2024);

        List<Map<String,Object>> studentInfo=new ArrayList<>();
        studentInfo.add(student1);
        studentInfo.add(student2);

       insert(studentInfo);


        List<Map<String,Object>> StudentDetails = getAllStudents();
        System.out.println("Students from the Database:");
        StudentDetails.forEach(
                System.out::println
        );
    }
}

