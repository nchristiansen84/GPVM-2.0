package jjp.gpvm;
/**
* Opcode LDS will load a string from the dspace at x into the stringHolder. 
*	Pre: dspace[x] is valid string
*	Post: stringHolder = dpsace[x]
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class LDS extends AbstractOpCode{
        /**
         * constructs a load string opcode.
         */
        public LDS(){
                super("lds", "Loads string from dspace at the x", 1);        
        }
        /**
         * Functor to execute the load string.
         */
	public void opCode(GPVM g) {
                g.setSH(g.getDSpace(g.getOperand()));
	}
}