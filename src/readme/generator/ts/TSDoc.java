package readme.generator.ts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSDoc extends TSBaseComponent {
    private List<TSVariable> parameters;
    private String description;
    private String returnType;
    private String returnDescription;

    public TSDoc() {
    }

    public List<TSVariable> getParameters() {
        return parameters;
    }

    public void setParameters(List<TSVariable> parameters) {
        this.parameters = parameters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getReturnDescription() {
        return returnDescription;
    }

    public void setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
    }

    @Override
    public String toString() {
        return "TSDoc{" +
                "parameters=" + parameters +
                ", description='" + description + '\'' +
                ", returnType='" + returnType + '\'' +
                ", returnDescription='" + returnDescription + '\'' +
                '}';
    }

    @Override
    public Map toMap() {
        Map<String, String> map = new HashMap<>();
        StringBuilder parameterBuilder = new StringBuilder();
        parameterBuilder.append("(");
        for(int i=0; i<parameters.size(); ++i){
            parameterBuilder.append(parameters.get(i).getName())
                    .append(":").append(parameters.get(i).getType())
                    .append(":").append(parameters.get(i).getDescription());
            if(i+1 != parameters.size()) parameterBuilder.append(", ");
        }parameterBuilder.append(")");
        map.put("Parameters", parameterBuilder.toString());
        map.put("Return Type", this.getReturnType());
        map.put("Return Description", this.getReturnDescription());
        map.put("Description", this.getDescription());
        return map;
    }
}
