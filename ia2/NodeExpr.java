/**
 * CS354-1 ai1
 * Expr Node Class
 * @author Saman Rastgar
 */
public class NodeExpr extends Node {

    private NodeTerm term; //Grammar term
    private NodeAddop addop; //Grammar addop
    private NodeExpr expr; //Grammer expr

    /**
     * Constructor: initialize term, addop, and expr
     * @param term
     * @param addop
     * @param expr
     */
    public NodeExpr(NodeTerm term, NodeAddop addop, NodeExpr expr) {
	this.term=term;
	this.addop=addop;
	this.expr=expr;
    }

    /**
     * Append NodeExpr
     * @param expr
     */
    public void append(NodeExpr expr) {
	if (this.expr==null) {
	    this.addop=expr.addop;
	    this.expr=expr;
	    expr.addop=null;
	} else
	    this.expr.append(expr);
    }

    /**
     *Expr Node Evaluation
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return expr==null
	    ? term.eval(env)
	    : addop.op(expr.eval(env),term.eval(env));
    }

}
