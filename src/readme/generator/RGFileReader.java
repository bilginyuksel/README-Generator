package readme.generator;

import java.io.IOException;

public interface RGFileReader {
    RGFileData read(String filename) throws IOException;
    RGFileData readAll(String folderName, String extension) throws IOException;
    // Find all files inside subdirectories with the extension given.
    // And store all of the information inside one FileData
}
