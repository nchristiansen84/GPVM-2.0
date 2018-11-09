package jjp.gpvm;
/**
* Opcode LDC loads a constant value, x, 
* and store it in the accumulator. 
*	Pre: x valid input 
*	Post: ac = x
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class LDC extends AbstractOpCode{
        /**
         * constructs a load constant opcode.
         */
        public LDC(){
                super("ldc", "Loads constant x into the accumulator", 1);
        }
        /**
         * Functor to execute the load constant.
         */
	public void opCode(GPVM g) {
		g.setAcc(g.getOperand());
	}
}