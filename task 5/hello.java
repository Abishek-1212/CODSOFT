import java.sql.*;
import java.util.Scanner;

class Student {
	String name;
	int rollNumber;
	int age;
	String grade;

	Student(String name, int rollNumber, int age, String grade) {
		this.name = name;
		this.rollNumber = rollNumber;
		this.age = age;
		this.grade = grade;
	}
}

class StudentManagementSystem {
	Connection conn;

	StudentManagementSystem() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb", "root", "root");
		} catch (SQLException e) {
			System.out.println("Connection error");
		}
	}

	void addStudent(Student s) {
		try {
			String query = "SELECT * FROM students WHERE rollNumber = " + s.rollNumber;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				System.out.println("Student with this roll number already exists.");
				return;
			}

			String sql = "INSERT INTO students VALUES (" + s.rollNumber + ", '" + s.name + "', " + s.age + ", '" + s.grade + "')";
			st.executeUpdate(sql);
			System.out.println("Student added.");
		} catch (SQLException e) {
			System.out.println("Error adding student.");
		}
	}

	void removeStudent(int rollNumber) {
		try {
			String sql = "DELETE FROM students WHERE rollNumber = " + rollNumber;
			Statement st = conn.createStatement();
			int rows = st.executeUpdate(sql);
			if (rows > 0) {
				System.out.println("Student removed.");
			} else {
				System.out.println("Student not found.");
			}
		} catch (SQLException e) {
			System.out.println("Error removing student.");
		}
	}

	void searchStudent(int rollNumber) {
		try {
			String sql = "SELECT * FROM students WHERE rollNumber = " + rollNumber;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				System.out.println("Student Name: " + rs.getString(2));
                System.out.println("Student Roll No: " + rs.getInt(1));
                System.out.println("Student Age:  " + rs.getInt(3));
                System.out.println("Student Grade: " + rs.getString(4));
                System.out.println();
			} else {
				System.out.println("Student not found.");
			}
		} catch (SQLException e) {
			System.out.println("Error searching student.");
		}
	}

	void displayAllStudents() {
		try {
			String sql = "SELECT * FROM students";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			boolean found = false;
			while (rs.next()) {
				found = true;
				System.out.println("Student Name: " + rs.getString(2));
                System.out.println("Student Roll No: " + rs.getInt(1));
                System.out.println("Student Age:  " + rs.getInt(3));
                System.out.println("Student Grade: " + rs.getString(4));
                System.out.println();
			}
			if (!found) {
				System.out.println("No students to display.");
			}
		} catch (SQLException e) {
			System.out.println("Error displaying students.");
		}
	}
	void updateStudent(int rollNumber, String newName, int newAge, String newGrade) {
		try {
			String query = "SELECT * FROM students WHERE rollNumber = " + rollNumber;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (!rs.next()) {
				System.out.println("Student not found.");
				return;
			}

			String sql = "UPDATE students SET name = '" + newName + "', age = " + newAge + ", grade = '" + newGrade + "' WHERE rollNumber = " + rollNumber;
			int rows = st.executeUpdate(sql);
			if (rows > 0) {
				System.out.println("Student updated successfully.");
			} else {
				System.out.println("Update failed.");
			}
		} catch (SQLException e) {
			System.out.println("Error updating student.");
		}
	}

}

public class hello {
	public static void main(String[] args) {
		StudentManagementSystem sms = new StudentManagementSystem();
		Scanner a = new Scanner(System.in);
		System.out.println("welocme to student Database management System.");

		while (true) {

			System.out.print("Choose the option(add/remove/update/search/display/exit): ");
			String choice = a.nextLine();

			if (choice.equals("add")) {
				System.out.print("Student Name: ");
				String name = a.nextLine();
				if (name.equals("")) {
					System.out.println("Name can't be empty.");
					continue;
				}

				System.out.print("Student Roll No: ");
				int roll;
				try {
					roll = Integer.parseInt(a.nextLine());
					if (roll <= 0) {
						System.out.println("Roll number must be positive.");
						continue;
					}
				} catch (Exception e) {
					System.out.println("Invalid roll number.");
					continue;
				}

				System.out.print("Student Age:  ");
				int age;
				try {
					age = Integer.parseInt(a.nextLine());
					if (age <= 0) {
						System.out.println("Age must be positive.");
						continue;
					}
				} catch (Exception e) {
					System.out.println("Invalid age.");
					continue;
				}

				System.out.print("Student Grade: ");
				String grade = a.nextLine();
				if (grade.equals("")) {
					System.out.println("Grade can't be empty.");
					continue;
				}

				Student s = new Student(name, roll, age, grade);
				sms.addStudent(s);

			} else if (choice.equals("remove")) {
				System.out.print("Student Roll No: ");
				int roll;
				try {
					roll = Integer.parseInt(a.nextLine());
					sms.removeStudent(roll);
				} catch (Exception e) {
					System.out.println("Invalid roll number.");
				}

			} else if (choice.equals("search")) {
				System.out.print("Student Roll No: ");
				int roll;
				try {
					roll = Integer.parseInt(a.nextLine());
					sms.searchStudent(roll);
				} catch (Exception e) {
					System.out.println("Invalid roll number.");
				}




			} else if (choice.equals("display")) {
				sms.displayAllStudents();

			} else if (choice.equals("exit")) {
				System.out.println("Thank You for visting student management system.");
				break;
			}

			else if (choice.equals("update")) {
				System.out.print("Student Roll No: ");
				int roll;
				try {
					roll = Integer.parseInt(a.nextLine());
				} catch (Exception e) {
					System.out.println("Invalid roll number.");
					continue;
				}

				System.out.print("New Name: ");
				String newName = a.nextLine();
				if (newName.equals("")) {
					System.out.println("Name can't be empty.");
					continue;
				}

				System.out.print("New Age: ");
				int newAge;
				try {
					newAge = Integer.parseInt(a.nextLine());
					if (newAge <= 0) {
						System.out.println("Age must be positive.");
						continue;
					}
				} catch (Exception e) {
					System.out.println("Invalid age.");
					continue;
				}

				System.out.print("New Grade: ");
				String newGrade = a.nextLine();
				if (newGrade.equals("")) {
					System.out.println("Grade can't be empty.");
					continue;
				}

				sms.updateStudent(roll, newName, newAge, newGrade);
			}



			else {
				System.out.println("Invalid choice.");
			}
		}
		a.close();
	}
}
