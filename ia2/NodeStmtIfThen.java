/**
 * Stmt If Then Node Class
 * Extend NodeStmt
 * @author Saman Rastgar
 */
public class NodeStmtIfThen extends NodeStmt {
    private NodeBoolExpr boolExpr;
    private NodeStmt thenStmt;
    private NodeStmt elseStmt;

    /**
     * CONSTRUCTOR: NodeStmtIfThen
     * @param boolExpr
     * @param thenStmt
     * @param elseStmt
     */
    public NodeStmtIfThen(NodeBoolExpr boolExpr, NodeStmt thenStmt, NodeStmt elseStmt) {
        this.boolExpr = boolExpr;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }

    /**
     * NodeStmtIfThe eval method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException{
        if (boolExpr.eval(env) == 1.0){
            return thenStmt.eval(env);
        }else if (elseStmt == null){
            return 0.0;
        }else {
            return elseStmt.eval(env);
        }
    }
}
