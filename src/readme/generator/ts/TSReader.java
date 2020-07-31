package readme.generator.ts;

import readme.generator.RGASCII;
import readme.generator.RGComponent;
import readme.generator.RGFileData;
import readme.generator.RGFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TSReader implements RGFileReader {


    private RGComponent readTsFunctionTemplate(BufferedReader reader) throws IOException{
        TSFunction tsFunction = new TSFunction();

        String functionName = "";
        int c = 0;
        while ((c = reader.read()) != 40)
            if(!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                functionName += (char) c;
        tsFunction.setfName(functionName);

        String parameterLine = "";
        while ((c = reader.read()) != 41)  // Find the closing paranthesis
            if (!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                parameterLine += (char) c;
        String[] parameters = parameterLine.split(",");
        if(parameterLine.length() == 0) parameters = new String[0];
        for (String param : parameters) {
            TSVariable var = new TSVariable();
            String[] varBuilder = param.split(":");
            var.setName(varBuilder[0]);
            var.setType(varBuilder[1]);
            tsFunction.setParameter(var);
        }

        String returnType = "";
        while ((c = reader.read()) != 123) // Find the starting curly bracket
            if (!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii() || c == RGASCII.COLON.getAscii()))
                returnType += (char) c;

        tsFunction.setReturnType(returnType);

        while (reader.read() != 125);// Finish function body according to end curly bracket

        return tsFunction;
    }

    private RGComponent readTsEnumTemplate(BufferedReader reader) throws IOException{
        TSEnum tsEnum = new TSEnum();
        String eName = "";
        int c = 0;
        while((c = reader.read()) != 123)
            if(!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                eName += (char) c;

        tsEnum.seteName(eName);

        String eVariables = "";
        while((c = reader.read()) != 125)
            if(!(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()))
                eVariables+=(char) c;

        String[]eVarList = eVariables.split(",");
        for(String var : eVarList){
            String[] nava = var.split("=");
            TSEnum.EnumVar enumVar = new TSEnum.EnumVar(nava[0], nava[1]);
            tsEnum.addVariable(enumVar);
        }

        return tsEnum;
    }

    private RGComponent readTsClassTemplate(BufferedReader reader){
        return null;
    }

    @Override
    public RGFileData read(String filename) throws IOException {
        RGFileData rgFileData = new RGFileData();
        File file = new File(filename);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        int c = 0;
        String keyw = "";
        while((c = bufferedReader.read()) != -1){
            // Until end of the file

            if(c == RGASCII.LINE_FEED.getAscii() || c == RGASCII.NEW_LINE.getAscii() || c == RGASCII.SPACE.getAscii()){
                if (keyw.length() < 1) continue;

                if(keyw.equals(TSKeywords.FUNCTION.getKeyword()))
                    rgFileData.addComponent(readTsFunctionTemplate(bufferedReader));
                else if(keyw.equals(TSKeywords.ENUM.getKeyword()))
                    rgFileData.addComponent(readTsEnumTemplate(bufferedReader));
                else if(keyw.equals(TSKeywords.CLASS.getKeyword()))
                    rgFileData.addComponent(readTsClassTemplate(bufferedReader));

                keyw = "";
            }else keyw += (char) c;

        }

        return rgFileData;
    }

    @Override
    public RGFileData read(String folderName, String extension) {
        return null;
    }
}
