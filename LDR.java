package jjp.gpvm;
/**
* Opcode LDR loads relative to what is in the 
* dspace at the sp+x and store it in the accumulator. 
*	Pre: x valid input
*	Post: ac = dpsace[sp+x]
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class LDR extends AbstractOpCode{
        /**
         * constructs a load relative opcode.
         */
        public LDR(){
                super("ldr", "Loads relative from dspace at sp+x into the accumulator", 1);        
        }
        /**
         * Functor to execute the load relative.
         */
	public void opCode(GPVM g) {
		g.setAcc(Integer.parseInt(g.getDSpace(g.getSP()+g.getOperand())));
	}
}