
import java.util.Scanner;

public class Application {
    
    public static int delimiter;
    
    public static void main(String[] args) {
        
        // Choose Delimiter
        Scanner scanner = new Scanner(System.in);
        while(delimiter != 1 && delimiter != 2) {
            System.out.print("Choose delimiter:\n1. to use enter, 2. to use space: ");
            delimiter = scanner.nextInt();  
        }
        
        /* Read file and write it as integer to output.txt */
        BinaryRW brw = new BinaryRW("files/sakhra.jpg", 4);
        
        /* Read output.txt and write it to output.extension */
        IntegerToFile itf = new IntegerToFile("files/numbers.txt", 4);
    }
    
}
