/**
 * CS354-1 ia2
 * Environment Class
 * @author Saman Rastgar
 */

import java.util.HashMap;

public class Environment {

    private HashMap<String, Double> variables;

    /**
     * CONSTRUCTOR: Environment
     */
    public Environment(){
        variables = new HashMap<String, Double>();
    }

    /**
     * Put variable and val
     * @param var
     * @param val
     * @return
     */
    public double put(String var, double val) {
        variables.put(var,val);
        return val;
    }

    /**
     * Get varaible form a position in the program
     * @param pos
     * @param var
     * @return
     * @throws EvalException
     */
    public double get(int pos, String var) throws EvalException {
        if(variables.containsKey(var)){
            return variables.get(var);
        }
        throw new EvalException(pos, "Uninitialized variable");
    }

}
