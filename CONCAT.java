package jjp.gpvm;
/**
* Opcode CONCAT will concatenate a string in the dspace 
* at x with a string in the dspace at x+1 then store the 
* resulting string in dspace at x. 
*	Pre: dspace[x] is valid string and dspace[x+1] is valid string
*	Post: dpsace[x] = dpsace[x]+dspace[x+1]
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class CONCAT extends AbstractOpCode{
        /**
         * constructs a concatenate opcode.
         */
        public CONCAT(){
                super("concat", "Concatenates two strings", 1);        
        }
        /**
         * Functor to execute the concatenate.
         */
	public void opCode(GPVM g) {
                g.setDSpace(g.getOperand(), g.getDSpace(g.getOperand())+
                        g.getDSpace(g.getOperand()+1));
	}
}