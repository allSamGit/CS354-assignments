/**
 * Stmt While Node Class
 * Extends NodeStmt
 * @author Saman Rastgar
 */
public class NodeStmtBegin extends NodeStmt{
    NodeBeginEnd beginEnd;

    public NodeStmtBegin(NodeBeginEnd beginEnd){
        this.beginEnd = beginEnd;
    }
}

