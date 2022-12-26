package persistence;

import java.io.BufferedReader;
import java.io.FileNotFoundException;

public interface InterfBD {
    BufferedReader getFileReader(String filePath) throws FileNotFoundException;
}
