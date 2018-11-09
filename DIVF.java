package jjp.gpvm;
/**
* Opcode DIVF divides two fixed point numbers from the top 
* of the stack and store the result back on top of the stack. 
*	Pre: the top four values on the stack will be valid and will 
*               represent entire first number (top), location of decimal 
*               in first number (second), entire second number (third), 
*               location of decimal in second number (fourth)
*	Post: the top two values on the stack will be the quotient 
*               (top/second), possible loss of precision, with the top 
*               value on the stack the entire number and the second value 
*               on the stack the location of the decimal
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class DIVF extends AbstractOpCode {
	/**
	 * constructs an divide fixed point opcode.
	 */
	public DIVF(){
		super("divf", "Fixed point division", 0);
	}
        /**
         * Functor to execute the divide fixed point.
         */ 
	public void opCode(GPVM g) {
                String tempA1 = g.pop()+""; int tempA2 = g.pop();
                String tempB1 = g.pop()+""; int tempB2 = g.pop();
                double tempA = Double.parseDouble(tempA1.substring(0, tempA2)+"."+tempA1.substring(tempA2));
                double tempB = Double.parseDouble(tempB1.substring(0, tempB2)+"."+tempB1.substring(tempB2));
                double result = tempA/tempB;
                String sResult = result+"";
                if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) 
                    System.out.println("Overflow during fixed point division");
                else{
                    for(int i=0; i<sResult.length(); i++){
                        if(sResult.charAt(i)=='.'){
                            if(i>=10) g.push(10);
                            else g.push(i);
                            g.push(Integer.parseInt(sResult.substring(0, i)+sResult.substring(i+1)));
                            break;
                        }
                    }
                }
	}
}
