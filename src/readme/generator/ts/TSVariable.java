package readme.generator.ts;

public class TSVariable extends TSBaseComponent {

    private String name;
    private String type;
    private String defaultValue;

    public TSVariable(){}

    public TSVariable(String name, String type){
        this.name = name;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "TSVariable{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", export='"+ isExport() +'\'' +
                '}';
    }
}

