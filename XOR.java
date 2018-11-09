package jjp.gpvm;
/**
 * Opcode representing pop the top two items from the stack and push their bitwise xor:  tempA = stack[sp]; tempB = stack[sp+1]; stack[sp++] = tempB^tempA;  
 */	
public class XOR extends AbstractOpCode {

	public XOR() {
		super("xor", "Bitwise xor",0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opCode(GPVM g) {
		g.push(g.pop()^g.pop());

	}

}
