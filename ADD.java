package jjp.gpvm;

/**
 * A class to support an opcode representing: 
 * pop the top two items from the stack, add the first 
 * to the second and push the result: 
 * 		tempA = stack[sp]; 
 * 		tempB = stack[sp+1]; 
 * 		stack[sp++] = tempB-tempA;
 * @author Jim Perry
 * @since July 2012
 *
 */
public class ADD extends AbstractOpCode {
	/**
	 * constructs an add opcode.
	 */
	public ADD(){
		super("add", "Addition", 0);
	}

/**
 * Functor to execute the add.
 */
	public void opCode(GPVM g) {
            int a = g.pop();
            int b = g.pop();
            long sum = a+b;
            if(sum-a!=b){
                System.out.println("Overflow During Calculation Process Aborted");
                g.terminate();
            }
            else g.push((int)sum);
	}

}
