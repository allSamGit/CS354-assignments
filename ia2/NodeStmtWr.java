/**
 * Stmt Node Wr
 * Extends NodeStmt
 * @author Saman Rastgar
 */
public class NodeStmtWr extends NodeStmt{

    private NodeExpr expr;

    /**
     * CONSTRUCTOR: NodeStmtWr
     * @param expr
     */
    public NodeStmtWr(NodeExpr expr){
        this.expr = expr;
    }

    /**
     * NodeStmtWr eval method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        System.out.println(expr.eval(env));
        return expr.eval(env);
    }
}
