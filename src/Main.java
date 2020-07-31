import parser.Enum;
import parser.Function;
import parser.Variable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {

        File f = new File("types.ts");
        FileReader fileReader = new FileReader(f);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        Set<String> keywords = new HashSet<>();
        keywords.add("function");
        keywords.add("class");
        keywords.add("enum");
        keywords.add("interface");
        keywords.add("var");
        keywords.add("let");
        keywords.add("const");
        keywords.add("Promise");


        int c = 0;
        // Keep going until the end of the file
        List<String> types = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Function> functionList = new ArrayList<>();
        List<Enum> enumList = new ArrayList<>();
        String tmp = "";
        boolean isFunctionStarted = false;
        while ((c = bufferedReader.read()) != -1) {
            //System.out.print(c + " ");
            char character = (char) c;

            if (c == 44) continue;
            if (c == 10 || c == 13 || c == 32) {
                if (tmp.length() < 1) continue;
                if (keywords.contains(tmp)) {
                    types.add(tmp);
                    if (tmp.equals("function")) {
                        isFunctionStarted = true;
                        // Go fill function parameters.
                        // First of all find the function name
                        String fName = "";
                        // System.out.println("Starts....");
                        while ((c = bufferedReader.read()) != 40) { // Find the starting paranthesis
                            fName += (char) c;
                            // System.out.println("Inside fName....");
                        }
                        Function tmpFunction = new Function(fName);
                        String parameterLine = "";
                        while ((c = bufferedReader.read()) != 41) { // Find the closing paranthesis
                            // System.out.println("Inside parameter name.....");
                            if (c == 10 || c == 13 || c == 32) continue;
                            parameterLine += (char) c;
                        }
                        String[] parameters = parameterLine.split(",");
                        if(parameterLine.length() == 0) parameters = new String[0];
                        for (String param : parameters) {
                            Variable var = new Variable();
                            String[] varBuilder = param.split(":");
                            var.setName(varBuilder[0]);
                            var.setType(varBuilder[1]);
                            tmpFunction.setParameter(var);
                        }
                        String returnType = "";
                        while ((c = bufferedReader.read()) != 123) { // Find the starting curly bracket
                            // System.out.println("Find the return type.....");
                            if (c == 10 || c == 13 || c == 58 || c == 32) continue;
                            returnType += (char) c;
                        }
                        tmpFunction.setReturnType(returnType);
                        while ((c = bufferedReader.read()) != 125);// Finish function body according to end curly bracket
                        functionList.add(tmpFunction);
                    }else if(tmp.equals("enum")){
                        String eName = "";
                        while((c = bufferedReader.read()) != 123){
                            if(c==10 || c==13 || c==32) continue;
                            eName += (char) c;
                        }
                        Enum enu = new Enum(eName);
                        String eVariables = "";
                        while((c = bufferedReader.read()) != 125) if(!(c == 10 || c==13 || c==32)) eVariables+=(char) c;
                        String[]eVarList = eVariables.split(",");
                        for(String var : eVarList){
                            String[] nava = var.split("=");
                            Enum.EnumVar enumVar = new Enum.EnumVar(nava[0], nava[1]);
                            enu.addVariable(enumVar);
                        }
                        enumList.add(enu);

                    }
                } else names.add(tmp);
                tmp = "";
                continue;
            }
            /**
             * if (c == 32) {// Space check here do int check
             *                 if (tmp.length() < 1) continue;
             *                 if (keywords.contains(tmp)) types.add(tmp);
             *                 else names.add(tmp);
             *                 tmp = "";
             *                 continue;
             *             }
             */
            tmp += character;
            // System.out.print(c + " ");
        }
        System.out.println();
        // System.out.println("Types: " + types.toString());
        // for(int i=0; i<names.size(); ++i){
        //     System.out.println(names.get(i));
        // }
        System.out.println(functionList.toString());
        System.out.println(enumList.toString());
        System.out.println("Types: "+ types.toString());
        System.out.println("Names: "+ names.toString());
    }
}
