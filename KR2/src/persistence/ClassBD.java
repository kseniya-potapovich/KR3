package persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ClassBD implements InterfBD {

    private static final String delimiters = "[ _;]";

    private static final String rezdel = "rezdel.txt";

    private static final String rezreg = "rezreg.txt";

    private static final String rezsort = "rezsort.txt";

    private static final String rezjson = "rezjson.txt";

    private static final List<Student> studentList = new ArrayList<>();

    private static final String digitRegex = ".*\\d.*";

    private static final Pattern fileNameRegex = Pattern.compile("(.*\\.gif)||(.*\\.bmp)||(.*\\.jpg)");

    private static final Map<Integer, Student> studentMap = new HashMap<>();

    @Override
    public BufferedReader getFileReader(String filePath) throws FileNotFoundException {
        return new BufferedReader(new FileReader(filePath));
    }

    public void readInList(String fileName) throws IOException {
        BufferedReader br = getFileReader(fileName);
        String st;
        int id = 1;
        while ((st = br.readLine()) != null) {
            String[] p = st.split(delimiters);
            studentList.add(new Student(id, p[0], p[1], Double.parseDouble(p[2]), p[3]));
            id++;
        }
        br.close();
        ;
    }

    public void readInMap(String fileName) throws IOException {
        BufferedReader br = getFileReader(fileName);
        String st;
        int id = 1;
        while ((st = br.readLine()) != null) {
            String[] p = st.split(delimiters);
            studentMap.put(id, new Student(id, p[0], p[1], Double.parseDouble(p[2]), p[3]));
            id++;
        }
    }

    public void deleteWrongStudents() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(rezdel));
        Iterator<Student> iterator = studentList.iterator();
        Student student;
        while (iterator.hasNext()) {
            student = iterator.next();
            if (student.getFirstName().matches(digitRegex) || student.getLastName().matches(digitRegex)) {
                writer.append(student.toString()).append("\n------------------------------\n");
                studentMap.remove(student.getId());
                iterator.remove();
            }
        }
        writer.close();
    }

    public void findStudents() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(rezreg));
        for (Student student : studentList) {
            if (fileNameRegex.matcher(student.getFileName()).matches()) {
                writer.append(student.toString()).append("\n------------------------------\n");
            }
        }
        writer.close();
    }

    public void sortStudents() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(rezsort));
        studentList.stream().sorted(Student::compareTo).forEach(s -> {
            try {
                writer.append(s.toString()).append("\n------------------------------\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        writer.close();
    }

    public void studentsToJson() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(rezjson), studentList);
    }


}
