package jjp.gpvm;
/**
* Opcode STR stores relative to what is in the 
* accumulator into the dspace at the sp+x. 
*	Pre: x is valid input 
*	Post: dspace[sp+x] = ac
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class STR extends AbstractOpCode{
        /**
         * constructs a store relative opcode.
         */
        public STR(){
                super("str", "Stores relative accumulator in dspace at sp+x", 1);        
        }
        /**
         * Functor to execute the store relative.
         */
	public void opCode(GPVM g) {
                g.setDSpace(g.getSP()+g.getOperand(), g.getAcc()+"");
	}
}