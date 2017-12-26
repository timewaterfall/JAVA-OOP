import java.util.Arrays;

public class Group {

    private Student[] students = new Student[10];
    private int count;
    private String nameGroup;

    Group(String nameGroup) {
        setNameGroup(nameGroup);
        setCount();
    }

    public Group() {
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }

    private String getNameGroup() {
        return nameGroup;
    }

    private void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    private void setCount() {
        this.count = 0;
    }

    void addStudent(Student student) {
        System.out.println("added " + student);

        try {
            if (count >= 10) {
                throw new MyException();
            }
            students[count] = student;
            count++;
        } catch (MyException e) {
            System.out.println(student.getName() + " " + student.getLastName() + " could not add - " + e.getLocalizedMessage());
        }
    }

    void deleteStudent(String lastName) {
        boolean switchIf = true;

        System.out.println("deleting " + lastName + "..........");
        for (int i = 0; i < students.length; i++) {
            if (!(students[i] == null) && students[i].getLastName().equals(lastName)) {
                students[i] = null;
                switchIf = false;
            }
        }
        if (switchIf) {
            System.out.println("..........there is no such student");
        }
    }

    String searchStudent(String lastName) {
        String result = "";
        boolean switchIf = true;

        System.out.println("search " + lastName + "..........");
        for (Student student : students) {
            if ((!(student == null)) && student.getLastName().equals(lastName)) {
                result = student.toString();
                switchIf = false;
            }
        }
        if (switchIf) {
            result = "..........there is no such student";
        }
        return result;
    }

    public void studentToDisplay() {

        System.out.println("Group  " + getNameGroup() + ":");
        for (Student student : students) {
            if (!(student == null)) System.out.println(student.toString());
        }
    }

    public void sortArray() {

        int countCharLength = students.length;
        for (Student student : students) {
            if (student == null) countCharLength--;
        }
        char[] lastNames = new char[countCharLength];
        for (int i = 0; i < countCharLength; i++) {
            if (!(students[i] == null)) lastNames[i] = students[i].getLastName().charAt(0);
        }


        Arrays.sort(lastNames);

        for (int i = 0; i < lastNames.length; i++) {
            char a = lastNames[i];
            if (!(a == '\u0000')) {
                for (int j = i; j <= students.length - i - 1; j++) {
                    if ((!(students[j] == null)) && students[j].getLastName().charAt(0) < a) {
                        Student temp = students[j];
                        students[j] = students[j - 1];
                        students[j - 1] = temp;
                    }
                }
            }

        }
    }


    @Override
    public String toString() {
        sortArray();
        StringBuilder out = new StringBuilder();
        for (Student student : students) {
            if (!(student == null)) out.append(student.toString()).append("\n");
        }

        return out.toString();
    }
}

class MyException extends Exception {
    @Override
    public String getLocalizedMessage() {
        return " there is no place o_O";

    }
}
