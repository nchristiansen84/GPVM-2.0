package jjp.gpvm;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Brian
 */
public class AssmTest {
    
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
        gPVM.addOpCode(new AND());
        gPVM.addOpCode(new BOO());
        gPVM.addOpCode(new CLL());
        gPVM.addOpCode(new CONCAT());
        gPVM.addOpCode(new DIV());
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
        gPVM.addOpCode(new LD ());
        gPVM.addOpCode(new LDF());
        gPVM.addOpCode(new LDS());
        gPVM.addOpCode(new MOD());
        gPVM.addOpCode(new MUL());
        gPVM.addOpCode(new NEG());
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
        gPVM.addOpCode(new STS());
        gPVM.addOpCode(new SUB());			
        gPVM.addOpCode(new XOR());
        
        GPVMAssm assm = new GPVMAssm(gPVM);
        
        int []obj = assm.assemble(prg);
        
        int act = gPVM.calculate(obj, new int[10], 100);
        
        System.out.print("Expected: "+exp+" Actual: "+act+" Result: ");
        if(exp==act){
            System.out.println("PASSED");
            passed++;
        }
        else System.out.println("FAILED");
        
        System.out.println(" ");
    }
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        
        String test01 = "psh\n100\npsh\n10\nadd\nhlt";
        runTest(test01, 110, "01");
        
        String test02 = "dpsh\n1000\n10000\nsub\nhlt";
        runTest(test02, -9000, "02");
        
        String test03 = "dpsh\n0\n13\ndiv\nhlt";
        runTest(test03, 0, "03");
        
        String test04 = "psh\nOne: 1\npsh\nTwo: 2\nadd\nhlt";
        runTest(test04, 3, "04");
        
        String test05 = "psh\n10\npsh\n5\nmul\nhlt";
        runTest(test05, 50, "05");
        
        String test06 = "psh\n 21\n neg\nhlt";
        runTest(test06, -21, "06");
    
        System.out.println("---------------------------------------------------");
        System.out.println("Tests Run: "+att+" Tests Passed: "+passed);
        System.out.println("---------------------------------------------------");

    }
}
