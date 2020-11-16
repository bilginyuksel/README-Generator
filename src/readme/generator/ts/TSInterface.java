package readme.generator.ts;

import readme.generator.Pair;
import readme.generator.RGComponent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TSInterface extends TSBaseComponent {
    private String interfaceName;
    private List<InterfaceElement> elementList;

    public TSInterface(){
        this.elementList = new ArrayList<>();
    }

    public void addElement(InterfaceElement interfaceElement){
        this.elementList.add(interfaceElement);
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    @Override
    public String toString() {
        return "TSInterface{" +
                "interfaceName='" + interfaceName + '\'' +
                ", elementList=" + elementList +
                ", export='" + isExport() + '\'' +
                '}';
    }

    @Override
    public Map toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("Title", this.interfaceName);
        List<Pair<String, String>> fieldType = new ArrayList<>();
        for(InterfaceElement e : elementList){
            fieldType.add(new Pair<>(e.elementName, e.elementType));
        }
        map.put("Field-Type", fieldType);
        return map;
    }

    public static class InterfaceElement{
        private String elementName;
        private String elementType;
        private String elementDefaultValue;

        public InterfaceElement(){
        }

        public void setElementDefaultValue(String elementDefaultValue) {
            this.elementDefaultValue = elementDefaultValue;
        }

        public void setElementName(String elementName) {
            this.elementName = elementName;
        }

        public void setElementType(String elementType) {
            this.elementType = elementType;
        }

        public String getElementDefaultValue() {
            return elementDefaultValue;
        }

        public String getElementName() {
            return elementName;
        }

        public String getElementType() {
            return elementType;
        }

        @Override
        public String toString() {
            return "InterfaceElement{" +
                    "elementName='" + elementName + '\'' +
                    ", elementType='" + elementType + '\'' +
                    ", elementDefaultValue='" + elementDefaultValue + '\'' +
                    '}';
        }
    }
}
