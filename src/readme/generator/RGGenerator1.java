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
            for(Pair<String, String> pair : items)
                table += "|"+ pair.getKey().replace("|", "or") + "|`" + pair.getValue().replace("|", "or") + "`| |\n";

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
            return table.toString() + "\n";
        }
        return "";
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
