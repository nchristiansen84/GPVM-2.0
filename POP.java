package jjp.gpvm;
/**
 * Opcode representing pop from the stack:  sp++
 */
public class POP extends AbstractOpCode {

	public POP() {
		super("pop", "Pop",0);

	}

	@Override
	public void opCode(GPVM g) {
		g.pop();

	}

}
