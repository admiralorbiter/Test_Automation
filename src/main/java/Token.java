public class Token {

    private TokenType type;
    private String value;

    public Token(TokenType type, String value){
        this.type=type;
        this.value=value;
    }

    public String getStringRep(){return "Token("+type+", "+value+")";}
}
