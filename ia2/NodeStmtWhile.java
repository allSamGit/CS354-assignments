/**
 * Stmt While Node Class
 * Extends NodeStmt
 * @author Saman Rastgar
 */
public class NodeStmtWhile extends NodeStmt{
    NodeBoolExpr boolExpr;
    NodeStmt doStmt;

	/**
	 * CONSTRUCTOR: NodeStmtWhile
	 * @param boolExpr
	 * @param doStmt
	 */
	public NodeStmtWhile(NodeBoolExpr boolExpr, NodeStmt doStmt){
        this.boolExpr = boolExpr;
        this.doStmt = doStmt;
    }

	/**
	 * NodeStmtWhile eval method
	 * @param env
	 * @return
	 * @throws EvalException
	 */
    public double eval(Environment env) throws EvalException{
        while (boolExpr.eval(env) == 1.0){
            return doStmt.eval(env);
        }
        return 0.0;
    }
}