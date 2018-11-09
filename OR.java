package jjp.gpvm;
/**
 * Opcode representing pop the top two items from the stack and push their bitwise or tempA = stack[sp]; tempB = stack[sp+1]; stack[sp++] = tempB|tempA;  
 */	
public class OR extends AbstractOpCode {

	public OR() {
		super("or",  "Bitwise or",0);
	}

	@Override
	public void opCode(GPVM g) {
		g.push(g.pop()|g.pop());
	}

}
