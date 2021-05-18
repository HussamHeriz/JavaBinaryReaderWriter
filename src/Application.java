public class Application {
    
    public static void main(String[] args) {
        /* Read file and write it as integer to output.txt */
        BinaryRW brw = new BinaryRW("files/sakhra.jpg", 4);
        
        /* Read output.txt and write it to output.extension */
        IntegerToFile itf = new IntegerToFile("files/output.txt", 4);
    }
    
}
