import java.util.*;

// 1. Interface Segregation Principle (ISP)

interface StudentManagement {
  String getName();
  String getID();
  String getYear();
  void enrollInCourse(CourseManagement course); 
}

interface CourseManagement {
  String getCourseID();
  String getCourseName();
  String getDepartment();
  String getSemester();
  void addStudent(StudentManagement student);
}

interface Enrollment {
  void enroll(StudentManagement student, CourseManagement course);
}

// 2. Dependency Inversion Principle (DIP)

class EnrollmentManager implements Enrollment {

  @Override
  public void enroll(StudentManagement student, CourseManagement course) {
    student.enrollInCourse(course);
    course.addStudent(student);
  }
}

// 3. Single Responsibility Principle (SRP)

class Student implements StudentManagement {
  private String name;
  private String ID;
  private Year year;
  private List<String> enrolledCourses; // Store course IDs as Strings

  public Student(String name, String ID, Year year) {
    this.name = name;
    this.ID = ID;
    this.year = year;
    this.enrolledCourses = new ArrayList<>();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getID() {
    return ID;
  }

  @Override
  public String getYear() {
    return year.getYear();
  }

  @Override
  public void enrollInCourse(CourseManagement course) {
    enrolledCourses.add(course.getCourseID() + "-" + course.getCourseName()); // Concatenate ID and Name
  }

  public List<String> getEnrolledCourses() {
    return enrolledCourses;
  }
}

class Year {
  private String yearString;

  public Year(String yearString) {
    this.yearString = yearString;
  }

  public String getYear() {
    return yearString;
  }
}

// 4. Open/Closed Principle (OCP)

abstract class Course implements CourseManagement {
  private String courseID;
  private String courseName;
  private String department;
  private String semester;
  private List<String> enrolledStudents;

  public Course(String courseID, String courseName, String department, String semester) {
    this.courseID = courseID;
    this.courseName = courseName;
    this.department = department;
    this.semester = semester;
    this.enrolledStudents = new ArrayList<>();
  }

  @Override
  public String getCourseID() {
    return courseID;
  }

  @Override
  public String getCourseName() {
    return courseName;
  }

  @Override
  public String getDepartment() {
    return department;
  }

  @Override
  public String getSemester() {
    return semester;
  }

  @Override
  public void addStudent(StudentManagement student) {
    enrolledStudents.add(student.getName() + " (" + student.getID() + ")"); 
  }

  public List<String> getEnrolledStudents() {
    return enrolledStudents;
  }
}

class RegularCourse extends Course {

  public RegularCourse(String courseID, String courseName, String department, String semester) {
    super(courseID, courseName, department, semester);
  }
}

public class Main {
  public static void main(String[] args) {
    Student student = new Student("Bala Aditya", "2320030043", new Year("2nd year"));
    Course course = new RegularCourse("23CS2103R", "Advanced Object Oriented Programming", "CSE", "Even Sem");

    Enrollment enrollmentManager = new EnrollmentManager();
    enrollmentManager.enroll(student, course);

    System.out.println("STUDENT DETAILS : ");
    System.out.println("Student Name: " + student.getName());
    System.out.println("Student ID: " + student.getID());
    System.out.println("Student Year: " + student.getYear());
    System.out.println("Enrolled Courses for " + student.getName() + ": " + student.getEnrolledCourses()); 
    System.out.println("COURSE DETAILS : ");
    System.out.println("Course Name: " + course.getCourseName());
    System.out.println("Course ID: " + course.getCourseID());
    System.out.println("Course Department: " + course.getDepartment());
    System.out.println("Course Semester: " + course.getSemester());
    System.out.println("Enrolled Students in " + course.getCourseName() + ": " + course.getEnrolledStudents()); 
  }
}