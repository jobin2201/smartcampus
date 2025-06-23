//package pojos;
//import campus.Management;
//
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.time.format.ResolverStyle;
//import java.util.Scanner;
//
//public class StudentManagement extends Management {
//
//	public void manageStudents(Connection con, Scanner sc) {
//        while (true) {
//            System.out.println("\n🎓 Student Management:");
//            System.out.println("1. Add Student");
//            System.out.println("2. View Students");
//            System.out.println("3. Update Student");
//            System.out.println("4. Delete Student");
//            System.out.println("5. Back");
//            System.out.print("Enter your choice: ");
//            int ch = sc.nextInt();
//            sc.nextLine();
//
//            try {
//                switch (ch) {
//                    case 1:
//                        try {
//                            String name;
//                            while (true) {
//                                System.out.print("Name: ");
//                                name = sc.nextLine();
//                                if (name.matches("^[a-zA-Z ]+$")) break;
//                                System.out.println("❌ Name must contain only letters and spaces. Try again.");
//                            }
//
//                            String email;
//                            while (true) {
//                                System.out.print("Email: ");
//                                email = sc.nextLine();
//                                if (email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) break;
//                                System.out.println("❌ Invalid email format. Try again.");
//                            }
//
//                            String phone;
//                            while (true) {
//                                System.out.print("Phone: ");
//                                phone = sc.nextLine();
//                                if (phone.matches("\\d{10}")) break;
//                                System.out.println("❌ Phone must be 10 digits.");
//                            }
//
//                            String dob;
//                            while (true) {
//                                System.out.print("DOB (yyyy-mm-dd): ");
//                                dob = sc.nextLine();
//
//                                try {
//                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd")
//                                        .withResolverStyle(ResolverStyle.STRICT);
//
//                                    LocalDate parsedDate = LocalDate.parse(dob, formatter);
//
//                                    if (parsedDate.getYear() >= 2025) {
//                                        System.out.println("❌ Year must be before 2025.");
//                                        continue;
//                                    }
//                                    break;
//                                } catch (DateTimeParseException e) {
//                                    System.out.println("❌ Invalid date. Use proper yyyy-mm-dd and valid date.");
//                                }
//                            }
//
//                            String gender;
//                            while (true) {
//                                System.out.print("Gender (Male/Female): ");
//                                gender = sc.nextLine();
//                                if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) break;
//                                System.out.println("❌ Enter 'Male' or 'Female' only.");
//                            }
//
//                            String dept;
//                            while (true) {
//                                System.out.print("Department: ");
//                                dept = sc.nextLine();
//                                if (dept.matches("^[a-zA-Z ]+$")) break;
//                                System.out.println("❌ Department must contain only letters and spaces.");
//                            }
//
//                            int year;
//                            while (true) {
//                                System.out.print("Year of Study: ");
//                                String yearInput = sc.nextLine();
//                                try {
//                                    year = Integer.parseInt(yearInput);
//                                    if (year >= 1 && year <= 6) break;
//                                    System.out.println("❌ Year must be between 1 and 6.");
//                                } catch (NumberFormatException e) {
//                                    System.out.println("❌ Enter a valid numeric year.");
//                                }
//                            }
//
//                            PreparedStatement ps = con.prepareStatement(
//                                "INSERT INTO student (name, email, phone, dob, gender, department, year_of_study) VALUES (?, ?, ?, ?, ?, ?, ?)"
//                            );
//                            ps.setString(1, name);
//                            ps.setString(2, email);
//                            ps.setString(3, phone);
//                            ps.setDate(4, Date.valueOf(dob));
//                            ps.setString(5, gender);
//                            ps.setString(6, dept);
//                            ps.setInt(7, year);
//                            ps.executeUpdate();
//
//                            System.out.println("✅ Student Added!");
//                        } catch (Exception e) {
//                            System.out.println("❌ Error: " + e.getMessage());
//                        }
//                        break;
//
//                    case 2:
//                        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM student");
//                        while (rs.next()) {
//                            System.out.println(rs.getInt("student_id") + " | " + rs.getString("name") + " | " + rs.getString("department"));
//                        }
//                        break;
//
//                    case 3:
//                        System.out.print("Student ID to Update: ");
//                        int sid = sc.nextInt();
//                        sc.nextLine();
//                        System.out.print("New Email: ");
//                        String newEmail = sc.nextLine();
//                        PreparedStatement update = con.prepareStatement("UPDATE student SET email=? WHERE student_id=?");
//                        update.setString(1, newEmail);
//                        update.setInt(2, sid);
//                        update.executeUpdate();
//                        System.out.println("✅ Student Updated!");
//                        break;
//
//                    case 4:
//                        System.out.print("Student ID to Delete: ");
//                        int delId = sc.nextInt();
//                        PreparedStatement del = con.prepareStatement("DELETE FROM student WHERE student_id=?");
//                        del.setInt(1, delId);
//                        del.executeUpdate();
//                        System.out.println("🗑 Student Deleted!");
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
