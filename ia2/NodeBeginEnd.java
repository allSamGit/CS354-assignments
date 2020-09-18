/**
 * Begin End Node Class
 * @author Saman Rastgar
 */
public class NodeBeginEnd extends NodeStmt{
    private NodeBlock block;

    /**
     * CONSTRUCTOR: NodeBeginEnd
     * @param block
     */
    public NodeBeginEnd(NodeBlock block) {
        this.block = block;
    }

    /**
     * NodeBeginEnd eval method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException{
        return block.eval(env);
    }
}
