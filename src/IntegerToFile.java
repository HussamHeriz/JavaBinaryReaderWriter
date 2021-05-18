import java.io.*;

public class IntegerToFile {
    
    String fileName; // file of integers
    int numOfBits; // number of bits to write
    String extension; // extension of file
    String outputFile; // output file name
    
    public IntegerToFile(String fileName, int numOfBits) {
        this.fileName = fileName;
        this.numOfBits = numOfBits;
        convert();
    }
    
    /*
    Convert integer numbers line by line,
    and convert each number to equivilant number of
    bits specified in the attribute above
    */
    public void convert() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
            String line = br.readLine();
            extension = line;
            outputFile = "files/output."+extension;
            System.out.println("Started converting "+fileName+" to "+outputFile);
            BitOutputStream bos = new BitOutputStream(new FileOutputStream(outputFile));
            while ((line = br.readLine()) != null ) {
                int num = Integer.parseInt(line);
                int count = numOfBits;
                while(num != 0) {
                    boolean x = (num%2 == 1);
                    bos.write(x);
                    num /= 2;
                    count--;
                }
                while(count != 0) { // fix at the end when no more values
                    bos.write(false);
                    count--;
                }
            }
            
            br.close();
            bos.close();
            System.out.println("Finished converting");
            
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
}
