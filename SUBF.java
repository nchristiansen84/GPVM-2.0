package jjp.gpvm;
/**
* Opcode SUBF subtracts two fixed point numbers from the 
* top of the stack and store the result back on top of the stack. 
*	Pre: the top four values on the stack will be valid and will 
*               represent entire first number (top), location of decimal 
*               in first number (second), entire second number (third), 
*               location of decimal in second number (fourth)
*	Post: the top two values on the stack will be the difference 
*               (top-second), possible loss of precision, with the top 
*               value on the stack the entire number and the second 
*               value on the stack the location of the decimal
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class SUBF extends AbstractOpCode {
	/**
	 * constructs an sub fixed point opcode.
	 */
	public SUBF(){
		super("subf", "Fixed point subtraction", 0);
	}
        /**
         * Functor to execute the sub fixed point.
         */ 
	public void opCode(GPVM g) {
                String tempA1 = g.pop()+""; int tempA2 = g.pop();
                String tempB1 = g.pop()+""; int tempB2 = g.pop();
                long leftOfDecimal = Integer.parseInt(tempA1.substring(0, tempA2))-
                        Integer.parseInt(tempB1.substring(0, tempB2));
                long rightOfDecimal = Integer.parseInt(tempA1.substring(tempA2))-
                        Integer.parseInt(tempB1.substring(tempB2));
                long result = Long.parseLong(leftOfDecimal+""+rightOfDecimal);
                if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) 
                    System.out.println("Overflow during fixed point subtraction");
                else {
                    if(tempA2>=tempB2) g.push(tempA2);
                    else g.push(tempB2);
                    g.push((int)result);
                }
	}
}
