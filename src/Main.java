import readme.generator.RGComponent;
import readme.generator.RGFileData;
import readme.generator.RGFileReader;
import readme.generator.ts.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args)  {


        RGFileReader rgFileReader = new TSReader();
        RGFileData rgFileData = null;
        try{
            rgFileData = rgFileReader.read("types.ts");
        }catch (IOException e){
            e.printStackTrace();
        }

        for(RGComponent component : rgFileData.getComponentList()){
            System.out.println(component);
        }

    }
}
