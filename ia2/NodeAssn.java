/**
 * CS354-1 ia2
 * Assn Node Class
 * @author Saman Rastgar
 */
public class NodeAssn extends Node {

    private String id; //Grammar id
    private NodeExpr expr; //Grammar expr

    /**
     * Constructor: initialize id and expr
     * @param id
     * @param expr
     */
    public NodeAssn(String id, NodeExpr expr) {
	this.id=id;
	this.expr=expr;
    }

    /**
     *Assn Node Evaluation
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return env.put(id,expr.eval(env));
    }

}
