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
            rgFileData = rgFileReader.read("C:\\Users\\k84167261\\Desktop\\HUAWEI\\GITLAB-REPOSITORIES\\HP_HMSCore-Plugin-Cordova_Ads-Library\\scripts\\interfaces.ts");
            rgFileData.append(rgFileReader.read("C:\\Users\\k84167261\\Desktop\\HUAWEI\\GITLAB-REPOSITORIES\\HP_HMSCore-Plugin-Cordova_Ads-Library\\scripts\\banner.ts"));
            rgFileData.append(rgFileReader.read("C:\\Users\\k84167261\\Desktop\\HUAWEI\\GITLAB-REPOSITORIES\\HP_HMSCore-Plugin-Cordova_Ads-Library\\scripts\\splash.ts"));
            rgFileData.append(rgFileReader.read("C:\\Users\\k84167261\\Desktop\\HUAWEI\\GITLAB-REPOSITORIES\\HP_HMSCore-Plugin-Cordova_Ads-Library\\scripts\\interstitial.ts"));
            rgFileData.append(rgFileReader.read("C:\\Users\\k84167261\\Desktop\\HUAWEI\\GITLAB-REPOSITORIES\\HP_HMSCore-Plugin-Cordova_Ads-Library\\scripts\\reward.ts"));
            RGFileData n = rgFileReader.read("C:\\Users\\k84167261\\Desktop\\HUAWEI\\GITLAB-REPOSITORIES\\HP_HMSCore-Plugin-Cordova_Ads-Library\\scripts\\native.ts");
            //rgFileData = rgFileReader.read("types.ts");
            System.out.println("\n");
            System.out.println(n.getComponentList().toString());
            rgFileData.append(n);
            rgFileData.append(rgFileReader.read("C:\\Users\\k84167261\\Desktop\\HUAWEI\\GITLAB-REPOSITORIES\\HP_HMSCore-Plugin-Cordova_Ads-Library\\scripts\\utils.ts"));
            writer.generateREADME(rgFileData);
        }catch (IOException e){
            e.printStackTrace();
        }

        for(RGComponent component : rgFileData.getComponentList()){
            System.out.println(component);
        }



    }
}
