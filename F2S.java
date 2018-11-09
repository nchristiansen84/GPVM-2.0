package jjp.gpvm;
/**
* Opcode F2S converts a fixed point in the dspace at the 
* value in the accumulator and value in the accumulator+1 
* to a string and store it back in the dspace at the value 
* in the accumulator. 
*	Pre: dspace[ac] is valid entire number without decimal 
*               point and dspace[ac+1] is valid decimal location 
*               from right side of the number
*	Post: dspace[ac] will equal a string of what the fixed 
*               point represented
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class F2S extends AbstractOpCode{
        /**
         * Converts a fixed point in the dspace to a string and stores 
         * it in the dspace.
         */
       public F2S(){
                super("f2s", "Converts fixed point to string", 0);
        }
        /**
         * Functor to execute the conversion of fixed point to string.
         */
	public void opCode(GPVM g) {
                String temp = g.getDSpace(g.getAcc());
                int temp2 = temp.length()-Integer.parseInt(g.getDSpace(g.getAcc()+1));
                g.setDSpace(g.getAcc(), temp.substring(0, temp2)+"."+
                        temp.substring(temp2));
                g.setDSpace(g.getAcc()+1, 0+"");
	}
}