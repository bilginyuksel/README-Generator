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
            rgFileData = rgFileReader.readAll("C:\\Users\\k84167261\\Desktop\\HUAWEI\\GITLAB-REPOSITORIES\\HP_HMSCore-Plugin-Cordova_Ads-Library\\scripts", "ts");
            writer.generateREADME(rgFileData, "TEST.md");
        }catch (IOException e){
            e.printStackTrace();
        }

        for(RGComponent component : rgFileData.getComponentList()){
            System.out.println(component);
        }


    }
}
