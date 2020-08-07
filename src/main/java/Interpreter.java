public class Interpreter {

    private int position=0;
    private Token currentToken=null;
    private String[] text;
    private String currentString;

    public Interpreter(String text){
        if(text!=null)
            this.text = text.split(" ");
        else
            this.text[0]="";
    }

    public void run(){
        currentToken=getNextToken();
        while(currentToken.getType()!=TokenType.EOF){
            eatToken(currentToken);
            currentToken=getNextToken();
        }
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
        }else if(currentString.equals("WaitFor:")){
            Token token = new Token(TokenType.KEYWORD, Keyword.WAIT);
            position++;
            return token;
        }else{
            position++;
        }

        return getNextToken();
    }

    public void eatToken(Token token){
        if(token.getType()==TokenType.KEYWORD){
            if(token.getKeyword().equals(Keyword.CLICK)){
                if(text.length>position && text.length>position+1) {
                    AutomationFeatures.click(Integer.parseInt(text[position]), Integer.parseInt(text[position + 1]));
                    position = position + 2;
                }
            }else if(token.getKeyword().equals(Keyword.WAIT)){
                if(text.length>position){
                    AutomationFeatures.wait(Integer.parseInt(text[position]));
                    position++;
                }
            }
        }
    }
}
