public class Token {

    private TokenType type=null;
    private String value=null;
    private Keyword keyword=null;

    public Token(TokenType type){
        this.type=type;
    }

    public Token(TokenType type, String value){
        this.type=type;
        this.value=value;
    }

    public Token(TokenType type, Keyword keyword){
        this.type=type;
        this.keyword=keyword;
    }

    public String getStringRep(){return "Token("+type+", "+value+")";}

    public TokenType getType(){return type;}
    public String getValue(){return value;}
    public Keyword getKeyword(){return keyword;}
}
