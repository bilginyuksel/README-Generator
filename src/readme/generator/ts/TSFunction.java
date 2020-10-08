package readme.generator.ts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSFunction extends TSBaseComponent {

    private List<TSVariable> parameters;
    private String returnType;
    private String fName;
    private String docString;
    private String accessSpecifier;

    public TSFunction(String fName, String docString){
        this.fName = fName;
        this.docString = docString;
        parameters = new ArrayList<>();
    }

    public TSFunction(){
        parameters = new ArrayList<>();
    }
    public TSFunction(String fName){
        this.fName = fName;
        parameters = new ArrayList<>();
    }

    public void setDocString(String docString) {
        this.docString = docString;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setParameter(TSVariable parameter) {
        this.parameters.add(parameter);
    }

    public void setReturnType(String returnType) {
        if(returnType.length() < 1) this.returnType = "any";
        else this.returnType = returnType;
    }

    public List<TSVariable> getParameters() {
        return parameters;
    }

    public String getDocString() {
        return docString;
    }

    public String getfName() {
        return fName;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setAccessSpecifier(String accessSpecifier) {
        this.accessSpecifier = accessSpecifier;
    }

    public String getAccessSpecifier() {
        return accessSpecifier;
    }

    @Override
    public String toString() {
        return "TSFunction{" +
                "parameters=" + parameters +
                ", returnType='" + returnType + '\'' +
                ", fName='" + fName + '\'' +
                ", docString='" + docString + '\'' +
                ", accessSpecifier='" + accessSpecifier + '\'' +
                ", export=" + isExport() +
                '}';
    }

    @Override
    public Map toMap() {
        Map<String, String> map = new HashMap<>();
        StringBuilder parameterBuilder = new StringBuilder();
        parameterBuilder.append("(");
        for(int i=0; i<parameters.size(); ++i){
            parameterBuilder.append(parameters.get(i).getName()).append(":").
                    append(parameters.get(i).getType());
            if(i+1 != parameters.size()) parameterBuilder.append(", ");
        }parameterBuilder.append(")");
        map.put("Name", fName);
        map.put("Parameters", parameterBuilder.toString());
        map.put("Return Type", this.getReturnType());
        map.put("Description","");
        return map;
    }
}
