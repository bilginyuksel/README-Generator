package readme.generator;

import java.util.List;
import java.util.Map;

public class RGGenerator1 implements RGComponentGenerator{

    @Override
    public String makeTable(RGComponent component, String heading, String ...columns) {
        return null;
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
