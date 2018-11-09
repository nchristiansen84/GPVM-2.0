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
                double tempA = convert(g.pop(), g.pop());
                double tempB = convert(g.pop(), g.pop());
                if(tempB==0) {
                    System.out.println("Cannot devide by 0");
                    return;
                }
                String result = tempA/tempB+"";
                if(result.charAt(0)=='-' && result.length()>=11) result = result.substring(0, 12);
                else if(result.length()>=10) result = result.substring(0, 11);
                else result = result.substring(0, result.length());
                for(int i=0; i<result.length(); i++){
                    if(result.charAt(i)=='.'){
                        long temp = Long.parseLong(result.substring(0, i)+result.substring(i+1));
                        if(temp > Integer.MAX_VALUE || temp < Integer.MIN_VALUE){
                            System.out.println("Overflow during fixed point division");
                            g.terminate();
                        }
                        g.push(result.length()-(i+1));
                        g.push((int)temp);
                        break;
                    }
                }
	}
        private double convert(double base, int pow){
            if(pow>=0) for(int i=0; i<pow; i++) base/=10;
            else for(int i=0; i<pow*-1; i++) base*=10;
            return Double.parseDouble(base+"");
        }
}
