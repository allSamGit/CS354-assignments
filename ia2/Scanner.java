/**
 * CS354-1 ia2
 * This class is a scanner for the program and the programming language
 * being interpreted
 * @author Saman Rastgar
 */

import java.util.*;

public class Scanner {

    private String program;	// source program being interpreted
    private int pos;		// index of next char in program
    private Token token;	// last/current scanned token

    // sets of various characters and lexemes
    private Set<String> whitespace=new HashSet<String>();
    private Set<String> digits=new HashSet<String>();
    private Set<String> letters=new HashSet<String>();
    private Set<String> legits=new HashSet<String>();
    private Set<String> keywords=new HashSet<String>();
    private Set<String> operators=new HashSet<String>();
    private Set<String> commentchar=new HashSet<String>();

	/**
	 * Initializer for previous sets
	 * @param s - String set
	 * @param lo - lower character
	 * @param hi - higher character
	 */
    private void fill(Set<String> s, char lo, char hi) {
	for (char c=lo; c<=hi; c++)
	    s.add(c+"");
    }

	/**
	 * Initialize white space set
	 * @param s - white space string set
	 */
	private void initWhitespace(Set<String> s) {
	s.add(" ");
	s.add("\n");
	s.add("\t");
    }

	/**
	 * Initialize digits set
	 * @param s - digits string set
	 */
	private void initDigits(Set<String> s) {
		s.add(".");
		fill(s,'0','9');
    }

	/**
	 * Initialize letters set
	 * @param s - letters string set
	 */
	private void initLetters(Set<String> s) {
	fill(s,'A','Z');
	fill(s,'a','z');
    }

	/**
	 * Initialize legits set
	 * @param s - legits string set
	 */
	private void initLegits(Set<String> s) {
	s.addAll(letters);
	s.addAll(digits);
    }

	/**
	 * Initialize operators set
	 * @param s -  operators string set
	 */
	private void initOperators(Set<String> s) {
	s.add("=");
	s.add("+");
	s.add("-");
	s.add("*");
	s.add("/");
	s.add("(");
	s.add(")");
	s.add(";");
	s.add("<");
	s.add("<=");
	s.add(">");
	s.add(">=");
	s.add("<>");
	s.add("==");
    }

	/**
	 * Initialize keywords set
	 * @param s - keywords string set
	 */
	private void initKeywords(Set<String> s) {
		s.add("wr");
		s.add("rd");
		s.add("if");
		s.add("then");
		s.add("else");
		s.add("while");
		s.add("do");
		s.add("begin");
		s.add("end");
    }

    /**
     * Initialize commentchar set
     * @param s - commentchar string set
     */
    private void initCommentchar(Set<String> s) {
        s.add("$");
    }

	/**
	 * Constructor:
	 * -squirrel-away source program
	 * - initialize sets
	 * @param program - program to scan
	 */
    public Scanner(String program) {
	this.program=program;
	pos=0; //Index to the next character in program
	token=null;
	initWhitespace(whitespace); //Initialize whitespace string set
	initDigits(digits); //Initialize digits string set
	initLetters(letters); //Initialize letters string set
	initLegits(legits); //Initialize legits string set
	initKeywords(keywords); //Initialize keywords string set
	initOperators(operators); //Initialize operators string set
    initCommentchar(commentchar); //Initialize comment character string set
    }

	/**
	 * Handy string-processing methods
	 * @return boolean - true if position is great than or equal to program length, false otherwise
	 */
    public boolean done() {
	return pos>=program.length(); //Check if the index to the next character in program is greater than or equal to the program length
    }

	/**
	 * Check how many
	 * @param s - string set
	 */
	private void many(Set<String> s) {
	while (!done() && s.contains(program.charAt(pos)+""))
	    pos++; //Increment the index to the next character in program
    }

	/**
	 * Past a character
	 * @param c - the character past
	 */
	private void past(char c) {
	while (!done() && c!=program.charAt(pos))
	    pos++;
	if (!done() && c==program.charAt(pos))
	    pos++;
    }

	/**
	 * Scan various kinds of lexeme
	 */
    private void nextNumber() {
	int old=pos; //Index of the next character in the program assign to old
	many(digits); //How many digits is in a row
	token=new Token("num",program.substring(old,pos)); //New Token("num", program.substring(old,pos) assign to token
    }

	/**
	 *Check for legal ID (variable name)
	 */
	private void nextKwId() {
	int old=pos; //Index of the next character in the program assign to old
	many(letters); //How many letters is in a row
	many(legits); //How many legits is in a row

	String lexeme=program.substring(old,pos); //Call substring (old,pos) on program assign value to lexeme
	token=new Token((keywords.contains(lexeme) ? lexeme : "id"),lexeme); //Create new Token((keywords.contains(lexeme) ? lexeme : "id:),lexeme) object assign it to token
    }

	/**
	 *Next operator
	 */
	private void nextOp() {
	int old=pos; //Index of the next character in the program assign to old
	pos=old+2; //Old + 2 assign to the next character in the program

		//Check if the program is done
	if (!done()) {
	    String lexeme=program.substring(old,pos); //Call substring(old,pos) on program and assign value to lexeme

		//Check if lexeme is a operator
	    if (operators.contains(lexeme)) {
		token=new Token(lexeme); // two-char operator
		return;
	    }
	}
	pos=old+1; //Old + 1 assign to the next character in the program
	String lexeme=program.substring(old,pos); //Call substring(old,pos) on program and assign value to lexeme
	token=new Token(lexeme); // one-char operator
    }

    /**
     * Next comment character
     */
    public void nextComment()  {
        pos++;
        past('$');
        next();
    }

	/**
	 * This method determines the kind of the next token (e.g., "id"),
	 * and calls a method to scan that token's lexeme (e.g., "foo").
	 * @return boolean
	 */
    public boolean next() {
	if (done())
	    return false;
	many(whitespace);
	String c=program.charAt(pos)+"";

	if (digits.contains(c)) //Check if c is a digit
	    nextNumber(); //Call private method nextNumber
	else if (letters.contains(c)) //Check if c is letter
	    nextKwId(); //Call private method nextKwId
	else if (operators.contains(c)) //Check if c is operator
	    nextOp(); //Call private method nextOp
    else if(commentchar.contains(c))//Check if c is comment
        nextComment(); //Call private method nextComment
	else {
	    System.err.println("illegal character at position "+pos);
	    pos++; //Increment index of next character in the program
	    return next();
	}
	return true;
    }

	/**
	 * This method scans the next lexeme,
	 * if the current token is the expected token.
	 * @param t - Token
	 * @throws SyntaxException
	 */
    public void match(Token t) throws SyntaxException {
	if (!t.equals(curr()))
	    throw new SyntaxException(pos,t,curr());
	next(); //Call next
    }

	/**
	 * Get a current token
	 * @return token - current token
	 * @throws SyntaxException
	 */
	public Token curr() throws SyntaxException {
		//Check if token is equal to null
	if (token==null)
	    throw new SyntaxException(pos,new Token("ANY"),new Token("EMPTY"));
	return token; //Return token
    }

	/**
	 * Get the index (position) of the next character in program
	 * @return int - character index (position)
	 */
	public int pos() { return pos; }

    // for unit testing
    public static void main(String[] args) {
	try {
	    Scanner scanner=new Scanner(args[0]);
	    while (scanner.next())
		System.out.println(scanner.curr());
	} catch (SyntaxException e) {
	    System.err.println(e);
	}
    }
}