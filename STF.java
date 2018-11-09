package jjp.gpvm;
/**
* Opcode STF stores a fixed point number on top of 
* the stack into the dspace at x and x+1. 
*	Pre: top value on stack is entire number without decimal 
*               point and second value on stack is location of the 
*               decimal point from the right side of the number
*	Post: dspace[x] will equal the value that was on top of the 
*               stack and dspace[x+1] will equal the value that was 
*               second value on stack
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class STF extends AbstractOpCode{
        /**
         * constructs a store fixed point opcode.
         */
        public STF(){
                super("stf", "Stores fixed point from top of the stack to dspace at x", 1);        
        }
        /**
         * Functor to execute the store fixed point.
         */
	public void opCode(GPVM g) {
                g.setDSpace(g.getOperand(), g.pop()+"");
                g.setDSpace(g.getOperand()+1, g.pop()+"");
	}
}