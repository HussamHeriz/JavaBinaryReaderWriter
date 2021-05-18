import java.io.IOException;
import java.io.OutputStream;

class BitOutputStream {

    private OutputStream out; // output file to write into
    private boolean[] buffer = new boolean[8]; // buffer array as boolean to write as byte
    private int count = 0; // number of written bits

    public BitOutputStream(OutputStream out) {
        this.out = out;
    }

    /*
    Fill buffer array with current bit, then
    after filling buffer array
    write a byte to file
    */
    public void write(boolean x) throws IOException {
        this.count++;
        this.buffer[8-this.count] = x;
        if (this.count == 8){
            int num = 0;
            for (int index = 0; index < 8; index++){
                num = 2*num + (this.buffer[index] ? 1 : 0);
            }

            this.out.write(num);

            this.count = 0;
        }
    }

    // close file and ensure to flush buffer
    public void close() throws IOException {
        if(count != 0) {
            int num = 0;
            for (int index = 0; index < 8; index++){
                num = 2*num + (this.buffer[index] ? 1 : 0);
            }

            this.out.write(num);
        }

        this.out.close();
    }

}
