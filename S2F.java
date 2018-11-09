package jjp.gpvm;
/**
* Opcode S2F converts a string in the dspace at the value in the 
* accumulator to a fixed point and store the entire number back in 
* the dspace at the value of the accumulator and store the location 
* of the decimal point from the right side of the number in the 
* dspace at the value of the accumulator+1. 
*	Pre: dspace[ac] is valid string of a fixed point number
*               dspace[ac+1] is unused 
*	Post: dspace[ac] will equal the entire number without the 
*               decimal point and dspace[ac+1] will equal the location 
*               of the decimal point from the right side of the number
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class S2F extends AbstractOpCode{
        /**
         * Converts a string in the dspace to a fixed point and stores 
         * it in the dspace.
         */
       public S2F(){
                super("s2f", "Converts string to fixed point", 0);        
        }
        /**
         * Functor to execute the conversion of string to fixed point.
         */
	public void opCode(GPVM g) {
                String temp = g.getDSpace(g.getAcc());
                for(int i=0; i<temp.length(); i++){
                    if(temp.charAt(i)=='.'){
                        g.setDSpace(g.getAcc(), temp.substring(0, i)+temp.substring(i+1));
                        g.setDSpace(g.getAcc()+1, temp.length()-(i+1)+"");
                        break;
                    }
                }
	}
}