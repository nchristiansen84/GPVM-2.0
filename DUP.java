package jjp.gpvm;
/**
 * Opcode representing duplicate the top of the stack:  temp = stack[sp]; stack[--sp] = temp;
 */
public class DUP extends AbstractOpCode{
	
	public DUP() {
		super("dup", "Duplicate",0);
	}

	@Override
	public void opCode(GPVM g) {
		g.push(g.peek());

	}

}
