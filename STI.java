package jjp.gpvm;
/**
* Opcode STI stores indirect what is in the dspace at 
* the sp++ into the dspace at the value in the accumulator. 
*	Pre: ac is valid
*	Post: dspace[ac] = dspace[sp++]
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class STI extends AbstractOpCode{
        /**
         * constructs a store indirect opcode.
         */
        public STI(){
                super("sti", "Store indirect of dspace at sp in dspace at accumulator", 0);        
        }
        /**
         * Functor to execute the store indirect.
         */
	public void opCode(GPVM g) {
                g.setDSpace(g.getAcc(), g.getDSpace(g.getSP()+1));
	}
}