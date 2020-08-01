package readme.generator.ts;


import readme.generator.RGComponentGenerator;
import readme.generator.RGFileData;
import readme.generator.RGFileWriter;
import readme.generator.RGGenerator1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSWriter implements RGFileWriter {

    private RGComponentGenerator generator;
    public TSWriter(){
        generator = new RGGenerator1();
    }
    @Override
    public File generateREADME(RGFileData fileData) throws IOException {
        List<Map<String, String>> mergedComponents = new ArrayList<>();

        for(int i=0;i<fileData.getComponentList().size(); ++i){
            if(fileData.getComponentList().get(i) instanceof TSFunction){
                Map<String, String> map = new HashMap<>();
                String parameters = ((TSFunction) fileData.getComponentList().get(i)).getfName()+"(";
                for(TSVariable variable : ((TSFunction) fileData.getComponentList().get(i)).getParameters()){
                    parameters += variable.getName()+":"+variable.getType()+", ";
                }parameters += ")";
                map.put("Parameters", parameters);
                map.put("Return Type", ((TSFunction) fileData.getComponentList().get(i)).getReturnType());
                map.put("Description","");

                mergedComponents.add(map);
            }
        }
        String table = generator.mergedTable(mergedComponents, "Public Function Summary",
                new String[]{"Parameters", "Return Type", "Description"});

        File file = new File("TEST.md");
        file.createNewFile();
        Files.write(file.toPath(), table.getBytes());

        System.out.println(table);
        return file;
    }
}
