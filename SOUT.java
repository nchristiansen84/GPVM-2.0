package jjp.gpvm;
/**
* Opcode SOUT outputs a string that is stored 
* in the dspace at the value of the accumulator. 
*	Pre: ac = address that is valid
*	Post: dspace[ac] will be the string to be outputted
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class SOUT extends AbstractOpCode{
        /**
         * constructs a output opcode.
         */
        public SOUT(){
                super("sout", "Outputs what is in the dspace at the accumulator", 0);        
        }
        /**
         * Functor to execute the output.
         */
	public void opCode(GPVM g) {
                System.out.println(g.getDSpace(g.getAcc()));
	}
}