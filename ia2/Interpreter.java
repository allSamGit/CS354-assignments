// This is the main class/method for the interpreter.
// Each command-line argument is a complete program,
// which is scanned, parsed, and evaluated.
// All evaluations share the same environment,
// so they can share variables.

/**
 * CS354-1
 * Interpreter Class
 * @author Saman Rastgar
 */
public class Interpreter {

    public static void main(String[] args) {
	Parser parser=new Parser(); //Create a parser object
	Environment env=new Environment(); //Create a environment object

	//Loop through commandline input
	for (String stmt: args)
	    try {
		parser.parse(stmt).eval(env); //Call parse on commandline input(stmt), and eval on environment
	    } catch (SyntaxException e) {
		System.err.println(e);
	    } catch (EvalException e) {
		System.err.println(e);
	    }
    }

}
