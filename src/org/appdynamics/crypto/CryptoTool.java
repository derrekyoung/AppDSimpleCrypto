/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.appdynamics.crypto;

import java.io.Console;

/**
 *
 * @author gilbert.solorzano
 */
public class CryptoTool {
    /*
     *  This is going to be the password for the key, the password starts after the first 8 characters
     * and extends for 10 characters. To change it, change the following characters to your own characters
     *                   @m1C5!t00L
     */
    
    private static final String myKey="a3ApPD4H@m1C5!t00L"; //CHANGE THIS
    
    public static void main(String[] args){
        System.out.println("This utility requires a password for it to work.\nPlease enter the tool's password.\n");
        char[] ipass=null;
        Console console=System.console();
        if(console == null){
            System.out.println("Unable to get console, this program will exit now.");
            System.exit(1);
        }
        //System.out.println("The pass is " + myKey.substring(8,myKey.length()));
        

        
        ipass = console.readPassword("[Please input your password]: ");
        StringBuilder bud=new StringBuilder();
        if(ipass != null){   
                for(int i=0; i < ipass.length;i++){
                    bud.append(ipass[i]);                    
                }
        }
        
	if(bud.toString().compareTo(myKey.substring(8,myKey.length())) !=  0){  
            System.out.println("The incorrect password was provided, exiting.");
            System.exit(1);   
        }
        
        boolean valid=true;
        int count=0;
        while(valid){
            String msg="Please enter the string to encrypt";
            ipass = console.readPassword("[Please enter the string to encrpyt]: ");
            bud=new StringBuilder();
            StringBuilder bud1=new StringBuilder();
            if(ipass != null){   
                for(int i=0; i < ipass.length;i++){
                    bud.append(ipass[i]);                    
                }
            }
            ipass = console.readPassword("[Please re-enter the string to encrpyt]: ");
            if(ipass != null){   
                for(int i=0; i < ipass.length;i++){
                    bud1.append(ipass[i]);                    
                }
            }
            if(bud1.toString().compareTo(bud.toString()) == 0){
                valid=false;
                //StringLogger apmString = new StringLogger("ApMIntElKEY".getBytes(),"ySoecKby".getBytes());
                StringLogger apmString=getStringLogger();
                
                //System.out.println("The length " + bud1.toString().length() + " the strings " + bud1.toString());
                try{
                    System.out.println("This is the encrypted value:\n" + apmString.toUpper1(bud1.toString()) );
                }catch(Exception e){
                    System.out.println("Exception occurred " + e.getMessage());
                }
                        
            }else{
                if(count < 4){System.out.println("The strings don't match, please try again.");}
                else{ System.out.println("The number of tries have been exceeded, exiting.");valid=false;}
            }
            count++;
        }
                
    }
    /**
     * <p>
     *  Please change the characters in the keys without changing the length, these will be the keys that 
     * will encrypt your strings. 
     * </p>
     */
    public static StringLogger getStringLogger(){
        StringLogger apmString = new StringLogger("ApMIntElKEY".getBytes(),"ySoecKby".getBytes());
        return apmString;
    }
    
}
