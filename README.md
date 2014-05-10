AppDSimpleCrypto
================

The AppDSimpleCrypto is a tool that allows the encryption of strings so that they 
can be added to configuration files, such as a password in a property file. The tool
provides a method to encrypt a string and decrypt the string. The developer must provide
new strings for tool password and hashing key. If these strings are not used then anyone
with access to the tool in git can use the default key values to unencrypt the string.

Disclaimer
----------
This tool is intended to be used with other AppDynamics extensions that require that a
password be placed in a file most time un-encrypted. The tool will provide an encrypted string 
that can be unencrypted with the same tool. The tool is meant to provide a simple level of security
and must not be considered the only level of security. If more security is needed then additional
ciphers should be used.


Requirements:
------------
* Ant 1.8 or above
* JDK 1.6 or above
* Apache's commons-codec-1.9 (provided)

Building:
--------
1. Fork the Repo to local machine

2. Using an editor open the file src/org/appdynamics/crypto/CryptoTool.java
   a)Follow the instructions on line 15-16 to change the string value on line 19, for example

    change this: 

        private static final String myKey="a3ApPD4H@m1C5!t00L"; //CHANGE THIS

    to this:

        private static final String myKey="a3ApPD4HMY-Secrets"; //CHANGE THIS

   b)Follow the instructions on line 86 to change the string values on line 91, for example

    change this:

      	StringLogger apmString = new StringLogger("ApMIntElKEY".getBytes(),"ySoecKby".getBytes());

    to this:

      	StringLogger apmString = new StringLogger("ACME1234KEY".getBytes(),"ySoecKby".getBytes());
   
3. Run ant -f AppD_build.xml

4. Add the execLib folder to your classpath for use with any of your projects

Usage:
-----
To use the tool is a two step process first you must use the tool to encrypt a
string. The tool OOTB requires a Unix/Linux terminal or Windows CMD window to 
execute. 

java -cp "execLib/*" org.appdynamics.crypto.CryptoTool

Example:
```sh
java -cp "execLib/*" org.appdynamics.crypto.CryptoTool
This utility requires a password for it to work.
Please enter the tool's password.

[Please input your password]: 
[Please enter the string to encrpyt]: 
[Please re-enter the string to encrpyt]: 
This is the encrypted value:
pO6R9GvVPVt65ftbckIX6hR6SesEFDtK
```

###To unencrypt the string use the StringLogger class for example:
```java
import org.appdynamics.crypto.*;

public class TestCypto {
    
    public static void main(String[] args){
        System.out.println("Hello");
        // String that was encrypted prior
        String enCrypt="pO6R9GvVPVt65ftbckIX6hR6SesEFDtK";

        StringLogger sl = CryptoTool.getStringLogger();
        try{
		String deCrypted=sl.toLower1(sl.format1(enCrypt));
	        System.out.println(deCrypted);
        }catch(Exception e){System.out.println("Exception " + e.getMessage());}
    }
    
}

```
