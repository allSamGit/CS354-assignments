/**
 * CS354-1 ia2
 * Term Node Class
 * @author Saman Rastgar
 */
public class NodeTerm extends Node {

    private NodeFact fact; //Grammar fact
    private NodeMulop mulop; //Grammar mulop
    private NodeTerm term; //Grammar term

	/**
	 * Constructor: Initialize fact, mulop, and term
	 * @param fact
	 * @param mulop
	 * @param term
	 */
    public NodeTerm(NodeFact fact, NodeMulop mulop, NodeTerm term) {
	this.fact=fact;
	this.mulop=mulop;
	this.term=term;
    }

	/**
	 * Append NodeTerm
	 * @param term
	 */
	public void append(NodeTerm term) {
	if (this.term==null) {
	    this.mulop=term.mulop;
	    this.term=term;
	    term.mulop=null;
	} else
	    this.term.append(term);
    }

    /**
     *Term evaluation
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return term==null
	    ? fact.eval(env)
	    : mulop.op(term.eval(env),fact.eval(env));
    }

}
