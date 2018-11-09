package jjp.gpvm;
/**
* Opcode LDF loads a fixed point number from the dspace at x 
* and x+1 to the top of the stack. 
*	Pre: dspace[x] will equal the entire number without a decimal
*               point and dspace[x+1] will equal the location of the 
*               decimal point from the right side of the number and 
*               both will be valid
*	Post: top value on stack is entire number without decimal point 
*               and second value on stack is location of the decimal 
*               point from the right side of the number
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class LDF extends AbstractOpCode{
        /**
         * constructs a load fixed point opcode.
         */
        public LDF(){
                super("ldf", "Loads from dspace at x onto top of stack", 1);        
        }
        /**
         * Functor to execute the load fixed point.
         */
	public void opCode(GPVM g) {
                int operand = g.getOperand();
                g.push(Integer.parseInt(g.getDSpace(operand+1)));
                g.push(Integer.parseInt(g.getDSpace(operand)));
	}
}