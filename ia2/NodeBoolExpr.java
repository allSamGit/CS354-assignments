/**
 * Bool Expr Node Class
 * Extends NodeStmt
 * @author Saman Rastgar
 */
public class NodeBoolExpr extends NodeStmt{
    NodeExpr expr1;
    NodeExpr expr2;
    NodeRelop relop;

    /**
     * CONSTRUCTOR: NodeBoolExpr
     * @param expr1
     * @param relop
     * @param expr2
     */
    public NodeBoolExpr(NodeExpr expr1, NodeRelop relop, NodeExpr expr2){
        this.expr1 = expr1;
        this.expr2 = expr2;
        this.relop = relop;
    }

    /**
     * NodeBoolExpr eval method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException{
        return (relop.op(expr1.eval(env), expr2.eval(env)));
    }
}
