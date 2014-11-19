package cn.edu.seu.dbtest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Scanner;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.jdom2.*;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import cn.edu.seu.dbtest.*;

/**
 * A class that demonstrates Bluetooth communication between server mode PC and
 * client mode device through serial port profile.
 * 
 * @see <a href="http://sourceforge.net/projects/bluecove/">BlueCove - JSR-82
 *      implementation</a>
 */
public class PCServerCon {

	

	/*-
	 * 
	 * ---- Bluetooth attributes ----
	 */

	// serial port profile
	protected String UUID = new UUID("1101", true).toString();

	protected int discoveryMode = DiscoveryAgent.GIAC; // no paring needed
	/*-
	 * 
	 * ---- Connection handling attributes ----
	 */
	protected int endToken = 255;

	public PCServerCon() {
		try {
			LocalDevice device = LocalDevice.getLocalDevice();
			device.setDiscoverable(DiscoveryAgent.GIAC);

			String url = "btspp://localhost:" + UUID + ";name=PCServerCOMM";

			StreamConnectionNotifier notifier = (StreamConnectionNotifier) Connector
					.open(url);

			serverLoop(notifier);

		} catch (Throwable e) {
			log(e);
		}
	}

	private void serverLoop(StreamConnectionNotifier notifier) {
		try {
			while (true) { // infinite loop to accept connections.
				System.out.println("waiting fo connection");
				StreamConnection conn=notifier.acceptAndOpen();
				RemoteDevice rd=RemoteDevice.getRemoteDevice(conn);
				System.out.println("new client "+rd.getFriendlyName(false));
				System.out.println("mac "+rd.getBluetoothAddress());
				handleConnection(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean handleConnection(StreamConnection conn) throws IOException, ClassNotFoundException, SQLException, XMLStreamException, TransformerException {
		OutputStream out = conn.openOutputStream();
		InputStream in = conn.openInputStream();
		while(true)
		{
		byte[] buffer=new byte[16];
		System.out.println("正在读长度长度");
		in.read(buffer);
		if(!new String(buffer).equals(null))
		{
		int counter=0;
		for(;counter<16;counter++)
			if(buffer[counter]!='\0')
				break;
		byte[] length=new byte[16-counter];
		for(int con=0;con<16-counter;con++)
			length[con]=buffer[counter+con];
		String Str_Len=new String(length);
		int xml_len=0;
		try
		{
			xml_len=Integer.valueOf(Str_Len);
		}
		catch(NumberFormatException n)
		{
			n.getStackTrace();
			return false;
		}
		byte[] xml_buf=new byte[xml_len];
		in.read(xml_buf);
//			if((length=in.read(buffer))!=-1)
//			{
//				int counter=0;
//				for(;counter<templength;counter++)
//					xml_buf[length+counter]=buffer[counter];
//				length+=8;
//			}
		byte[] new_xml_buf=new byte[xml_len];
		System.arraycopy(xml_buf, 0, new_xml_buf,0,xml_len);
		System.out.println("\n"+new String(new_xml_buf));
		try {
		Xml_Parse input_xml_deal=new Xml_Parse(xml_buf);
		input_xml_deal.Xml_deal(out);
	} catch (JDOMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		}
		else
			return false;
		}
	}
	
	
	private void log(Throwable e) {
		e.printStackTrace();
	}

	/*-
	 *   -------  Bootstrap section, i.e., main method -------
	 */
}
