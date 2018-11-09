package gpvm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Brian
 */

/*
 * Tests integer overflow from arithmatic operations   
 */
public class OverflowTest {
    // number of passed tests
    static int passed = 0;
    
    // number of tests attempted
    static int att = 0;
    
    public static void runTest(String src, int exp, String caseNum) throws FileNotFoundException, UnsupportedEncodingException{
        
        System.out.println("Test ID: "+caseNum);
        att++;
        
        String fileName = "test.txt";
        
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.print(src);
        writer.close();
        
        String [] prg = GPVMAssm.read(fileName);
        GPVM gPVM = new GPVM();
                
        gPVM.addOpCode(new ADD());
        gPVM.addOpCode(new ADDF());
        gPVM.addOpCode(new AND());
        gPVM.addOpCode(new BOO());
        gPVM.addOpCode(new CLL());
        gPVM.addOpCode(new CONCAT());
        gPVM.addOpCode(new DIV());
        gPVM.addOpCode(new DIVF());
        gPVM.addOpCode(new DPSH());
        gPVM.addOpCode(new DUP());
        gPVM.addOpCode(new F2I());
        gPVM.addOpCode(new F2S());
        gPVM.addOpCode(new HLT());
        gPVM.addOpCode(new I2F());
        gPVM.addOpCode(new I2S());
        gPVM.addOpCode(new J());
        gPVM.addOpCode(new JP());
        gPVM.addOpCode(new JZ());
        gPVM.addOpCode(new LD());
        gPVM.addOpCode(new LDC());
        gPVM.addOpCode(new LDF());
        gPVM.addOpCode(new LDI());
        gPVM.addOpCode(new LDR());
        gPVM.addOpCode(new LDS());
        gPVM.addOpCode(new MOD());
        gPVM.addOpCode(new MUL());
        gPVM.addOpCode(new MULF());
        gPVM.addOpCode(new NEG());
        gPVM.addOpCode(new NEGF());
        gPVM.addOpCode(new NOT());
        gPVM.addOpCode(new OR ());
        gPVM.addOpCode(new POP());
        gPVM.addOpCode(new PSH());
        gPVM.addOpCode(new RET());
        gPVM.addOpCode(new S2F());
        gPVM.addOpCode(new S2I());
        gPVM.addOpCode(new SGN());
        gPVM.addOpCode(new SIN());
        gPVM.addOpCode(new SOUT());
        gPVM.addOpCode(new ST());
        gPVM.addOpCode(new STF());
        gPVM.addOpCode(new STI());
        gPVM.addOpCode(new STR());
        gPVM.addOpCode(new STS());
        gPVM.addOpCode(new SUB());
        gPVM.addOpCode(new SUBF());
        gPVM.addOpCode(new XOR());
        
        GPVMAssm assm = new GPVMAssm(gPVM);
        
        int []obj = assm.assemble(prg);
        
        int act = Integer.parseInt(gPVM.calculate(obj, new int[10], new String[10], 100, "-1"));
        
        System.out.print("Expected: "+exp+" Actual: "+act+" Result: ");
        if(exp==act){
            System.out.println("PASSED");
            passed++;
        }
        else System.out.println("FAILED");
        
        System.out.println(" ");
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.println("");
        
        String test1 = "dpsh \n 2147483647 \n 1 \n add \n hlt";
        //runTest(test1, 0, "1");
        
        String test2 = "dpsh \n -2147483648 \n -1 \n add \n hlt";
        //runTest(test2, 0, "2");
        
        String test3 = "dpsh \n 2147483647 \n 2 \n mul \n hlt";
        //runTest(test3, 0, "3");
        
        String test4 = "dpsh \n 1 \n -2147483647 \n sub \n hlt";
        runTest(test4, 0, "4");
    }
    
}
