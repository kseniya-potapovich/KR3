import persistence.ClassBD;
import persistence.InterfBD;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClassBD bd = new ClassBD();
        String file = "test1.txt";
        bd.readInList(file);
        bd.readInMap(file);
        bd.deleteWrongStudents();
        bd.findStudents();
        bd.sortStudents();
        bd.studentsToJson();
    }
}