package jjp.gpvm;
/**
* Opcode I2S converts an int in the dspace to a string and 
* stores it in the dspace at the value in the accumulator.
* 	Pre: dspace[ac] is valid int representation
* 	Post: dspace[ac] will equal a string of what the int represented.
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class I2S extends AbstractOpCode{
        /**
         * Converts an int from the dspace to a string and stores it 
         * in the dspace
         */
        public I2S(){
                super("i2s", "Converts int to string", 0);        
        }
        /**
         * Functor to execute the conversion of int to string.
         */
	public void opCode(GPVM g) {
                g.setDSpace(g.getAcc(), g.getDSpace(g.getAcc()));
	}
}