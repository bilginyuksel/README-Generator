package readme.generator.ts;

import readme.generator.RGComponent;

import java.util.ArrayList;
import java.util.List;

public class TSFunction extends TSBaseComponent {

    private List<TSVariable> parameters;
    private String returnType;
    private String fName;
    private String docString;

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
        this.returnType = returnType;
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

    @Override
    public String toString() {
        return "Function{" +
                "parameters=" + parameters +
                ", returnType='" + returnType + '\'' +
                ", fName='" + fName + '\'' +
                ", docString='" + docString + '\'' +
                ", export='" + isExport() + '\'' +
                '}';
    }
}
