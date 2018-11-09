package jjp.gpvm;
/**
* Opcode ST will store what is in the accumulator
* into the dspace at x. 
*	Pre: x valid address 
*	Post: dpsace[x] = ac
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class ST extends AbstractOpCode{
        /**
         * constructs a store opcode.
         */
        public ST(){
                super("st", "Stores accumulator in dspace at x", 1);        
        }
        /**
         * Functor to execute the store.
         */
	public void opCode(GPVM g) {
                g.setDSpace(g.getOperand(), g.getAcc()+"");
	}
}