/**
 * CS354-1 ia2
 * FactNum Node Class
 * @author Saman Rastgar
 */
public class NodeFactNum extends NodeFact {

    private String num; //Grammar num

    /**
     * Constructor: initialize num
     * @param num
     */
    public NodeFactNum(String num) {
        this.num=num;
    }

    /**
     *NodeFactNum evaluation
     * @param env
     * @return Double
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return Double.parseDouble(num);
    }
}
