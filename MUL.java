package gpvm;
/**
 * Opcode representing pop the top two items in the stack, multiple them and push the result:  tempA = stack[sp]; tempB = stack[sp+1]; stack[sp++] = tempA *tempB;
 */
public class MUL extends AbstractOpCode {

	public MUL() {
		super("mul", "Multiplication",0);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void opCode(GPVM g) {
            int a = g.pop();
            int b = g.pop();
            long prod = a*b;
            if(a!=0&&prod/a!=b){
                System.out.println("Overflow During Calculation Process Aborted");
                System.exit(0);
            }
            else g.push((int)prod);

	}

}
