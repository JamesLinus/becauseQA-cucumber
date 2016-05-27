package org.becausecucumber.eclipse.plugin.common.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class CucumberConsoleFactory implements IConsoleFactory {

	public static MessageConsole console;
	
	@Override
	public void openConsole() {
		// TODO Auto-generated method stub
		showConsole(); 
		
	}

	public static void showConsole() { 
       // if (console != null) { 
            final IConsoleManager manager = ConsolePlugin.getDefault() 
                    .getConsoleManager(); 
            IConsole[] existing = manager.getConsoles(); 
            boolean exists = false; 
            int consoleindex=0;
            for (int i = 0; i < existing.length; i++) {
            	 
            	 System.out.println("all console is:"+existing[i].getName());
            	 if ("Studio Console".equals(  existing[i].getName())) {
            		 console=(MessageConsole) existing[i];
            		 manager.removeConsoles(new IConsole[] { console }); 
                 }
                if ("Cucumber Console".equals(  existing[i].getName())) {
                	
                    exists = true; 
                    consoleindex= i;
                    break;
                }
            } 
            if (!exists) { 
            	console=new MessageConsole( 
                        "Cucumber Console", null); 
            	//console.createPage(view);
            	
            }else{
            	console=(MessageConsole) existing[consoleindex];
            }
            
          //console.s
            Display.getDefault().syncExec(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					console.setBackground(new Color(null,255,255,255));
		            
			}});
            manager.addConsoles(new IConsole[] { console }); 
            manager.showConsoleView(console); 
             
           // console.setWaterMarks(2000, -1);
            MessageConsoleStream stream = console.newMessageStream(); 
           // stream.setColor(new Color(null,5,5,5));
            stream.setActivateOnWrite(true);
            
            try {
         
            	String logfile=System.getProperty("user.home")+File.separator+"cucumber"+new SimpleDateFormat("_yyyyMMddkk").format(new Date())+".log";
				File logs = new File(logfile);
				if(!logs.exists()){
					logs.createNewFile();
                }
            	System.setOut(new PrintStream(new FileOutputStream(logfile)));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
       // } 
            catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    } 
 
    public static void closeConsole() { 
        IConsoleManager manager = ConsolePlugin.getDefault() 
                .getConsoleManager(); 
        if (console != null) { 
            manager.removeConsoles(new IConsole[] { console }); 
        } 
    } 
 
    public static MessageConsole getConsole() {
    	//if(console==null){
    		showConsole();
    	//}
    	//System.out.println("Console objct is:"+console);
        return console; 
    } 

}
