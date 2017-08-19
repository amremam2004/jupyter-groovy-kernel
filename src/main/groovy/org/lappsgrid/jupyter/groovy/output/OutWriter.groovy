package org.lappsgrid.jupyter.groovy.output;


import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import org.lappsgrid.jupyter.groovy.output.OutPublisher;

import org.lappsgrid.jupyter.groovy.msg.Message
import org.lappsgrid.jupyter.groovy.msg.Header
import org.apache.commons.io.output.WriterOutputStream;
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import static org.lappsgrid.jupyter.groovy.msg.Message.Type.*

class WRT extends OutputStream{
    protected Logger logger
	OutPublisher call;
	public WRT(OutPublisher call){
        logger = LoggerFactory.getLogger(WRT)
		this.call=call;
	}
	byte[] buf= new char[8192];
	int idx=0;
	int len=0;
	@Override
	public void close() throws IOException {
        //logger.info("### close")
		// TODO Auto-generated method stub
		
	}

	@Override
	public void flush() throws IOException {
        //logger.info("### Flush")
	
		Message message = call.getLastMessage();
        Message reply = new Message()
        reply.header = new Header(STREAM, message)
        reply.parentHeader = message.header
        reply.identities = message.identities
            reply.content = [
                    'name': "stdout",
                    'text': new String(buf, idx, len),
            ]
        idx=0;
        len=0;
        //logger.info("### publish")
        call.publish(reply)
		
	}
	@Override
	public void write(int b) throws IOException{
		buf[this.len]=(byte)b;
		this.len++;
		if(this.len > 1024){
		flush();
		} 
	}
	
	@Override
	public void write(byte[] src, int off, int len) throws IOException {
        //logger.info("### write")
		System.arraycopy(src, off, buf, this.len, len);
		this.len+=len;
		if(this.len > 1024){
		flush();
		} 
		// TODO Auto-generated method stub
		
	}
};

public class OutWriter extends PrintStream {
	public static PrintStream CreateWriter(OutPublisher call){
		WRT wrt=new WRT(call);
		OutWriter wrt1 = new OutWriter(wrt);
		//OutputStream os = new WriterOutputStream(wrt1);
		//PrintStream ps = new PrintStream(os);
		return wrt1;
	}
	private OutWriter(OutputStream wrt) {
		super(wrt, true);
		// TODO Auto-generated constructor stub
	}



}
