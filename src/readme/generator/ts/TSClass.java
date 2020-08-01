package readme.generator.ts;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSClass extends TSBaseComponent{
    private String className;
    private String suffixKeyword;
    private String suffixClass;
    private List<TSVariable> variables;
    private List<TSFunction> functions;

    public TSClass(){
        this.variables = new ArrayList<>();
        this.functions = new ArrayList<>();
    }

    public void addFunction(TSFunction function){
        this.functions.add(function);
    }

    public void addVariable(TSVariable variable){
        this.variables.add(variable);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setSuffixClass(String suffixClass) {
        this.suffixClass = suffixClass;
    }

    public void setSuffixKeyword(String suffixKeyword) {
        this.suffixKeyword = suffixKeyword;
    }

    @Override
    public String toString() {
        return "TSClass{" +
                "className='" + className + '\'' +
                ", suffixKeyword='" + suffixKeyword + '\'' +
                ", suffixClass='" + suffixClass + '\'' +
                ", variables=" + variables +
                ", functions=" + functions +
                ", export=" + isExport() +
                '}';
    }

    @Override
    public Map toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("Title", this.className);
        List<Pair<String, String>> fieldType = new ArrayList<>();
        for(TSFunction tsFunction : functions){
            StringBuilder builder = new StringBuilder();
            builder.append(tsFunction.getfName()).append("(");
            for(int i=0;i<tsFunction.getParameters().size(); ++i){
                builder.append(tsFunction.getParameters().get(i).getName()).append(": ").
                        append(tsFunction.getParameters().get(i).getType());
                if(i+1 != tsFunction.getParameters().size()) builder.append(", ");
            }builder.append(")");
            fieldType.add(new Pair<>(builder.toString(), tsFunction.getReturnType()));
        }
        map.put("Field-Type", fieldType);
        return map;
    }
}
