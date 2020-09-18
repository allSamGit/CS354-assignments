/**
 * CS354-1 ia2
 * FactExpr Node Class
 * @author Saman Rastgar
 */
public class NodeFactExpr extends NodeFact {

    private NodeExpr expr; //Grammar expr

    /**
     * Constructor: initialize expr
     * @param expr
     */
    public NodeFactExpr(NodeExpr expr) {
        this.expr=expr;
    }

    /**
     *FactExpr Node Evaluation
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return expr.eval(env);
    }

}
