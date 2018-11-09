package jjp.gpvm;
/**
 * A class to support an opcode representing: pop the top two items from the stack, performs and bitwise AND and push the result: 
 * = stack[sp]; tempB = stack[sp+1]; stack[sp++] = tempB&tempA;
 * @author Jim Perry
 * @since July 2012
 *
 */
public class AND extends AbstractOpCode {
	/** 
	 * Constructs an and opcode.
	 */
	public AND(){
		super("and", "Bitwise and", 0);
	}

	/**
	 * Functor to execute the bitwise and.
	 */
	@Override
	public void opCode(GPVM g) {
		g.push(g.pop()&g.pop());
	}

}
