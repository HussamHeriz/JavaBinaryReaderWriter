import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IntegerToFile {
    
    String fileName; // file of integers
    int numOfBits; // number of bits to write
    String extension; // extension of file
    String outputFile; // output file name
    BitOutputStream bos;
    BufferedReader br;
    
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
            br = new BufferedReader(new FileReader(new File(fileName)));
            String line = br.readLine();
            extension = line;
            outputFile = "files/output."+extension;
            System.out.println("Started converting "+fileName+" to "+outputFile);
            bos = new BitOutputStream(new FileOutputStream(outputFile));
            
            if(Application.delimiter == 1) { // Enter Delimiter
                while ((line = br.readLine()) != null ) {
                    int num = Integer.parseInt(line);
                    writeInteger(num);
                }
            } else { // Space Delimiter
                line = br.readLine();
                
                Scanner numbers = new Scanner(line);
                int number;
                while( (number = numbers.nextInt()) != -1) {
                    writeInteger(number);
                }
            }
        } catch(NoSuchElementException ex) {}
        catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
        } finally {
            try {
                br.close();
                bos.close();
                System.out.println("Finished converting");
            } catch (IOException ex) {}
        }
            

    }
    
    private void writeInteger(int num) throws IOException {
        // Remove one from number
        --num;
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
}
