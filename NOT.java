package jjp.gpvm;
/**
 * Opcode representing pop the stack and push the complement of the value: stack[sp] = ~stack[sp]
 */
public class NOT extends AbstractOpCode {

	public NOT() {
		super("not", "Bitwise not",0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opCode(GPVM g) {
			g.push(~g.pop());
	}

}
