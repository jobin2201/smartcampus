//package pojos;
//import campus.Management;
//
//
//import java.sql.*;
//import java.util.Scanner;
//
//public class FacultyManagement extends Management {
//
//    public void manageFaculty(Connection con, Scanner sc) {
//        while (true) {
//            System.out.println("\n👨‍🏫 Faculty Management:");
//            System.out.println("1. Add Faculty");
//            System.out.println("2. View Faculty");
//            System.out.println("3. Update Faculty");
//            System.out.println("4. Delete Faculty");
//            System.out.println("5. Back");
//            System.out.print("Enter your choice: ");
//            int ch = sc.nextInt();
//            sc.nextLine();
//
//            try {
//                switch (ch) {
//                    case 1:
//                        System.out.print("Name: ");
//                        String name = sc.nextLine();
//                        System.out.print("Email: ");
//                        String email = sc.nextLine();
//                        System.out.print("Department: ");
//                        String dept = sc.nextLine();
//                        System.out.print("Designation: ");
//                        String designation = sc.nextLine();
//
//                        PreparedStatement ps = con.prepareStatement("INSERT INTO faculty (name, email, department, designation) VALUES (?, ?, ?, ?)");
//                        ps.setString(1, name);
//                        ps.setString(2, email);
//                        ps.setString(3, dept);
//                        ps.setString(4, designation);
//                        ps.executeUpdate();
//                        System.out.println("Faculty Added!");
//                        break;
//
//                    case 2:
//                        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM faculty");
//                        while (rs.next()) {
//                            System.out.println(rs.getInt("faculty_id") + " | " + rs.getString("name") + " | " + rs.getString("department"));
//                        }
//                        break;
//
//                    case 3:
//                        System.out.print("Faculty ID to Update: ");
//                        int fid = sc.nextInt();
//                        sc.nextLine();
//                        System.out.print("New Email: ");
//                        String newEmail = sc.nextLine();
//                        PreparedStatement update = con.prepareStatement("UPDATE faculty SET email=? WHERE faculty_id=?");
//                        update.setString(1, newEmail);
//                        update.setInt(2, fid);
//                        update.executeUpdate();
//                        System.out.println("Faculty Updated!");
//                        break;
//
//                    case 4:
//                        System.out.print("Faculty ID to Delete: ");
//                        int delId = sc.nextInt();
//                        PreparedStatement del = con.prepareStatement("DELETE FROM faculty WHERE faculty_id=?");
//                        del.setInt(1, delId);
//                        del.executeUpdate();
//                        System.out.println("Faculty Deleted!");
//                        break;
//
//                    case 5:
//                        return;
//
//                    default:
//                        System.out.println("❌ Invalid Choice");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
