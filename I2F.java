package jjp.gpvm;
/**
* Opcode I2F converts an int in the dspace at the value of 
* the accumulator to a fixed point and store the entire number 
* dspace at the value of the accumulator and store the location 
* of the decimal point from the right side of the number in the 
* dspace at the value of the accumulator+1. 
*	Pre: dspace[ac] is valid int, dspace[ac+1] is unused 
*	Post: dspace[ac] will equal the entire number without the 
*               decimal point as an int and dspace[ac+1] will equal 
*               the location of the decimal point from the right 
*               side of the number
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class I2F extends AbstractOpCode{
        /**
         * Converts an int from the dspace to a fixed point and 
         * stores it in the dspace
         */
        public I2F(){
                super("i2f", "Converts int to fixed point", 0);        
        }
        /**
         * Functor to execute the conversion of int to fixed point. 12354
         */
	public void opCode(GPVM g) {
                String temp = g.getDSpace(g.getAcc());
                g.setDSpace(g.getAcc(), temp);
                g.setDSpace(g.getAcc()+1, 0+"");
	}
}