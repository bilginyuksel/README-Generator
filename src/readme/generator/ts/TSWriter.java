package readme.generator.ts;


import readme.generator.RGComponentGenerator;
import readme.generator.RGFileData;
import readme.generator.RGFileWriter;
import readme.generator.RGGenerator1;

import java.io.File;
import java.io.IOException;
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
        for(int i=0;i<fileData.getComponentList().size(); ++i){
            if(!(fileData.getComponentList().get(i) instanceof TSFunction || fileData.getComponentList().get(i) instanceof TSVariable)){
                data.append(generator.makeTable(fileData.getComponentList().get(i)));
            }
        }

        data.append(table);

        File file = new File(outputFileName);
        file.createNewFile();
        Files.write(file.toPath(), data.toString().getBytes());

        return file;
    }
}
