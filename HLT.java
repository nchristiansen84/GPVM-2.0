package jjp.gpvm;
/**
 * Opcode representing end execution
 */
public class HLT extends AbstractOpCode {

	public HLT() {
		super("hlt", "Halt",0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opCode(GPVM g) {
		g.terminate();

	}

}
