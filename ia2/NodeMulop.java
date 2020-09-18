/**
 * CS354-1 ia2
 * Mulop Node Class
 * @author Saman Rastgar
 */
public class NodeMulop extends Node {

    private String mulop;

    /**
     * Constructor: Initialize pos and mulop
     * @param pos
     * @param mulop
     */
    public NodeMulop(int pos, String mulop) {
	this.pos=pos;
	this.mulop=mulop;
    }

    /**
     * Mulop Operation
     * @param o1
     * @param o2
     * @return
     * @throws EvalException
     */
    public double op(double o1, double o2) throws EvalException {
	if (mulop.equals("*"))
	    return o1*o2;
	if (mulop.equals("/"))
	    return o1/o2;
	throw new EvalException(pos,"bogus mulop: "+mulop);
    }

}
