package jjp.gpvm;
/**
* Opcode S2I converts a string in the dspace at the value in the 
* accumulator to an int, truncating if a double and stores it in 
* dspace at the value in the accumulator.
* 	Pre: dspace[ac] is valid string
* 	Post: dspace[ac] is an int of the string
* @author  The-Opcodes
* @version 1.0
* @since   March 2016  
*/
public class S2I extends AbstractOpCode{
        /**
         * Converts string from the dspace to an int and stores 
         * it in accumulator
         */
        public S2I(){
                super("s2i", "Converts string to int", 0);        
        }
        /**
         * Functor to execute the conversion of string to int.
         */
	public void opCode(GPVM g) {
                String temp = g.getDSpace(g.getAcc());
                boolean isFP = false;
                for(int i=0; i<temp.length(); i++){
                    if(temp.charAt(i)=='.') {
                        g.setDSpace(g.getAcc(), temp.substring(0, i)+"");
                        isFP = true;
                        break;
                    }
                }
                if(!isFP) g.setDSpace(g.getAcc(), temp);
	}
}