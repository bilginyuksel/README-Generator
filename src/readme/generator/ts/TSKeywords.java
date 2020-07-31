package readme.generator.ts;


public enum TSKeywords {
    FUNCTION("function"),
    ENUM("enum"),
    VARIABLE("variable"),
    CLASS("class");

    private String keyword;

    TSKeywords(String keyword){
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }


}
