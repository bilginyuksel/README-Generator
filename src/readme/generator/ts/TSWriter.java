package readme.generator.ts;


import readme.generator.RGComponentGenerator;
import readme.generator.RGFileData;
import readme.generator.RGFileWriter;
import readme.generator.RGGenerator1;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TSWriter implements RGFileWriter {

    private RGComponentGenerator generator;
    public TSWriter(){
        generator = new RGGenerator1();
    }
    @Override
    public File generateREADME(RGFileData fileData, String outputFileName) throws IOException {
        List<Map<String, String>> mergedComponents = new ArrayList<>();

        for(int i=0;i<fileData.getComponentList().size(); ++i)
            if(fileData.getComponentList().get(i) instanceof TSFunction)
                mergedComponents.add(fileData.getComponentList().get(i).toMap());

        String table = generator.mergedTable(mergedComponents, "Public Method Summary",
                new String[]{"Parameters", "Return Type", "Description"});

        StringBuilder data = new StringBuilder();
        data.append(readRawStartingInstructionsFile("startWriting.txt"));

        for(int i=0;i<fileData.getComponentList().size(); ++i){
            if(!(fileData.getComponentList().get(i) instanceof TSFunction || fileData.getComponentList().get(i) instanceof TSVariable)){
                data.append(generator.makeTable(fileData.getComponentList().get(i)));
            }
        }

        data.append(table);
        data.append(readRawEndingInstructionFile("endWriting.txt"));

        File file = new File(outputFileName);
        file.createNewFile();
        Files.write(file.toPath(), data.toString().getBytes());

        return file;
    }

    private String readRawEndingInstructionFile(String filename){
        FileReader fileReader = null;
        StringBuilder finalOutput = new StringBuilder();
        finalOutput.append("\n\n---\n\n");
        try {
            File f = new File(filename);
            fileReader = new FileReader(f);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int c = 0;
            while ((c = bufferedReader.read()) != -1) {
                finalOutput.append((char)c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return finalOutput.toString();
    }

    private String readRawStartingInstructionsFile(String filename){
        FileReader fileReader = null;
        StringBuilder finalOutput = new StringBuilder();
        try {
            File f = new File(filename);
            fileReader = new FileReader(f);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int c = 0;
            while ((c = bufferedReader.read()) != -1) {
                finalOutput.append((char)c);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return finalOutput.append("\n\n---\n\n## 3. API Reference\n").toString();
    }
}
