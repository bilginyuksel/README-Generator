package readme.generator.ts;


import com.sun.javafx.collections.UnmodifiableListSet;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public enum TSKeywords {
    FUNCTION("function"),
    ENUM("enum"),
    INTERFACE("interface"),
    VARIABLE("variable"),
    CLASS("class");

    private String keyword;

    private final static Set<String> accessSpecifiers =
            new HashSet<>(Arrays.asList("protected", "private", "public", "default"));

    private final static Set<String> variableTypes =
            new HashSet<>(Arrays.asList("const", "var", "let"));

    TSKeywords(String keyword){
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public static Set<String> getAccessSpecifiers() {
        return Collections.unmodifiableSet(accessSpecifiers);
    }

    public static Set<String> getVariableTypes() {
        return Collections.unmodifiableSet(variableTypes);
    }
}
