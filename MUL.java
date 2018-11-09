package jjp.gpvm;
/**
 * Opcode representing pop the top two items in the stack, multiple them and push the result:  tempA = stack[sp]; tempB = stack[sp+1]; stack[sp++] = tempA *tempB;
 */
public class MUL extends AbstractOpCode {

	public MUL() {
		super("mul", "Multiplication",0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opCode(GPVM g) {
		g.push(g.pop()*g.pop());

	}

}
