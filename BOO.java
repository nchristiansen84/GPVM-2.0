package jjp.gpvm;
/**
 * Opcode representing pop the stack and push true if the value is non-zero and false otherwise: if(stack[sp]!=0)stack[sp]=1;else stack[sp]=0;
 *  @author Jim Perry
 * @since July 2012
 *
 */
public class BOO extends AbstractOpCode {
	/**
	 * Contructs the Boolean conversion op code.
	 */
	public BOO(){
		super("boo", "Convert to Boolean",0);
	}

	/**
	 * Functor for Boolean conversion.
	 */
	@Override
	public void opCode(GPVM g) {
		if(g.pop()!=0){
			g.push(1);
		}
		else{
			g.push(0);
		}

	}

}
