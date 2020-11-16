import readme.generator.RGComponent;
import readme.generator.RGFileData;
import readme.generator.RGFileReader;
import readme.generator.RGFileWriter;
import readme.generator.ts.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args)  {


        RGFileReader rgFileReader = new TSReader();
        RGFileWriter writer = new TSWriter();

        RGFileData rgFileData = null;
        // RGFileData data = null;
        try{
//            rgFileData = rgFileReader.readAll("scripts", "ts");
             rgFileData = rgFileReader.read("test.ts");
            writer.generateREADME(rgFileData, "NEARBY.md");
            // writer.generateREADME(data, "TEST2.md");
        }catch (IOException e){
            e.printStackTrace();
        }

        for(RGComponent component : rgFileData.getComponentList()){
            System.out.println(component);
        }


    }
}
