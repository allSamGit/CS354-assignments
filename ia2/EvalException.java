/**
 * CS354-1 ia2
 * EvalException Class
 * @author Saman Rastgar
 */
public class EvalException extends Exception {

    private int pos;
    private String msg;

    /**
     * Constructor: Initialize pos and msg
     * @param pos
     * @param msg
     */
    public EvalException(int pos, String msg) {
	this.pos=pos;
	this.msg=msg;
    }

    /**
     * EvalException toString
     * @return String
     */
    public String toString() {
	return "eval error"
	    +", pos="+pos
	    +", "+msg;
    }

}
