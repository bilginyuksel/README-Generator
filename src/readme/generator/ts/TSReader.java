package readme.generator.ts;

import readme.generator.RGASCII;
import readme.generator.RGComponent;
import readme.generator.RGFileData;
import readme.generator.RGFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TSReader extends RGFileReader {


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
        System.out.println(interfaceVar);
        for(String elem : elements){
            if(elem.length()==0) continue;
            TSInterface.InterfaceElement element = new TSInterface.InterfaceElement();
            elem = elem.replace("|", " or ");
            if(elem.contains("=")) {
                String splitted[] = elem.split("=");
                element.setElementDefaultValue(splitted[1]);
                element.setElementName(splitted[0].split(":", 1)[0]);
                element.setElementType(splitted[0].split(":", 1)[1]);
            } else{
                element.setElementDefaultValue(null);
                /*
                * Here I find the last colon to detect the actual name and type.
                * Because you can declare variables and functions in interface. If you declare
                * functions you can't right it's body.
                * I didn't want to detect functions and variables here like the way I did with the class type.
                * So functions can have parameters according to that information I tracked the last colon and I splitted
                * from the last colon index I found.
                * Basically I didn't store functions and variables inside interface.
                *  */
                int lastColonIndex = 0;
                for(int i=0;i<elem.length();++i) if((int)elem.charAt(i) == RGASCII.COLON.getAscii()) lastColonIndex = i;
                String name = ""; for(int i=0;i<lastColonIndex; ++i) name+=elem.charAt(i);
                String type = ""; for(int i=lastColonIndex+1; i<elem.length();++i) type+=elem.charAt(i);
                element.setElementName(name);
                type = type.replace(";", "");
                element.setElementType(type);
            }

            tsInterface.addElement(element);
        }

        return tsInterface;
    }

    private RGComponent readSingleLineComment(BufferedReader reader) throws IOException{
        // System.out.println("Single line comment reading...");
        /*
        * Store comment information if you want it. Store it to match next function or variable anything.*/
        int c = 0;
        while((c = reader.read()) != RGASCII.NEW_LINE.getAscii());
        return null;
    }

    private RGComponent readMultipleLineComment(BufferedReader reader) throws IOException{
        // System.out.println("Multiple line comment reading..");
        /*
        * Store docstring information to find the descriptions of methods, variables, classes etc.*/
        int c = 0;
        int first = reader.read();
        int second = reader.read();
        String data="";
        while((c =  reader.read()) != -1){
            data += (char) c;
            //System.out.println("f: "+first + ", s:" + second);
            if(first==42 && second==47) break;
            first = second;
            second = c;
        }
        // System.out.println(data);
        return null;
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
        int first = reader.read();
        int second = reader.read();
        name+=(char) first + (char) second;
        while((c = reader.read()) != RGASCII.CURLY_CLOSING_BRACKET.getAscii()){

            if(first==RGASCII.SLASH.getAscii() && second==RGASCII.ASTERIX.getAscii())
                readMultipleLineComment(reader);
            else if(first==RGASCII.SLASH.getAscii() && second==RGASCII.SLASH.getAscii())
                readSingleLineComment(reader);

            first = second;
            second = c;

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
                    if(param.contains(":")) {
                        String[] varBuilder = param.split(":");
                        var.setName(varBuilder[0]);
                        var.setType(varBuilder[1]);
                    }else{
                        var.setName(param);
                        var.setType("any");
                    }
                    tsFunction.setParameter(var);
                }

                String returnType = "";
                while((c = reader.read()) != RGASCII.CURLY_OPENING_BRACKET.getAscii()) { // find the return type
                    if(!(c == RGASCII.SPACE.getAscii() || c == RGASCII.NEW_LINE.getAscii() ||
                            c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.COLON.getAscii()))
                        returnType += (char) c;
                }
                tsFunction.setReturnType(returnType);
                int openedBracket = 0;

                /*
                * To skip function body we have to skip all characters until closing bracket of function.
                * The important thing here is while we are looking for closing brackets maybe in function body
                * there could be a new bracket opened and that means, we can find the new closing bracket but
                * we dont want it. To avoid this issue we are tracking if any brackets opened or not.
                *  */
                while(c != RGASCII.CURLY_CLOSING_BRACKET.getAscii()) { // skip function body
                    c = reader.read();
                    if(c == RGASCII.CURLY_OPENING_BRACKET.getAscii()) openedBracket++;
                    if(c == RGASCII.CURLY_CLOSING_BRACKET.getAscii()){
                        if(openedBracket>0) {
                            c = reader.read();
                            openedBracket--;
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
        int first = bufferedReader.read();
        int second = bufferedReader.read();
        keyw += (char) first + (char) second;
        while((c = bufferedReader.read()) != -1){
            // Until end of the file
            if(first==RGASCII.SLASH.getAscii() && second==RGASCII.ASTERIX.getAscii())
                readMultipleLineComment(bufferedReader);
            else if(first==RGASCII.SLASH.getAscii() && second==RGASCII.SLASH.getAscii())
                readSingleLineComment(bufferedReader);

            first = second;
            second = c;

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


}
