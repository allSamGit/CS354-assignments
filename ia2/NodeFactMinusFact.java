/**
 * CS354-1 ia2
 * FactMinusFact Node Class
 * @author Saman Rastgar
 */
public class NodeFactMinusFact extends NodeFact{

    private NodeFact fact;

    /**
     * Constructor: Initialize fact
     * @param fact
     */
    public NodeFactMinusFact(NodeFact fact){
        this.fact = fact;
    }

    /**
     *FactMinusFact Evaluation
     * @param env
     * @return Double
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return fact.eval(env) * -1;
    }

}
