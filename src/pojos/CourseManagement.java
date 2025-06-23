//package pojos;
//import campus.Management;
//
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//public class CourseManagement extends Management {
//
//    public void manageCourses(Connection con, Scanner sc) {
//        while (true) {
//            System.out.println("\n📘 Course Management:");
//            System.out.println("1. Add Course");
//            System.out.println("2. View Courses");
//            System.out.println("3. Update Course");
//            System.out.println("4. Delete Course");
//            System.out.println("5. Simulate Concurrent Enrollments");
//            System.out.println("6. Back");
//            System.out.print("Enter your choice: ");
//            int ch = sc.nextInt();
//            sc.nextLine();
//
//            try {
//                switch (ch) {
//                    case 1:
//                        System.out.print("Course Name: ");
//                        String cname = sc.nextLine();
//                        System.out.print("Credits: ");
//                        int credits = sc.nextInt();
//                        sc.nextLine();
//                        System.out.print("Department: ");
//                        String dept = sc.nextLine();
//                        System.out.print("Semester: ");
//                        int semester = sc.nextInt();
//                        System.out.print("Faculty ID: ");
//                        int fid = sc.nextInt();
//
//                        PreparedStatement ps = con.prepareStatement(
//                            "INSERT INTO course (course_name, credits, department, semester, faculty_id) VALUES (?, ?, ?, ?, ?)");
//                        ps.setString(1, cname);
//                        ps.setInt(2, credits);
//                        ps.setString(3, dept);
//                        ps.setInt(4, semester);
//                        ps.setInt(5, fid);
//                        ps.executeUpdate();
//                        System.out.println("Course Added!");
//                        break;
//
//                    case 2:
//                        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM course");
//                        while (rs.next()) {
//                            System.out.println(rs.getInt("course_id") + " | " +
//                                               rs.getString("course_name") + " | Credits: " + rs.getInt("credits"));
//                        }
//                        break;
//
//                    case 3:
//                        System.out.print("Course ID to Update: ");
//                        int cid = sc.nextInt();
//                        sc.nextLine();
//                        System.out.print("New Course Name: ");
//                        String newName = sc.nextLine();
//                        PreparedStatement update = con.prepareStatement("UPDATE course SET course_name=? WHERE course_id=?");
//                        update.setString(1, newName);
//                        update.setInt(2, cid);
//                        update.executeUpdate();
//                        System.out.println("Course Updated!");
//                        break;
//
//                    case 4:
//                        System.out.print("Course ID to Delete: ");
//                        int delId = sc.nextInt();
//                        PreparedStatement del = con.prepareStatement("DELETE FROM course WHERE course_id=?");
//                        del.setInt(1, delId);
//                        del.executeUpdate();
//                        System.out.println("Course Deleted!");
//                        break;
//
//                    case 5:
//                        simulateEnrollmentConcurrency(con);
//                        break;
//
//                    case 6:
//                        return;
//
//                    default:
//                        System.out.println("Invalid Choice");
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    
//    
//   
//    //the below code is for concurrency and threading-leave for now
//    private void simulateEnrollmentConcurrency(Connection con) {
//        Scanner sc = new Scanner(System.in);
//
//        // Department seat mapping
//        Map<String, Integer> seatMap = new HashMap<>();
//        seatMap.put("CSE", 6);
//        seatMap.put("ECE", 5);
//        seatMap.put("AGRI", 9);
//
//        System.out.print("Enter the department (CSE, ECE, AGRI): ");
//        String dept = sc.nextLine().trim().toUpperCase();
//
//        if (!seatMap.containsKey(dept)) {
//            System.out.println("Department not found. Please check the proper department.");
//            return;
//        }
//
//        // Get available seats
//        int seats = seatMap.get(dept);
//        System.out.println( dept + " Department has " + seats + " seats available.");
//        System.out.println("Simulating 10 students applying...");
//
//        final int[] availableSeats = {seats}; 
//        final Object lock = new Object();
//        final int totalApplicants = 10;
//        final int[] failedCount = {0};
//        List<String> enrolledStudents = new ArrayList<>();
//
//
//        class EnrollmentTask implements Runnable {
//            private String studentName;
//
//            public EnrollmentTask(String studentName) {
//                this.studentName = studentName;
//            }
//
//            @Override
//            public void run() {
//                synchronized (lock) {
//                	if (availableSeats[0] > 0) {
//                	    System.out.println(studentName + " enrolled ✅ | Remaining seats: " + (--availableSeats[0]));
//                	    enrolledStudents.add(studentName); // Track enrolled student
//                	} else {
//                	    System.out.println(studentName + " failed ❌ | No seats left");
//                	    failedCount[0]++;
//                	}
//                }
//            }
//        }
//
//        Thread[] threads = new Thread[totalApplicants];
//        for (int i = 0; i < totalApplicants; i++) {
//            threads[i] = new Thread(new EnrollmentTask("Student_" + (i + 1)));
//        }
//
//        for (Thread t : threads) t.start();
//        for (Thread t : threads) {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println("\n📊 Enrollment Complete:");
//        System.out.println("Total Applied: " + totalApplicants);
//        System.out.println("Successfully Enrolled: " + (totalApplicants - failedCount[0]));
//        System.out.println("Not Enrolled: " + failedCount[0]);
//        
//        //FOR COURSES 
//        System.out.println("\n📘 Now checking course-wise enrollment for department: " + dept);
//
//        try {
//			// Fetch courses for the department
//            PreparedStatement ps = con.prepareStatement("SELECT course_id, course_name FROM course WHERE department = ?");
//            ps.setString(1, dept);
//            ResultSet rs = ps.executeQuery();
//            // Map to track which students got into which courses
//            Map<String, List<String>> studentToCourses = new HashMap<>();
//
//            // Map to track how many students are in each course
//            Map<String, List<String>> courseToStudents = new HashMap<>();
//
//
//            while (rs.next()) {
//                int courseId = rs.getInt("course_id");
//                String courseName = rs.getString("course_name");
//
//                System.out.println("\n📗 Course: " + courseName + " (ID: " + courseId + ")");
//                final int[] courseSeats = {3}; // course-level seat limit
//                final int[] courseFailed = {0};
//                final Object courseLock = new Object();
//
//                // Show available seats in this course
//                System.out.println("Available seats for this course: " + courseSeats[0]);
//
//                // Simulate only as many students as enrolled in department
//                System.out.println("Available seats for this course: " + courseSeats[0]);
//
//                Thread[] courseThreads = new Thread[enrolledStudents.size()];
//                for (int i = 0; i < enrolledStudents.size(); i++) {
//                    final String studentName = enrolledStudents.get(i);
//                    courseThreads[i] = new Thread(() -> {
//                        synchronized (courseLock) {
//                        	if (courseSeats[0] > 0) {
//                        	    System.out.println("✅ " + studentName + " got into " + courseName);
//                        	    courseSeats[0]--;
//
//                        	    synchronized (courseToStudents) {
//                        	        courseToStudents.computeIfAbsent(courseName, k -> new ArrayList<>()).add(studentName);
//                        	    }
//
//                        	    synchronized (studentToCourses) {
//                        	        studentToCourses.computeIfAbsent(studentName, k -> new ArrayList<>()).add(courseName);
//                        	    }
//
//                        	} else {
//                        	    System.out.println("❌ " + studentName + " could not enroll in " + courseName + ". Check other courses as well.");
//                        	    courseFailed[0]++;
//                        	}
//                        }
//                    });
//                }
//
//
//                for (Thread t : courseThreads) t.start();
//                for (Thread t : courseThreads) t.join();
//
//                System.out.println("📊 Summary for " + courseName + ":");
//                System.out.println("Enrolled: " + (5 - courseFailed[0]));
//                System.out.println("Not Enrolled: " + courseFailed[0]);
//            }
//            System.out.println("\n📌 Final Course Enrollments:");
//            for (Map.Entry<String, List<String>> entry : courseToStudents.entrySet()) {
//                System.out.println("📘 Course: " + entry.getKey() + " | Total Enrolled: " + entry.getValue().size());
//            }
//
//            System.out.println("\n👩‍🎓 Course-wise Student Mapping:");
//            for (Map.Entry<String, List<String>> entry : studentToCourses.entrySet()) {
//                System.out.println("✅ " + entry.getKey() + " got into: " + String.join(", ", entry.getValue()));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
