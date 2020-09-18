/**
 * Block Node Class
 * Extends Node
 * @author Saman Rastgar
 */
public class NodeBlock extends Node {

    private NodeStmt stmt;
    private NodeBlock block;

    /**
     *  CONSTRUCTOR: NodeBlock
     * @param stmt
     * @param block
     */
    public NodeBlock(NodeStmt stmt, NodeBlock block){
        this.stmt = stmt;
        this.block = block;
    }

    /**
     * NodeBlock eval method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        double ev = stmt.eval(env);
        if (block == null){
            return ev;
        } else {
            return block.eval(env);
        }
    }
}
