package readme.generator.ts;

import readme.generator.RGComponent;

public class TSVariable implements RGComponent {

    private String name;
    private String type;

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

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

