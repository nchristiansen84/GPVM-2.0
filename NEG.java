package jjp.gpvm;
/**
 * Opcode representing pop the stack and push the value multiplied by -1: stack[sp] = -stack[sp];
 */
public class NEG extends AbstractOpCode {

	public NEG() {
		super("neg", "Negatation",0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opCode(GPVM g) {
		g.push(-g.pop());

	}

}
