/**
 * CS354-1 ia2
 * This class is a recursive-descent parser, modeled after the programming
 * language's grammar. It constructs and has-a Scanner for the program being parsed.
 * @author Saman Rastgar
 */

public class Parser {

    private Scanner scanner;

    /**
     * Check if two token match
     * @param s - The string to check
     * @throws SyntaxException
     */
    private void match(String s) throws SyntaxException {
	scanner.match(new Token(s));
    }

    /**
     * Get a token from the scanner
     * @return scanner token
     * @throws SyntaxException
     */
    private Token curr() throws SyntaxException {
	return scanner.curr();
    }

    /**
     * Get the scanner next character index (position)
     * @return int - scanner next character index
     */
    private int pos() {
	return scanner.pos();
    }

    /**
     * The grammar mulop
     * @return NodeMulop - '*' or '/'
     * @throws SyntaxException
     */
    private NodeMulop parseMulop() throws SyntaxException {

        //Check if "*" is equal to curr
    if (curr().equals(new Token("*"))) {
	    match("*"); //Check if "*" match the current token
	    return new NodeMulop(pos(),"*");
	}

	//Check if "/" is equal to curr
	if (curr().equals(new Token("/"))) {
	    match("/"); //Check if "/" match the current token
	    return new NodeMulop(pos(),"/");
	}

	return null;
    }

    /**
     * The grammar addop
     * @return NodeAddop - '+' or '-'
     * @throws SyntaxException
     */
    private NodeAddop parseAddop() throws SyntaxException {

        //Check if "+" is equal to curr
	if (curr().equals(new Token("+"))) {
	    match("+"); //Check if "+" match the current token
	    return new NodeAddop(pos(),"+");
	}

	//Check if "-" is equal to curr
	if (curr().equals(new Token("-"))) {
	    match("-"); //Check if "_" match the current token
	    return new NodeAddop(pos(),"-");
	}

	return null;
    }

    /**
     * The grammar fact
     * @return NodeFact - id or num or '(' expr ')'
     * @throws SyntaxException
     */
    private NodeFact parseFact() throws SyntaxException {

        //Check if "(" is equal to current token
	if (curr().equals(new Token("("))) {
	    match("("); //Check if "(" match the current token

	    //Check for the matching ")"
	    NodeExpr expr=parseExpr();
	    match(")"); //Check if ")" match the current token
	    return new NodeFactExpr(expr);
	}

	//Check if "id" is equal to current token
	if (curr().equals(new Token("id"))) {
	    Token id=curr(); //Call current token assign value to id
	    match("id"); //Check if "id" match the current token
	    return new NodeFactId(pos(),id.lex());
	}

	if (curr().equals(new Token("-"))){
	    match("-");
	    NodeFact fact=parseFact();
	    return new NodeFactMinusFact(fact);
    }

    if (curr().equals(new Token("num"))){
	    //Check if curr match with num
	    Token num=curr(); //Call current token assign value to num
	    match("num"); //Check if "num" match the current token
	    return new NodeFactNum(num.lex());
    }
        return null;
    }

    /**
     * The grammar term
     * @return NodeTerm - fact mulop term or fact
     * @throws SyntaxException
     */
    private NodeTerm parseTerm() throws SyntaxException {
	NodeFact fact=parseFact(); //Call parseFact assign value to fact
	NodeMulop mulop=parseMulop(); //Call parseMulop assign value to mulop

        //Check if mulop is null
	if (mulop==null)
	    return new NodeTerm(fact,null,null);


	NodeTerm term=parseTerm(); //Call parseTerm assign value to term
	term.append(new NodeTerm(fact,mulop,null));

	return term;
    }

    /**
     * The grammar expr
     * @return NodeExpr - term addop expr or term
     * @throws SyntaxException
     */
    private NodeExpr parseExpr() throws SyntaxException {
	NodeTerm term=parseTerm(); //Call parseTerm assign value to term
	NodeAddop addop=parseAddop(); //Call parseAddop assign value to addop

        //Check if addop is null
	if (addop==null)
	    return new NodeExpr(term,null,null);

	NodeExpr expr=parseExpr(); //Call parseExpr assign value to expr
	expr.append(new NodeExpr(term,addop,null));

	return expr;
    }

    /**
     * The grammar assn
     * @return NodeAssn - id '=' expr
     * @throws SyntaxException
     */
    private NodeAssn parseAssn() throws SyntaxException {
	Token id=curr();
	match("id"); //Check if "id" match is the current token
	match("="); //Check if "=" match is the current token

	NodeExpr expr=parseExpr(); //Call parseExpr assign value to expr
	NodeAssn assn=new NodeAssn(id.lex(),expr);

	return assn;
    }

