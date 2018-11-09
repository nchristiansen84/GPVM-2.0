package jjp.gpvm;
/**
* Opcode LDI loads indirect what is in the dspace at 
* the value in the accumulator and store it in the accumulator. 
*	Pre: ac is valid
*	Post: ac = dspace[ac]
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class LDI extends AbstractOpCode{
        /**
         * constructs a load indirect opcode.
         */
        public LDI(){
                super("ldi", "Load indirect into the accumulator", 0);
        }
        /**
         * Functor to execute the load indirect.
         */
	public void opCode(GPVM g) {
		g.setAcc(Integer.parseInt(g.getDSpace(g.getAcc())));
	}
}