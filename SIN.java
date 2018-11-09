package jjp.gpvm;

/**
* Opcode SIN takes in a string of input and store 
* it in the dspace at the value of the accumulator. 
*	Pre: ac = address that is valid
*	Post: dspace[ac] will equal the input string
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class SIN extends AbstractOpCode{
        /**
         * constructs a input opcode.
         */
        public SIN(){
                super("sin", "Stores input in dspace at accumulator", 0);        
        }
        /**
         * Functor to execute the input.
         */
	public void opCode(GPVM g) {
                System.out.print("?> ");
                g.setDSpace(g.getAcc(), new java.util.Scanner(System.in).nextLine());
                //g.setDSpace(g.getAcc(), System.console().readLine("?> "));
	}
}