package jjp.gpvm;
/**
 * Opcode representing a conditional jump: if(stack[sp]>0)pc = storage[pc+1]
 */
public class JP extends AbstractOpCode {

	public JP() {
		super("jp",  "If positive Jump",1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opCode(GPVM g) {
		if (g.peek()>0){
			g.setPC();
		}
		else {
			g.getOperand();
		}

	}

}
