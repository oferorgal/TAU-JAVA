package il.ac.tau.cs.sw1.ex8.bufferedIO;

import java.io.FileWriter;
import java.io.IOException;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class MyBufferedWriter implements IBufferedWriter{
	
	private FileWriter fWriter;
	private String buffer;
	private int bufferSize;
	
	public MyBufferedWriter(FileWriter fWriter, int bufferSize){
		this.buffer = new String();
		this.bufferSize = bufferSize;
		this.fWriter = fWriter;
	}

	
	@Override
	public void write(String str) throws IOException {
		int len;
		String localBuff = new String(this.buffer);
		while(str.length() != 0){
			if(str.length() > this.bufferSize){ // check if the string length is longer then th buffer size
				len = this.bufferSize;
			}
			else{
				len = str.length(); // if not, set the length to the end of the string
			}
			
			if(localBuff.length() == 0){ // if buffer is empty write all len into it
				localBuff = str.substring(0, len);
				str = str.substring(len);
			}
			else{ // if: this.buffer != null
				if((localBuff.length() + len) > this.bufferSize){ // check if we can write all len into the buffer or we need to downsize len
					len = this.bufferSize - localBuff.length();
				}
				localBuff = localBuff.concat(str.substring(0, len));
				str = str.substring(len);
			}
			if(localBuff.length() == this.bufferSize){ // if buffer is full, write.
				//System.out.println("write");
				this.fWriter.write(localBuff);
				localBuff = ""; // clear local buff
			}
		}
		this.buffer = new String(localBuff); // when we are done, update buffer for later use
	}
	
	@Override
	public void close() throws IOException {
		if(this.buffer.length() != 0){
			//System.out.println("write");
			this.fWriter.write(this.buffer);
		}
		this.fWriter.close();
	}

}
