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
        try{
            rgFileData = rgFileReader.readAll("scripts", "ts");
            //don't change the outputFileName and don't push .md files.
            writer.generateREADME(rgFileData, "README.md");
        }catch (IOException e){
            e.printStackTrace();
        }

        for(RGComponent component : rgFileData.getComponentList()){
            System.out.println(component);
        }


    }
}
