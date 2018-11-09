package jjp.gpvm;
/**
* Opcode LD load what is in the dspace at x
* and store it in the accumulator.
*	Pre: x is valid address 
*	Post: ac = dspace[x]
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class LD extends AbstractOpCode{
        /**
         * constructs a load opcode.
         */
        public LD(){
                super("ld", "Loads from dspace at x into the accumulator", 1);        
        }
        /**
         * Functor to execute the load.
         */
	public void opCode(GPVM g) {
		g.setAcc(Integer.parseInt(g.getDSpace(g.getOperand())));
	}
}