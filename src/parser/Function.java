package parser;

import java.util.ArrayList;
import java.util.List;

public class Function {

    private List<Variable> parameters;
    private String returnType;
    private String fName;
    private String docString;

    public Function(String fName, String docString){
        this.fName = fName;
        this.docString = docString;
        parameters = new ArrayList<>();
    }

    public Function(String fName){
        this.fName = fName;
        parameters = new ArrayList<>();
    }

    public void setDocString(String docString) {
        this.docString = docString;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setParameter(Variable parameter) {
        this.parameters.add(parameter);
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<Variable> getParameters() {
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
                '}';
    }
}
