package readme.generator;

public enum RGASCII {
    HORIZONTAL_TAB(9),
    SPACE(32),
    NEW_LINE(10),
    LINE_FEED(13),
    ASTERIX(42),
    COMMA(44),
    SEMI_COLON(59) ,
    COLON(58),
    SLASH(47),
    BACKSLASH(92),
    OPENING_PARANTHESIS(40),
    CLOSING_PARANTHESIS(41),
    CURLY_OPENING_BRACKET(123),
    CURLY_CLOSING_BRACKET(125);

    private int asciiValue;

    RGASCII(int asciiValue){
        this.asciiValue = asciiValue;
    }

    public int getAscii() {
        return asciiValue;
    }
}
