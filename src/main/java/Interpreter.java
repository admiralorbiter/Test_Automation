import java.awt.*;

public class Interpreter {

    private int position=0;
    private Token currentToken=null;
    private String[] text;
    private String currentString;
    private FileParser fileParser = null;

    public Interpreter(FileParser fileParser, String text){
        this.fileParser=fileParser;
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
        }else if(currentString.equals("ClickScreenshot")){
            Token token = new Token(TokenType.KEYWORD, Keyword.CLICKSCREENSHOT);
            position++;
            return token;
        }else if(currentString.equals("TakeScreenshot")){
            Token token = new Token(TokenType.KEYWORD, Keyword.TAKESCREENSHOT);
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
            }else if(token.getKeyword().equals(Keyword.CLICKSCREENSHOT)){
                if(text.length>position){
                    AutomationFeatures.findScreenshot(text[position]);
                    position++;
                }
            }else if(token.getKeyword().equals(Keyword.TAKESCREENSHOT)){
                if(text.length==position+1){
                    AutomationFeatures.takeScreenshot(fileParser, text[position]);
                    position++;
                }else if(text.length>position+1){
                    if(text[position+1].charAt(0)=='['){
                        //Include rectangle bounds
                        AutomationFeatures.takeScreenshot(fileParser, text[position],new Rectangle(
                                Integer.parseInt(text[position+1].substring(1)),
                                Integer.parseInt(text[position+2]),
                                Integer.parseInt(text[position+3]),
                                Integer.parseInt(text[position+4].substring(0, text[position+4].length()-1))
                        ));
                        position=position+4;//Probably will end up increasing by 4 since there will be 4 values for the bound
                    }else {
                        AutomationFeatures.takeScreenshot(fileParser,text[position]);
                        position++;
                    }
                }
            }
        }
    }
}
