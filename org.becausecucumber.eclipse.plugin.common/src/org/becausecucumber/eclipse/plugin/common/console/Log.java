package org.becausecucumber.eclipse.plugin.common.console;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.MessageConsoleStream;

public class Log {

	private static MessageConsoleStream consoleStream; 
	//private static CucumberConsoleFactory cf = new CucumberConsoleFactory(); 
	/*
	 * active the Log console on startup plugin
	 */
	/*public void openConsole(){         		
		//CucumberConsoleFactory.getConsole().setWaterMarks(5000, 8000);
		//cf.openConsole(); 
		
	}
	
	public void removeConsole(){         
		CucumberConsoleFactory.closeConsole();
		
	}*/
	
	/*
	 * http://www.eclipse.org/forums/index.php/t/172855/
	 * The color isn't changed because the setBackground or setColor raise an "Invalid thread access" exception because trying to access the UI thread and then the color isn't changed. The calls should be done synchronized with the UI thread in the following way:
	 */
    public static void info(final String _message){    
        Display.getDefault().asyncExec(new Runnable(){    
            @Override    
            public void run() {  
            	
                consoleStream = CucumberConsoleFactory.getConsole().newMessageStream();  
                String message=new SimpleDateFormat("[MMM/dd/yyyy EEE HH:mm:ss a").format(new Date())+ "(INFO)]:" +     
                        " " + _message;
               
                consoleStream.setColor(new Color(null,10,10,10));
                consoleStream.println(message);
                
                writeLogFile(message);
            }    
        });    
    }    
    public static void warning(final String _message){    
        Display.getDefault().asyncExec(new Runnable(){    
            @Override    
            public void run() {  
            	
                consoleStream = CucumberConsoleFactory.getConsole().newMessageStream();  
                String message=new SimpleDateFormat("[MMM/dd/yyyy EEE HH:mm:ss a").format(new Date())+ "(WARNING)]:" +     
                        " " + _message;
                consoleStream.setColor(new Color(null,102,51,0));
                consoleStream.println(message);
                
                writeLogFile(message);
            }    
        });    
    }    
    public static void output(final String _message){    
        Display.getDefault().asyncExec(new Runnable(){    
            @Override    
            public void run() {  
            	
                consoleStream = CucumberConsoleFactory.getConsole().newMessageStream();  
                consoleStream.setColor(new Color(null,0,0,255)); 
                
                consoleStream.println(_message); 
                
                writeLogFile(_message);
            }    
        });    
    }    
    public static void error(final String _message){    
        Display.getDefault().asyncExec(new Runnable(){    
            @Override    
            public void run() {    
                consoleStream = CucumberConsoleFactory.getConsole().newMessageStream();    
                consoleStream.setColor(new Color(null,255,0,0));  
                String message=new SimpleDateFormat("[MMM/dd/yyyy EEE HH:mm:ss a").format(new Date())+ "(ERROR)]:" +     
                        " " + _message;
                consoleStream.println(message); 
                writeLogFile(message);
            }           
        });    
    }  
    
    public static void writeLogFile(String _message){
    	 String logfile=System.getProperty("user.home")+File.separator+"cucumber"+new SimpleDateFormat("_yyyyMMddkk").format(new Date())+".log";
         
         FileWriter writer = null;
         try {
             File logs = new File(logfile);
		     if(!logs.exists()){					
				 logs.createNewFile();											
             }
			   
		     writer= new FileWriter(logs,true);
			 writer.write(_message+"\n");
         } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					if(writer!=null){
					  writer.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
    }
}
