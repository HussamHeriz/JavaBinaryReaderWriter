import java.io.*;

public class BinaryRW {
    
    String fileName; // name of file to read from
    int numOfBits; // number of bits to read each time
    
    public BinaryRW(String fileName, int numOfBits) {
        this.fileName = fileName;
        this.numOfBits = numOfBits;
        read();
    }
    
    /* 
    read file number of bits specified each time,
    then write to output.txt file each specified number of bits
    as integer number
    */
    public void read() {
        String delimiter = " ";
        if(Application.delimiter == 1) {
            delimiter = "\n";
        }
        boolean[] bitArray = new boolean[numOfBits];
        try {
            
            File file = new File(fileName);
            BitInputStream bis = new BitInputStream(new FileInputStream(file));
            System.out.println(fileName+" has opened successfully");
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/numbers.txt"));
            
            String extension = getFileExtension();
            bw.write(extension+"\n"); // write file extension at first line of file
            
            System.out.println("Now, writing to numbers.txt file");
            
            // Loop through size of file in bits
            long sizeOfFileInBits = file.length()*8;
            int writtenNumbers = 0;
            for(long i=0; i<sizeOfFileInBits; ++i){
                int modVal = (int) (numOfBits - 1 - i%numOfBits); 
                bitArray[modVal] = bis.read(); // fill array to write into output
                if(modVal == 0) { // when buffer and array is full, then write to file
                    int output = booleanToInteger(bitArray);
                    // Add one to output number
                    output++;
                    bw.write(output+delimiter);
                    writtenNumbers++;
                    if(writtenNumbers % 20 == 0) {
                        bw.write("\n");
                        writtenNumbers = 0;
                    }
                }
            }
            bis.close();
            bw.close();
            System.out.println("Finished writing to output.txt");
            
        } catch (Exception ex) {
            System.out.println("Error: "+ex.getMessage());
        }
    }
    
    // Change boolean array to integer
    public int booleanToInteger(boolean[] arrValues) {
        int n = 0, l = arrValues.length;
        for (int i = 0; i < l; ++i) {
            n = (n << 1) + (arrValues[i] ? 1 : 0);
        }
        return n;
    }
    
    // Get extension of file as the last segment of filename
    public String getFileExtension() {
        String[] splittedArray = fileName.split("\\.");
        return splittedArray[splittedArray.length-1];
    }
    
}
