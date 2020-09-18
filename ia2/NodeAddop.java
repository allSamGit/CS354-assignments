/**
 * CS354-1 ia2
 * Addop Node Class
 * @author Saman Rastgar
 */
public class NodeAddop extends Node {

    private String addop; //Grammar addop

	/**
	 * Constructor: initialize pos, and addop
	 * @param pos
	 * @param addop
	 */
    public NodeAddop(int pos, String addop) {
	this.pos=pos;
	this.addop=addop;
    }

    /**
     * Add or subtract operation
     * @param o1
     * @param o2
     * @return
     * @throws EvalException
     */
    public double op(double o1, double o2) throws EvalException {
	if (addop.equals("+"))
	    return o1+o2;
	if (addop.equals("-"))
	    return o1-o2;
	throw new EvalException(pos,"bogus addop: "+addop);
    }

}
