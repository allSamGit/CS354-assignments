/**
 * CS354-1 ai1
 * SyntaxException Class
 * @author Saman Rastgar
 */
public class SyntaxException extends Exception {

    private int pos;
    private Token expected;
    private Token found;

    /**
     * Constructor: Initialize pos, expected and found
     * @param pos
     * @param expected
     * @param found
     */
    public SyntaxException(int pos, Token expected, Token found) {
	this.pos=pos;
	this.expected=expected;
	this.found=found;
    }

    /**
     * SyntaxException toString
     * @return String
     */
    public String toString() {
	return "syntax error"
	    +", pos="+pos
	    +", expected="+expected
	    +", found="+found;
    }

}
