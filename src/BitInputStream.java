import java.io.IOException;
import java.io.InputStream;

class BitInputStream {

    private InputStream in; // file to be read
    private int num = 0; // current number as read (byte)
    private int count = 8; // index of array to fill buffer
 
    public BitInputStream(InputStream in) {
        this.in = in;
    }

    // read from file bit by bit, after reading 8 bits,
    // then read another byte from source file
    public boolean read() throws IOException {
        if (this.count == 8){
            this.num = this.in.read();
            this.count = 0;
        }

        boolean x = (num%2 == 1);
        num /= 2;
        this.count++;

        return x;
    }

    // close file and flush buffer
    public void close() throws IOException {
        this.in.close();
    }

}