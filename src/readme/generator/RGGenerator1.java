package readme.generator;

import javafx.util.Pair;
import readme.generator.ts.TSClass;
import readme.generator.ts.TSEnum;
import readme.generator.ts.TSInterface;

import java.util.List;
import java.util.Map;

public class RGGenerator1 implements RGComponentGenerator{

    @Override
    public String makeTable(RGComponent component) {
        if(component.toMap() == null) return "";

        if(component instanceof TSInterface){
            Map<String, List<Pair<String, String>>> map = component.toMap();
            String tableHeading = String.valueOf(map.get("Title"));
            String table = new StringBuilder()
                    .append("### ")
                    .append(tableHeading).append("\n")
                    .append("|Field|Type|Description|\n")
                    .append("|---|---|---|\n").toString();
            List<Pair<String, String>> items = map.get("Field-Type");


            for(Pair<String, String> pair : items) {
                // TODO: IMPORTANT
                // THIS TRY CATCH IS USED TO MAKE CODE RUN THIS IS NOT A GOOD WAY TO DO IT
                try {
                    table += "|" + pair.getKey().replace("|", "or") + "|`" + pair.getValue().replace("|", "or") + "`| |\n";
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }

            return table + "\n";
        }else if(component instanceof TSEnum){
            Map<String, List<Pair<String, String>>> map = component.toMap();
            String tableHeading = String.valueOf(map.get("Title"));
            String table = new StringBuilder()
                    .append("### Enum ")
                    .append(tableHeading).append("\n")
                    .append("|Field|Value|Description|\n")
                    .append("|---|---|---|\n").toString();
            List<Pair<String, String>> items = map.get("Field-Value");
            for(Pair<String, String> pair : items)
                table += "|"+ pair.getKey().replace("|", "or") + "|" + pair.getValue().replace("|", "or") + "| |\n";

            return table + "\n";
        }else if(component instanceof TSClass){
            Map<String, List<Pair<String, String>>> map = component.toMap();
            String tableHeading = String.valueOf(map.get("Title"));
            StringBuilder table = new StringBuilder()
                    .append("### ")
                    .append(tableHeading).append("\n")
                    .append("#### Public Method Summary").append("\n")
                    .append("|Field|Return Type|Description|").append("\n")
                    .append("|---|---|---|").append("\n");
            List<Pair<String, String>> items = map.get("Field-Type");
            for(Pair<String, String> pair : items) {
                table.append("|").append(pair.getKey().replace("|", "or")).append("|`").append(pair.getValue().replace("|", "or")).
                        append("`| |\n");
            }
            table.append(createDetailedViewOfClassFunctions(((TSClass) component).getFunctions()));
            return table.toString() + "\n";
        }
        return "";
    }

    private String createDetailedViewOfClassFunctions(List<Map<String, String>> funcMap){
        StringBuilder builder = new StringBuilder();
        // Check HUAWEI developer site.
        // https://developer.huawei.com/consumer/en/doc/development/HMSCore-References-V5/huaweimap-0000001050151757-V5
        builder.append("#### Public Methods\n\n");
        for(Map<String, String> map : funcMap){
            // If you need to parse parameters, you can split them with comma.
            String fName = map.get("Name");
            String fReturnType = map.get("Return Type");
            String fParametersString = map.get("Parameters");
            builder.append("###### ")
                    .append(fName)
                    .append("\n\n");

            // I don't get her ebuilder.append("| Method |").append("|---|");
            builder.append("Function explanation field...\n\n");
            if(fParametersString != ""){
                builder.append("###### Parameters\n\n");
            }
            if(fReturnType != "void"){
                builder.append("do something\n\n");
            }
            // Parameter type description table
            // Return description table
        }
        return builder.toString();
    }

    @Override
    public String mergedTable(List<Map<String, String>> components, String heading, String ...columns) {
        String table = "### "+heading+"\n";
        for(int i=0;i<columns.length; ++i)
            table += "|"+columns[i];
        table += "|\n";
        for(int i=0;i<columns.length;++i)
            table += "|---";
        table+="|\n";

        for(int i=0;i<components.size();++i){
            for(String key: columns){
                if(key.equals("Return Type")) table+="|`"+components.get(i).get(key)+"`";
                else table += "|" + components.get(i).get(key);
            }table+="|\n";
        }

        return table;
    }
}
