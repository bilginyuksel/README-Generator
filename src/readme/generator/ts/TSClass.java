package readme.generator.ts;

import java.util.ArrayList;
import java.util.List;

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
}
