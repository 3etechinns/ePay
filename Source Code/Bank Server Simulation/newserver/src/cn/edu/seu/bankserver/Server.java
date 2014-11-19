package cn.edu.seu.bankserver;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.sql.SQLException;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

import org.jdom2.JDOMException;

public class Server {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, XMLStreamException, TransformerException {
		// TODO Auto-generated method stub
			try{
				ServerSocket Citi_Ser=new ServerSocket(30145);
				while(true)
				{
					Socket Citi_Cli=Citi_Ser.accept();
					System.out.println("received!");
					InputStream in=Citi_Cli.getInputStream();
					OutputStream out=Citi_Cli.getOutputStream();
					byte[] buffer=new byte[16];
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
						continue;
					}
					byte[] xml_buf=new byte[xml_len];
					in.read(xml_buf);
//						if((length=in.read(buffer))!=-1)
//						{
//							int counter=0;
//							for(;counter<templength;counter++)
//								xml_buf[length+counter]=buffer[counter];
//							length+=8;
//						}
					byte[] new_xml_buf=new byte[xml_len];
					System.arraycopy(xml_buf, 0, new_xml_buf,0,xml_len);
					System.out.println("\n"+new String(new_xml_buf));
					try {
					Xml_Parse input_xml_deal=new Xml_Parse(xml_buf);
					input_xml_deal.Xml_deal(out);
					Citi_Cli.close();
				} catch (JDOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					}
					else
						Citi_Cli.close();
					continue;
			}
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}

	}
}

