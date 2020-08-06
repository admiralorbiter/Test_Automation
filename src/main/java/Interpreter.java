public class Interpreter {

    private int position=0;
    private Token currentToken=null;
    private String[] text;
    private String currentString;

    public Interpreter(String text){
        this.text = text.split(" ");
    }

    public Token getNextToken(String[] text){
        this.text=text;
        if(position> text.length-1)
            return new Token(TokenType.EOF, null);

        currentString=text[position];

        if(currentString.equals("Click")) {
            Token token = new Token(TokenType.METHOD, "Click");
            position++;
            return token;
        }

        return null;
    }
}
