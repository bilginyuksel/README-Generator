package readme.generator.ts;

import readme.generator.RGASCII;
import readme.generator.RGComponent;
import readme.generator.RGFileData;
import readme.generator.RGFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TSReader implements RGFileReader {


    private RGComponent readTsFunctionTemplate(BufferedReader reader, Boolean export) throws IOException{
        TSFunction tsFunction = new TSFunction();
        tsFunction.setExport(export);

        String functionName = "";
        int c = 0;
        while ((c = reader.read()) != RGASCII.OPENING_PARANTHESIS.getAscii())
            if(!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                functionName += (char) c;
        tsFunction.setfName(functionName);

        String parameterLine = "";
        while ((c = reader.read()) != RGASCII.CLOSING_PARANTHESIS.getAscii())  // Find the closing paranthesis
            if (!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                parameterLine += (char) c;
        String[] parameters = parameterLine.split(",");
        if(parameterLine.length() < 1) parameters = new String[0];
        for (String param : parameters) {
            TSVariable var = new TSVariable();
            String[] varBuilder = param.split(":");
            if(param.contains(":")){
                var.setName(varBuilder[0]);
                var.setType(varBuilder[1]);
            } else{
                var.setName(param);
                var.setType("any");
            }

            tsFunction.setParameter(var);
        }

        String returnType = "";
        while ((c = reader.read()) != RGASCII.CURLY_OPENING_BRACKET.getAscii()) // Find the starting curly bracket
            if (!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii() || c == RGASCII.COLON.getAscii()))
                returnType += (char) c;

        tsFunction.setReturnType(returnType);

        while (reader.read() != RGASCII.CURLY_CLOSING_BRACKET.getAscii());// Finish function body according to end curly bracket

        return tsFunction;
    }

    private RGComponent readTsEnumTemplate(BufferedReader reader, Boolean export) throws IOException{
        TSEnum tsEnum = new TSEnum();
        tsEnum.setExport(export);
        String eName = "";
        int c = 0;
        while((c = reader.read()) != RGASCII.CURLY_OPENING_BRACKET.getAscii())
            if(!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                eName += (char) c;

        tsEnum.seteName(eName);

        String eVariables = "";
        while((c = reader.read()) != RGASCII.CURLY_CLOSING_BRACKET.getAscii())
            if(!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                eVariables+=(char) c;

        String[]eVarList = eVariables.split(",");
        if(eVariables.length() < 1) eVarList = new String[0];
        for(String var : eVarList){
            String[] nava = var.split("=");
            TSEnum.EnumVar enumVar = new TSEnum.EnumVar(nava[0], nava[1]);
            tsEnum.addVariable(enumVar);
        }

        return tsEnum;
    }

    private RGComponent readTsVariableTemplate(BufferedReader reader, Boolean export) throws IOException{
        TSVariable tsVariable = new TSVariable();
        tsVariable.setExport(export);

        String var = "";
        int c = reader.read();
        while(c != RGASCII.NEW_LINE.getAscii() && c != RGASCII.LINE_FEED.getAscii() && c != RGASCII.SEMI_COLON.getAscii()){
            if(c != RGASCII.SPACE.getAscii()) var+=(char) c;
            c = reader.read();
        }
        if(var.contains("=")) {
            System.out.println(var);
            String splitted[] = var.split("=");
            tsVariable.setDefaultValue(splitted[1]);
            if(splitted[0].contains(":")) {
                tsVariable.setName(splitted[0].split(":")[0]);
                tsVariable.setType(splitted[0].split(":")[1]);
            }else{
                tsVariable.setName(splitted[0]);
                tsVariable.setType("any");
            }
        } else{
            tsVariable.setDefaultValue(null);
            tsVariable.setName(var.split(":")[0]);
            tsVariable.setType(var.split(":")[1]);
        }

        return tsVariable;
    }

    private RGComponent readTsInterfaceTemplate(BufferedReader reader, Boolean export) throws IOException{
        TSInterface tsInterface = new TSInterface();
        tsInterface.setExport(export);

        String interfaceName = "";
        int c = 0;
        while((c = reader.read()) != RGASCII.CURLY_OPENING_BRACKET.getAscii())
            if(!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                interfaceName += (char) c;
        tsInterface.setInterfaceName(interfaceName);

        String interfaceVar = "";
        while((c = reader.read()) != RGASCII.CURLY_CLOSING_BRACKET.getAscii()){
            if(!( c == RGASCII.SPACE.getAscii() || c == RGASCII.COMMA.getAscii() ||
                    c == RGASCII.HORIZONTAL_TAB.getAscii())){
                interfaceVar += (char) c;
                //System.out.print(c+" ");
            }
        }//System.out.println();

        String [] elements = interfaceVar.split("\r\n"); // SEMI_COLON
        for(String elem : elements){
            if(elem.length()==0) continue;
            TSInterface.InterfaceElement element = new TSInterface.InterfaceElement();
            elem = elem.replace("|", " or ");
            if(elem.contains("=")) {
                String splitted[] = elem.split("=");
                element.setElementDefaultValue(splitted[1]);
                element.setElementName(splitted[0].split(":")[0]);
                element.setElementType(splitted[0].split(":")[1]);
            } else{
                element.setElementDefaultValue(null);
                element.setElementName(elem.split(":")[0]);
                element.setElementType(elem.split(":")[1]);
            }

            tsInterface.addElement(element);
        }

        return tsInterface;
    }

    private RGComponent readTsClassTemplate(BufferedReader reader, Boolean export) throws IOException{
        TSClass tsClass = new TSClass();
        tsClass.setExport(export);

        String className = "";
        List<String> classProps = new ArrayList<>(); // 1. Classname, 2. Keyword(implements, extends), 3. Other class name
        int c = 0;
        while(c != RGASCII.CURLY_OPENING_BRACKET.getAscii()){
            c = reader.read();
            if((c == RGASCII.SPACE.getAscii() || c == RGASCII.CURLY_OPENING_BRACKET.getAscii()) && className.length() > 1) {
                classProps.add(className);
                className = "";
            }
            else className += (char) c;
        }

        if(classProps.size() == 1) tsClass.setClassName(classProps.get(0));
        else{
            tsClass.setClassName(classProps.get(0));
            tsClass.setSuffixKeyword(classProps.get(1));
            tsClass.setSuffixClass(classProps.get(2));
        }

        // Find functions and variables inside class, and also their access specifiers too.
        // I need to find what are variables and functions.
        String name = "";
        String accessSpecifier = null;
        while((c = reader.read()) != RGASCII.CURLY_CLOSING_BRACKET.getAscii()){

            if(c == RGASCII.SPACE.getAscii() && name.length() > 0){
                // this was probably an access specifier.
                accessSpecifier = name;
                name = "";
                continue;
            }

            if(c == RGASCII.OPENING_PARANTHESIS.getAscii()){
                // That means this is a function.
                TSFunction tsFunction = new TSFunction();
                tsFunction.setfName(name);
                tsFunction.setAccessSpecifier(accessSpecifier==null?"default":accessSpecifier);
                // tsFunction.setExport(according to access specifiers inherit)
                String parameters = "";
                boolean isFunctionType = false;
                c = reader.read();
                while(c != RGASCII.CLOSING_PARANTHESIS.getAscii()){
                    if(c == RGASCII.OPENING_PARANTHESIS.getAscii()) isFunctionType = true;

                    if(!(c == RGASCII.SPACE.getAscii() && c == RGASCII.NEW_LINE.getAscii() && c == RGASCII.LINE_FEED.getAscii()))
                        parameters += (char) c;
                    c = reader.read();
                    if(isFunctionType){
                        parameters += (char) c;
                        c = reader.read();
                        isFunctionType = false;
                    }
                }
                String[] paramList = parameters.split(",");
                if(parameters.length() < 1) paramList = new String[0];
                for(String param : paramList){
                    TSVariable var = new TSVariable();
                    String[] varBuilder = param.split(":");
                    var.setName(varBuilder[0]);
                    var.setType(varBuilder[1]);
                    tsFunction.setParameter(var);
                }

                String returnType = "";
                while((c = reader.read()) != RGASCII.CURLY_OPENING_BRACKET.getAscii()) { // find the return type
                    if(!(c == RGASCII.SPACE.getAscii() || c == RGASCII.NEW_LINE.getAscii() ||
                            c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.COLON.getAscii()))
                        returnType += (char) c;
                }
                tsFunction.setReturnType(returnType);
                boolean isBracketOpened = false;

                /*
                * To skip function body we have to skip all characters until closing bracket of function.
                * The important thing here is while we are looking for closing brackets maybe in function body
                * there could be a new bracket opened and that means, we can find the new closing bracket but
                * we dont want it. To avoid this issue we are tracking if any brackets opened or not.
                *  */
                while(c != RGASCII.CURLY_CLOSING_BRACKET.getAscii()) { // skip function body
                    c = reader.read();
                    if(c == RGASCII.CURLY_OPENING_BRACKET.getAscii()) isBracketOpened = true;
                    if(c == RGASCII.CURLY_CLOSING_BRACKET.getAscii()){
                        if(isBracketOpened) {
                            c = reader.read();
                            isBracketOpened = false;
                        }
                    }
                }
                tsClass.addFunction(tsFunction);
            }else if(c == RGASCII.COLON.getAscii()){
                // That means this is a variable
                // Go until you find semicolon or new line line feed
                TSVariable tsVariable = new TSVariable();
                tsVariable.setName(name);
                String var = "";
                c = reader.read();
                while(c != RGASCII.SEMI_COLON.getAscii() && c != RGASCII.LINE_FEED.getAscii()){
                    if(!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                        var += (char) c;
                    c = reader.read();
                }
                tsVariable.setAccessSpecifier(accessSpecifier==null?"default":accessSpecifier);
                System.out.println("Var: "+ var);
                if(var.contains("=")) {
                    String splitted[] = var.split("=");
                    tsVariable.setDefaultValue(splitted[1]);
                    tsVariable.setType(splitted[0]);
                } else{
                    tsVariable.setDefaultValue(null);
                    tsVariable.setType(var);
                }
                tsClass.addVariable(tsVariable);
            }else if(!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii())){
                name += (char) c;
                continue;
            }
            name = "";
            accessSpecifier = null;
        }

        return tsClass;
    }

    @Override
    public RGFileData read(String filename) throws IOException {
        RGFileData rgFileData = new RGFileData();
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int c = 0;
        String keyw = "";
        Boolean export = false;
        while((c = bufferedReader.read()) != -1){
            // Until end of the file

            if(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()){
                if (keyw.length() < 1) continue;

                if(keyw.equals("export")) {
                    export = true;
                    keyw = "";
                    continue;
                }

                if(keyw.equals(TSKeywords.FUNCTION.getKeyword()))
                    rgFileData.addComponent(readTsFunctionTemplate(bufferedReader, export));
                else if(keyw.equals(TSKeywords.ENUM.getKeyword()))
                    rgFileData.addComponent(readTsEnumTemplate(bufferedReader, export));
                else if(keyw.equals(TSKeywords.INTERFACE.getKeyword()))
                    rgFileData.addComponent(readTsInterfaceTemplate(bufferedReader, export));
                else if(keyw.equals(TSKeywords.CLASS.getKeyword()))
                    rgFileData.addComponent(readTsClassTemplate(bufferedReader, export));
                else if(TSKeywords.getVariableTypes().contains(keyw))
                    rgFileData.addComponent(readTsVariableTemplate(bufferedReader, export));
                // Try to simplify abstract class situation

                keyw = "";
                export = false;
            }else keyw += (char) c;

        }

        return rgFileData;
    }

    @Override
    public RGFileData readAll(String folderName, String extension) throws IOException{

        RGFileData mergedRGFileData = new RGFileData();
        // Find all files under the folder specified.
        // Also include every subfolders too...
        // ---- *.extension
        // mergedRGFileData.append();
        return mergedRGFileData;
    }
}
