package readme.generator.ts;

import readme.generator.RGComponent;

import java.util.ArrayList;
import java.util.List;

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
