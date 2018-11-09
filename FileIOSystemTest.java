
package jjp.gpvm;

/**
 * @author Brian
 */

/*
* SystemTest allowing for a file to be run as assembly.
*/
public class FileIOSystemTest {
    public static void main(String[] args) {
        String fileName = "Demo1.txt";        //Arithmatic Overflow 3*1008987878=Overflow
        //String fileName = "Demo2.txt";        //Label x=400 (x+x)/8=100
        //String fileName = "Demo3.txt";        //Float 2635.69
        //String fileName = "Demo4.txt";        //Float addition -69+10.45=-58.55
        //String fileName = "Demo5.txt";        //Negate Float -(-60.234)=60.234
        InputOutput.inputFile(fileName);
    }
}
