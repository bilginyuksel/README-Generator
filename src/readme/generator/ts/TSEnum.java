package readme.generator.ts;

import readme.generator.RGComponent;

import java.util.ArrayList;
import java.util.List;

public class TSEnum extends TSBaseComponent {

    private List<EnumVar> variableList;
    private String eName;

    public TSEnum(String eName){
        this.variableList = new ArrayList<>();
        this.eName = eName;
    }

    public TSEnum(){
        this.variableList = new ArrayList<>();
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public void addVariable(EnumVar enumVar){
        this.variableList.add(enumVar);
    }

    public List<EnumVar> getVariableList() {
        return variableList;
    }

    public String geteName() {
        return eName;
    }

    @Override
    public String toString() {
        return "TSEnum{" +
                "variableList=" + variableList +
                ", eName='" + eName + '\'' +
                ", export='" + isExport() + '\'' +
                '}';
    }

    public static class EnumVar{
        private String vaName, vaValue;
        public EnumVar(){}
        public EnumVar(String vaName, String vaValue){
            this.vaName = vaName; this.vaValue = vaValue;
        }
        public void setVaName(String vaName) {
            this.vaName = vaName;
        }
        public void setVaValue(String vaValue) {
            this.vaValue = vaValue;
        }

        @Override
        public String toString() {
            return "EnumVar{" +
                    "eName='" + vaName + '\'' +
                    ", eValue='" + vaValue + '\'' +
                    '}';
        }
    }
}
