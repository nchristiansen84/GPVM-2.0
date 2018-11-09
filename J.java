package jjp.gpvm;
/**
 * Opcode representing a unconditional jump: pc = storage[pc+1]
 */
public class J extends AbstractOpCode {

	public J() {
		super("j",   "Unconditional Jump",1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opCode(GPVM g) {
		g.setPC();

	}

}
