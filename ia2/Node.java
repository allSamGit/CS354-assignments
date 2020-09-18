// This class, and its subclasses,
// collectively model parse-tree nodes.
// Each kind of node can be eval()-uated.
/**
 * CS354-1 ia2
 * This Class, and it subclasses, collectively model parse-tree nodes
 * Each kind of node can eval()-uated.
 * @author Saman Rastgar
 */
public abstract class Node {

    protected int pos=0;

    /**
     * Node Evaluation
     * @param env
     * @return Double
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	throw new EvalException(pos,"cannot eval() node!");
    }

}
