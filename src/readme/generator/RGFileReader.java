package readme.generator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class RGFileReader {
    public abstract RGFileData read(String filename) throws IOException;


    private void findFilesUtil(File f, List<String> fileList){
        if(f.isDirectory()){
            for(File nf : f.listFiles()){
                if(nf.isDirectory()) findFilesUtil(nf, fileList);
                else fileList.add(nf.getAbsolutePath());
            }
        }else fileList.add(f.getAbsolutePath());
    }

    // Find all files inside subdirectories with the extension given.
    // And store all of the information inside one FileData
    public RGFileData readAll(String folderName, String extension) throws IOException{
        RGFileData mergedRGFileData = new RGFileData();
        // Find all files under the folder specified.
        // Also include every subfolders too...
        // ---- *.extension
        // mergedRGFileData.append();
        File f = new File(folderName);
        if(!f.isDirectory()) throw new IOException("No directory detected!");

        List<String> fileList = new ArrayList<>();
        findFilesUtil(f, fileList);

        for(String file : fileList){
            mergedRGFileData.append(read(file));
        }

        return mergedRGFileData;
    }

}
