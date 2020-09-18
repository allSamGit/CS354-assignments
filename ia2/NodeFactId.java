/**
 * CS354-1 ia2
 * FactId Class
 * @author Saman Rastgar
 */
public class NodeFactId extends NodeFact {

    private String id; //Grammar id

    /**
     * Constructor: initialize pos, and id
     * @param pos
     * @param id
     */
    public NodeFactId(int pos, String id) {
	this.pos=pos;
	this.id=id;
    }

    /**
     *FactId evaluation
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
	return env.get(pos,id);
    }

}