    /**
     * The grammar stmt
     * @return NodeStmt - assn ';'
     * @throws SyntaxException
     */
    private NodeStmt parseStmt() throws SyntaxException {
    if (curr().equals(new Token("id"))){
        NodeAssn assn=parseAssn();
        NodeStmtAssn assnNode = new NodeStmtAssn(assn);
        return assnNode;
    }

    if (curr().equals(new Token("rd"))){
        NodeStmtRd read = parseRd();
        return read;
    }

    if (curr().equals(new Token("wr"))){
        NodeStmtWr write = parseWr();
        return write;
    }

    if (curr().equals(new Token("if"))){
        NodeStmtIfThen ifStmt = parseIf();
        return ifStmt;
    }

    if (curr().equals(new Token("while"))){
        NodeStmtWhile whileStmt = parseWhile();
        return whileStmt;
    }

    match("begin");
    NodeBlock beginBlock = parseBlock();
    match("end");
    NodeBeginEnd beginEnd = new NodeBeginEnd(beginBlock);
    return beginEnd;

    }

    private NodeStmtWhile parseWhile() throws SyntaxException {
        match("while");
        NodeBoolExpr boolExpr = parseBoolExpr();
        match("do");
        NodeStmt doStmt = parseStmt();
        NodeStmtWhile whileStmt = new NodeStmtWhile(boolExpr, doStmt);
        return whileStmt;
    }

    private NodeStmtIfThen parseIf() throws SyntaxException {
        match("if");
        NodeBoolExpr boolExpr = parseBoolExpr();
        match("then");
        NodeStmt thenStmt = parseStmt();
        NodeStmt elseStmt = null;

        if (curr().equals(new Token("else"))){
            match("else");
            elseStmt = parseStmt();
        }
        NodeStmtIfThen ifStmt = new NodeStmtIfThen(boolExpr, thenStmt, elseStmt);
        return ifStmt;
    }

    private NodeBoolExpr parseBoolExpr() throws SyntaxException{
        NodeExpr expr1 = parseExpr();
        NodeRelop relop = parseRelop();
        NodeExpr expr2 = parseExpr();

        NodeBoolExpr boolExpr = new NodeBoolExpr(expr1, relop, expr2);
        return boolExpr;
    }

    private NodeRelop parseRelop() throws SyntaxException {
        if (curr().equals(new Token(">"))){
            match(">");
            return new NodeRelop(pos(),">");
        }

        if (curr().equals(new Token("<"))){
            match("<");
            return new NodeRelop(pos(), "<");
        }

        if (curr().equals(new Token(">="))){
            match(">=");
            return new NodeRelop(pos(),">=");
        }

        if (curr().equals(new Token("<="))){
            match("<=");
            return new NodeRelop(pos(),"<=");
        }

        if (curr().equals(new Token("<>"))){
            match("<>");
            return new NodeRelop(pos(),"<>");
        }

        if (curr().equals(new Token("=="))){
            match("==");
            return new NodeRelop(pos(), "==");
        }
        return new NodeRelop(pos(), "");
    }

    private NodeStmtWr parseWr() throws SyntaxException {
        match("wr");
        NodeExpr expr = parseExpr();
        NodeStmtWr write = new NodeStmtWr(expr);
        return write;
    }

    private NodeStmtRd parseRd() throws SyntaxException {
        match("rd");
        Token id = curr();
        match("id");
        NodeStmtRd read = new NodeStmtRd(id);
        return read;
    }

    private NodeBlock parseBlock() throws SyntaxException {
        NodeStmt stmt = parseStmt();
        NodeBlock block = null;

        if (curr().equals(new Token(";"))){
            match(";");
            block = parseBlock();
        }
        NodeBlock block1 = new NodeBlock(stmt, block);
        return block1;
    }

    private NodeBlock parseProg() throws SyntaxException {
        NodeBlock block = parseBlock();

        if (!scanner.done()){
            throw new SyntaxException(pos(), new Token("EOF"), curr());
        }
        return block;
    }

    /**
     * Parse program
     * @param program - the program to parse
     * @return - Node - parseStmt
     * @throws SyntaxException
     */
    public Node parse(String program) throws SyntaxException {
	scanner=new Scanner(program); //Send program to scanner
	scanner.next(); //scanner get next token
    return parseProg();
    }
}