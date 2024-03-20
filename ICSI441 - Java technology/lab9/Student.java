import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

class Student implements Serializable {
    String name;
    String id;
    String address;
    double gpa;

    public Student(String name, String id, String address, double gpa) {
        this.name = name;
        this.id = id;
        this.address = address;
        this.gpa = gpa;
    }
}

class StudentChunk implements Serializable {
    private List<Student> students;

    public StudentChunk(List<Student> students) {
        this.students = students;
    }


}

class BinaryFileGenerator {
    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student("Suren Suren", "21b1num1632", "UB Bayangol", 3.8));
        students.add(new Student("Baldan Baldan", "21b1num1631", "UB Sukhbaatar", 3.6));

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            for (Student student : students) {
                oos.writeObject(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String inputFileName = "students.dat";
        String outputFileName = "output";
        int chunkSize = 1;

        SplitBinaryFileTask task = new SplitBinaryFileTask(inputFileName, outputFileName, chunkSize);
        task.fork();
        task.join();
    }
}

class SplitBinaryFileTask extends RecursiveTask<Void> {
    private final String inputFileName;
    private final String outputFileName;
    private final int chunkSize;

    public SplitBinaryFileTask(String inputFileName, String outputFileName, int chunkSize) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.chunkSize = chunkSize;
    }

    @Override
    protected Void compute() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputFileName))) {
            List<Student> students = new ArrayList<>();
            try {
                while (true) {
                    Student student = (Student) ois.readObject();
                    students.add(student);
                }
            } catch (ClassNotFoundException | EOFException e) {
            }

            List<StudentChunk> chunks = new ArrayList<>();
            for (int i = 0; i < students.size(); i += chunkSize) {
                int end = Math.min(i + chunkSize, students.size());
                List<Student> sublist = new ArrayList<>(students.subList(i, end));
                chunks.add(new StudentChunk(sublist));
            }

            for (int i = 0; i < chunks.size(); i++) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFileName + i + ".dat"))) {
                    oos.writeObject(chunks.get(i));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
