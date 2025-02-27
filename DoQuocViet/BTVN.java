import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String className;
    private Map<String, Double> grades; 

    public Student(String firstName, String lastName, LocalDate birthDate, String address, String className) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.className = className;
        this.grades = new HashMap<>();
    }

    public void setGrade(String subject, double grade) {
        grades.put(subject, grade);
    }

    public double getGrade(String subject) {
        return grades.getOrDefault(subject, 0.0);
    }

    public String getClassName() {
        return className;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getGradeRank() {
        double averageGrade = grades.values().stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
        if (averageGrade >= 9.0) return "A";
        else if (averageGrade >= 8.0) return "B";
        else if (averageGrade >= 7.0) return "C";
        else if (averageGrade >= 6.0) return "D";
        else return "<D";
    }
}

class Classroom {
    private String classCode;
    private List<Student> students;

    public Classroom(String classCode) {
        this.classCode = classCode;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Map<String, Integer> getGradeRankSummary() {
        Map<String, Integer> rankSummary = new HashMap<>();
        rankSummary.put("A", 0);
        rankSummary.put("B", 0);
        rankSummary.put("C", 0);
        rankSummary.put("D", 0);
        rankSummary.put("<D", 0);

        for (Student student : students) {
            String rank = student.getGradeRank();
            rankSummary.put(rank, rankSummary.get(rank) + 1);
        }

        return rankSummary;
    }

    public String getClassCode() {
        return classCode;
    }
}



class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Dữ liệu mẫu
        Classroom class1 = new Classroom("64KTPM1");
        Classroom class2 = new Classroom("64KTPM2");
        
        Student student1 = new Student("Le", "Tien", LocalDate.of(2001, 2, 2), "456 Avenue", "64KTPM1");
        student1.setGrade("LT OOP", 7.0);
        student1.setGrade("Quan ly du an", 6.5);
        student1.setGrade("Hoc May", 7.0);
        student1.setGrade("CSDL", 6.0);
        student1.setGrade("Lap trinh ung dung cho TBDĐ", 7.5);

        Student student2 = new Student("Bui", "Binh", LocalDate.of(2000, 1, 1), "123 Street", "64KTPM2");
        student2.setGrade("LT OOP", 8.5);
        student2.setGrade("Quan ly du an", 9.0);
        student2.setGrade("Hoc May", 7.5);
        student2.setGrade("CSDL", 8.0);
        student2.setGrade("Lap trinh ung dung cho TBDĐ", 8.5);

        Student student3 = new Student("Do", "Viet", LocalDate.of(2000, 1, 1), "133 Arial", "64KTPM2");
        student3.setGrade("LT OOP", 5.5);
        student3.setGrade("Quan ly du an", 7.0);
        student3.setGrade("Hoc May", 6.5);
        student3.setGrade("CSDL", 4.0);
        student3.setGrade("Lap trinh ung dung cho TBDĐ", 8.5);

        class1.addStudent(student1);
        class2.addStudent(student2);
        class2.addStudent(student3);

        List<Classroom> classrooms = new ArrayList<>();
        classrooms.add(class1);
        classrooms.add(class2);

        System.out.println("Danh sach cac lop:");
        for (Classroom classroom : classrooms) {
            System.out.println(classroom.getClassCode());
        }

        System.out.print("Nhap ma lop de hien thi danh sach sinh vien: ");
        String classCode = scanner.nextLine();

        Classroom selectedClass = classrooms.stream()
                .filter(c -> c.getClassCode().equals(classCode))
                .findFirst()
                .orElse(null);

        if (selectedClass != null) {
            System.out.println("Danh sach sinh vien lop " + classCode + ":");
            for (Student student : selectedClass.getStudents()) {
                System.out.println(student.getFullName() + " - Rank: " + student.getGradeRank());
            }

            Map<String, Integer> rankSummary = selectedClass.getGradeRankSummary();
            System.out.println("Tong ket so nguoi nguoi theo rank:");
            for (Map.Entry<String, Integer> entry : rankSummary.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Khong tim thay lop voi ma " + classCode);
        }
    }
}