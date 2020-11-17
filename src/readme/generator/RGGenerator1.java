package readme.generator;

import readme.generator.ts.TSClass;
import readme.generator.ts.TSEnum;
import readme.generator.ts.TSInterface;

import java.util.List;
import java.util.Map;

public class RGGenerator1 implements RGComponentGenerator {

    @Override
    public String makeTable(RGComponent component) {
        if (component.toMap() == null) return "";

        if (component instanceof TSInterface) {
            Map<String, List<Pair<String, String>>> map = component.toMap();
            String tableHeading = String.valueOf(map.get("Title"));
            String table = new StringBuilder()
                    .append("### ")
                    .append(tableHeading).append("\n")
                    .append("|Field|Type|Description|\n")
                    .append("|---|---|---|\n").toString();
            List<Pair<String, String>> items = map.get("Field-Type");


            for (Pair<String, String> pair : items) {
                // TODO: IMPORTANT
                // THIS TRY CATCH IS USED TO MAKE CODE RUN THIS IS NOT A GOOD WAY TO DO IT
                try {
                    table += "|" + pair.getKey().replace("|", "or") + "|`" + pair.getValue().replace("|", "or") + "`| |\n";
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }

            return table + "\n";
        } else if (component instanceof TSEnum) {
            Map<String, List<Pair<String, String>>> map = component.toMap();
            String tableHeading = String.valueOf(map.get("Title"));
            String table = new StringBuilder()
                    .append("### Enum ")
                    .append(tableHeading).append("\n")
                    .append("|Field|Value|Description|\n")
                    .append("|---|---|---|\n").toString();
            List<Pair<String, String>> items = map.get("Field-Value");
            for (Pair<String, String> pair : items)
                table += "|" + pair.getKey().replace("|", "or") + "|" + pair.getValue().replace("|", "or") + "| |\n";

            return table + "\n";
        } else if (component instanceof TSClass) {
            Map<String, List<Pair<String, String>>> map = component.toMap();
            String tableHeading = String.valueOf(map.get("Title"));
            StringBuilder table = new StringBuilder()
                    .append("### ")
                    .append(tableHeading).append("\n")
                    .append("#### Public Method Summary").append("\n")
                    .append("|Method|Return Type|Description|").append("\n")
                    .append("|---|---|---|").append("\n");
            List<Pair<String, String>> items = map.get("Field-Type");
            for (Pair<String, String> pair : items) {
                table.append("|").append(pair.getKey().replace("|", "or")).append("|`").append(pair.getValue().replace("|", "or")).
                        append("`| |\n");
            }
            table.append(createDetailedViewOfClassFunctions(((TSClass) component).getFunctions()));
            return table.toString() + "\n\n";
        }
        return "";
    }

    public String createDetailedViewOfClassFunctions(List<Map<String, String>> funcMap) {
        StringBuilder builder = new StringBuilder();
        // Check HUAWEI developer site.
        // https://developer.huawei.com/consumer/en/doc/development/HMSCore-References-V5/huaweimap-0000001050151757-V5
        builder.append("#### Public Methods\n\n");
        for (Map<String, String> map : funcMap) {
            // If you need to parse parameters, you can split them with comma.
            String fName = map.get("Name");
            String fReturnType = map.get("Return Type");
            String fParametersString = map.get("Parameters");
            String fDescription = map.get("Description");
            String fReturnDescription = map.get("Return Description");
            String fParametersDescString = map.get("Parameter Descriptions");
            builder.append("##### ").append(fName).append(fParametersString).append("\n");

            // I don't get her ebuilder.append("| Method |").append("|---|");
            if (fDescription != null)
                builder.append(fDescription).append("\n");
            if (!fParametersString.equals("()")) {
                builder.append("###### Parameters\n");
                //builder.append("|Name|Description|\n");
                builder.append("|Name|Type|Description|\n");
                //builder.append("|---|---|\n");
                builder.append("|---|---|---|\n");

//                String paramsString = fParametersString.trim().replace("(", "").replace(")", "");
                String paramsString = fParametersDescString.trim().replace("(", "").replace(")", "");

                String[] params = paramsString.split(",");
                for (String s : params) {
                    String split[] = s.split(":", 3);

                    if(split.length > 2){
                        builder.append(String.format("|%s|%s|%s|\n", split[0], split[1], split[2]));
                    } else {
                        builder.append(String.format("|%s|%s||\n", split[0], split[1]));
                    }
                }
            }
            builder.append("###### Return Type\n");
            builder.append("|Type|Description|\n");
            builder.append("|---|---|\n");
            if (fReturnDescription == null)
                fReturnDescription = "-";
            builder.append(String.format("|`%s`|%s|\n", fReturnType, fReturnDescription));

            builder.append("###### Call Example\n");

            builder.append(String.format("```ts\n%s\n```\n\n", generateExampleCode(fName, fReturnType, fParametersString)));
            // Parameter type description table
            // Return description table
        }
        return builder.toString();
    }

    @Override
    public String mergedTable(List<Map<String, String>> components, String heading, String... columns) {
        StringBuilder builder = new StringBuilder();
        builder.append("### " + heading + "\n");
        for (int i = 0; i < columns.length; ++i)
            builder.append("|" + columns[i]);
        builder.append("|\n");
        for (int i = 0; i < columns.length; ++i)
            builder.append("|---");
        builder.append("|\n");

        for (int i = 0; i < components.size(); ++i) {
            builder.append(String.format("|%s%s", components.get(i).get("Name"), components.get(i).get("Parameters")));
            builder.append(String.format("|`%s`", components.get(i).get("Return Type")));
            builder.append(String.format("|%s|\n", components.get(i).get("Description")));
        }
        return builder.toString();
    }

    public String generateExampleCode(String methodName, String returnType, String parameterStrings) {
        StringBuilder builder = new StringBuilder();
        if(returnType.contains("Promise")) {
            builder.append("async ");
        }
        builder.append(String.format("function %s() {\n", methodName));
        if (!parameterStrings.equals("()")) {
            String paramsString = parameterStrings.trim().replace("(", "").replace(")", "").replace("?", "");
            String[] params = paramsString.split(",");
            for (String s : params) {
                builder.append(String.format("\t\tconst %s = 'todo';\n", s.split(":")[0].trim()));
            }

            builder.append("\t\t");
            if(returnType.contains("Promise")) {
                builder.append("await ");
            }
            builder.append(String.format("HMS<name>.%s(", methodName));

            String prefix = "";
            for (String s : params) {
                builder.append(String.format("%s%s", prefix, s.split(":")[0].trim()));
                prefix = ", ";
            }
            builder.append(");\n");

        } else {
            builder.append("\t\t");
            if(returnType.contains("Promise")) {
                builder.append("await ");
            }
            builder.append(String.format("HMS<name>.%s();\n", methodName));
        }
        builder.append("}");
        return builder.toString();
    }
}
