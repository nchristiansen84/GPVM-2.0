package jjp.gpvm;
/**
 * Opcode providing subroutine ret linkage: pc=stack[sp++]
 */
public class RET extends AbstractOpCode {

	public RET() {
		super("ret", "Subroutine Linkage Return",0);
	}

	@Override
	public void opCode(GPVM g) {
		g.popPC();
	}

}
