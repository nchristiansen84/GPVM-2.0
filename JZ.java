package jjp.gpvm;
/**
 * Opcode representing a conditional jump: if(stack[sp]==0)pc = storage[pc+1]
 */
public class JZ extends AbstractOpCode {

	public JZ() {
		super("jz",  "If zero Jump",1);
	}

	@Override
	public void opCode(GPVM g) {
		if (g.peek()==0){
			g.setPC();
		}
		else {
			g.getOperand();
		}

	}

}
