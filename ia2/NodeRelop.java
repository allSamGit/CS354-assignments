/**
 * Relop Node Class
 * @author Saman Rastgar
 */
public class NodeRelop extends Node{
    private int pos;
    private String relop;

	/**
	 * CONSTRUCTOR: NodeRelop
	 * @param pos
	 * @param relop
	 */
	public NodeRelop(int pos, String relop){
        this.pos=pos;
        this.relop = relop;
    }

	/**
	 * NodeRelop operation method
	 * @param o1
	 * @param o2
	 * @return
	 * @throws EvalException
	 */
    public double op(double o1, double o2) throws EvalException{
        if (relop.equals("<")){
            if (o1 < o2){
                return 1.0;
            }
                return 0.0;
        }

        if (relop.equals("<=")){
            if (o1 <= o2){
                return 1.0;
            }
                return 0.0;
        }

        if (relop.equals(">")){
            if (o1 > o2){
                return 1.0;
            }
                return 0.0;
        }

        if (relop.equals(">=")){
            if (o1 >= o2){
                return 1.0;
            } else {
                return 0.0;
            }
        }

        if (relop.equals("<>")){
            if (o1 != o2){
                return 1.0;
            }
                return 0.0;
        }

        if (relop.equals("==")){
            if (o1 == o2){
                return 1.0;
            }
                return 0.0;
        }
        throw new EvalException(pos, "bogus relop: " + relop);
    }
}
