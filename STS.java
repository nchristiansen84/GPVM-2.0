package jjp.gpvm;
/**
* Opcode STS stores a string from stringHolder into the 
* dspace at x.
*       Pre: x valid address
*       Post: dspace[x]=stringHolder
* @author  The-Opcodes
* @version 1.0
* @since   March 2016  
*/
public class STS extends AbstractOpCode{
        /**
         * constructs a store string opcode.
         */
        public STS(){
                super("sts", "Stores string in dspace at x", 1);
        }
        /**
         * Functor to execute the store string.
         */
	public void opCode(GPVM g) {
                g.setDSpace(g.getOperand(), g.getSH());
	}
}