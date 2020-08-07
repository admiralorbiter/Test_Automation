public class Interpreter {

    private int position=0;
    private Token currentToken=null;
    private String[] text;
    private String currentString;

    public Interpreter(String text){
        this.text = text.split(" ");
    }

    public Token getNextToken(){
        this.text=text;
        if(position> text.length-1)
            return new Token(TokenType.EOF);

        currentString=text[position];

        if(currentString.equals("Click")) {
            Token token = new Token(TokenType.KEYWORD, Keyword.CLICK);
            position++;
            return token;
        }

        return null;
    }

    public void eatToken(Token token){
        if(token.getType()==TokenType.KEYWORD){
            if(token.getKeyword().equals(Keyword.CLICK)){
                AutomationFeatures.click(Integer.parseInt(text[position]), Integer.parseInt(text[position+1]));
                position=position+2;
            }
        }
    }
}
