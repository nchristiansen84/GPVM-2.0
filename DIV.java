package jjp.gpvm;
/**
 * Opcode representing pop the top two items in the stack, divide the second by the first and push the result:  tempA = stack[sp]; tempB = stack[sp+1]; stack[sp++] = tempB/tempA;
 */	
public class DIV extends AbstractOpCode {

	public DIV() {
		super("div", "Division",0);

	}

	@Override
	public void opCode(GPVM g) {
			int temp = g.pop();
			g.push(g.pop()/temp);

	}

}
