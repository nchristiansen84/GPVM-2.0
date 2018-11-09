package jjp.gpvm;
/**
* Opcode NEGF negates a fixed point number from the 
* top of the stack and store the result back on top of the stack. 
*	Pre: the top two values on the stack will be valid and will 
*               represent entire first number (top), location of 
*               decimal in first number (second)
*	Post: the top two values on the stack will be the negated value 
*               of the original value on the stack with the top value 
*               on the stack the entire number and the second value 
*               on the stack the location of the decimal
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class NEGF extends AbstractOpCode{
        /**
         * constructs a negate fixed point opcode.
         */
        public NEGF(){
                super("negf", "Fixed point negation", 0);        
        }
        /**
         * Functor to execute the negate fixed point.
         */
	public void opCode(GPVM g) {
                g.push(g.pop()*(-1));
	}
}