import java.util.Scanner;

/**
 * Stmt Rd Node Class
 * Extends NodeStmt
 * @author Saman Rastgar
 */
public class NodeStmtRd extends NodeStmt{
    private static Scanner scan;
    private Token id;

    /**
     * CONSTRUCTOR: NodeStmtRd
     * @param id
     */
    public NodeStmtRd(Token id){
        this.id = id;
        scan = new Scanner(System.in);
    }

    /**
     * NodeStmtRd eval method
     * @param env
     * @return
     * @throws EvalException
     */
    public double eval(Environment env) throws EvalException {
        return env.put(id.lex(), scan.nextDouble());
    }
}
