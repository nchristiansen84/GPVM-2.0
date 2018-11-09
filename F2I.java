package jjp.gpvm;
/**
* Opcode F2I converts a fixed point in the dspace at the 
* value of the accumulator and value of the accumulator+1 
* to an int, possibly losing precision, and store it in the
* dspace at the value of the accumulator. 
*	Pre: dspace[ac] is valid entire number without decimal 
*               point and dspace[ac+1] is valid decimal location 
*               from right side of the number
*	Post: dspace[ac] will equal an int of what the fixed point 
*               represented with possible loss of precision, and
*               dspace[ac+1] will no longer be used
* @author  The-Opcodes
* @version 1.0
* @since   March 2016 
*/
public class F2I extends AbstractOpCode{
        /**
         * Converts a fixed point in the dspace to an int and stores 
         * it in the dspace.
         */
       public F2I(){
                super("f2i", "Converts fixed point to int", 0);
        }
        /**
         * Functor to execute the conversion of fixed point to int.
         */
	public void opCode(GPVM g) {
                String temp = g.getDSpace(g.getAcc());
                int temp2 = temp.length()-Integer.parseInt(g.getDSpace(g.getAcc()+1));
                g.setDSpace(g.getAcc(), temp.substring(0, temp2));
                g.setDSpace(g.getAcc()+1, 0+"");
	}
}