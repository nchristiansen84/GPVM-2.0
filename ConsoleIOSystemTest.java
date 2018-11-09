
package gpvm;

import java.util.Scanner;

/**
 * @author Brian
 */

/*
* SystemTest allowing user to enter assembly code.
*/
public class ConsoleIOSystemTest {
    public static void main(String[] args) {
        System.out.println("Type in assembly code here:");
        Scanner sc = new Scanner(System.in); 
        String src = sc.nextLine();
        System.out.println(InputOutput.inputConsole(src));
    }
}
