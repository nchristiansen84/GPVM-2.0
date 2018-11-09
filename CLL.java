package jjp.gpvm;
/**
 * Opcode providing subroutine call linkage: stack[--sp]=pc+2 pc = storage[pc+1]
 */
public class CLL extends AbstractOpCode {

	/**
	 * constructs subroutine call linkage opcode.
	 */
	public CLL(){
		super("cll", "Subroutine Linkage Call",1);
	}

	/**
	 * functor .
	 */
	@Override
	public void opCode(GPVM g) {
		g.pushPC();
		g.setPC();
	}

}
