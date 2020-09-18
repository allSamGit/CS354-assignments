// This class models a token, which has two parts:
// 1) the token itself (e.g., "id" or "+")
// 2) the token's lexeme (e.g., "foo")

/**
 * CS354-1 ia2
 * This class models a token, which has two parts:
 * 1) the token itself (e.g., "id" or "+")
 * 1) the token's lexeme (e.g., "foo")
 * @author Saman Rastgar
 */
public class Token {

    private String token; //Token
    private String lexeme; //Lexeme

    /**
     * Constructor: Initialize token and lexeme
     * @param token
     * @param lexeme
     */
    public Token(String token, String lexeme) {
	this.token=token;
	this.lexeme=lexeme;
    }

    /**
     * Constructor:
     * - Call two parameter token
     * @param token
     */
    public Token(String token) {
	this(token,token);
    }

    /**
     * Get token
     * @return token
     */
    public String tok() { return token; }

    /**
     * Get lexeme
     * @return lexeme
     */
    public String lex() { return lexeme; }

    /**
     * Check if two token are equal
     * @param t
     * @return boolean
     */
    public boolean equals(Token t) {
	return token.equals(t.token);
    }

    /**
     * String representation of token
     * @return String
     */
    public String toString() {
	return "<"+tok()+","+lex()+">";
    }

}
