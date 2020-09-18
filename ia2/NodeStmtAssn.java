/**
 * CS354-1 ia2
 * Stmt Assn Node Class
 * @author Saman Rastgar
 */
public class NodeStmtAssn extends NodeStmt {

    private NodeAssn assn; //Grammar assn

    /**
     * Constructor: Initialize assn
     * @param assn
     */
    public NodeStmtAssn(NodeAssn assn) {
	this.assn=assn;
    }

    /**
     *Stmt evaluation
     * @param env
     * @return int
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return assn.eval(env);
    }
}