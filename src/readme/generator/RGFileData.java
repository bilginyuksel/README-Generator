package readme.generator;

import java.util.ArrayList;
import java.util.List;

public class RGFileData {

    private List<RGComponent> componentList;
    private String programmingLanguage;
    // More configurations maybe
    // Or just create a configuration class...

    public RGFileData(){
        this.componentList = new ArrayList<>();
    }

    public void addComponent(RGComponent component){
        this.componentList.add(component);
    }

    public List<RGComponent> getComponentList() {
        return componentList;
    }
}
