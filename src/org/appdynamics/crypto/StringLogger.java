/* 

 */
package org.appdynamics.crypto;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//import org.apache.commons.codec.binary.Base64;
import java.util.logging.*;

public class StringLogger {

private Cipher deCipher;
private Cipher enCipher;
private SecretKeySpec key;
private IvParameterSpec ivSpec;

public final static Logger log=Logger.getLogger(StringLogger.class.getName());

public StringLogger(byte[] keyBytes,   byte[] ivBytes) {
    // wrap key data in Key/IV specs to pass to cipher
    
    
     ivSpec = new IvParameterSpec(ivBytes);
    // create the cipher with the algorithm you choose
    // see javadoc for Cipher class for more info, e.g.
    try {
         DESKeySpec dkey = new  DESKeySpec(keyBytes);
         
          key = new SecretKeySpec(dkey.getKey(), "DES");
          
         deCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
         enCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    } catch (NoSuchAlgorithmException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (NoSuchPaddingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (InvalidKeyException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


public byte[] format1(String str){
    //log.log(Level.INFO,"Getting Apache " + str);
    org.apache.commons.codec.binary.Base64 b64=new org.apache.commons.codec.binary.Base64();
    //log.log(Level.INFO,"Object created" + str);
    //byte[] val=b64.decode(str);
    //log.log(Level.INFO,"Array created" + val.toString());
    return b64.decode(str);
}

public String toUpper1(Object obj) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, ShortBufferException, BadPaddingException {
    
    byte[] input = toUpper(obj);

    return new org.apache.commons.codec.binary.Base64().encodeToString(input);
}

public String toLower1( byte[]  encrypted) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException {
    
    return toLower(encrypted).toString();
}

// encrypt
public byte[] toUpper(Object obj) throws InvalidKeyException, InvalidAlgorithmParameterException, IOException, IllegalBlockSizeException, ShortBufferException, BadPaddingException {
    byte[] input = convertToByteArray(obj);
    enCipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);

    return enCipher.doFinal(input);
}

//decrypt
public Object toLower( byte[]  encrypted) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException, ClassNotFoundException {
    deCipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
    
    return convertFromByteArray(deCipher.doFinal(encrypted));
}



private Object convertFromByteArray(byte[] byteObject) throws IOException,
        ClassNotFoundException {
    ByteArrayInputStream bais;

    ObjectInputStream in;
    bais = new ByteArrayInputStream(byteObject);
    in = new ObjectInputStream(bais);
    Object o = in.readObject();
    in.close();
    return o;

}



private byte[] convertToByteArray(Object complexObject) throws IOException {
    ByteArrayOutputStream baos;

    ObjectOutputStream out;

    baos = new ByteArrayOutputStream();

    out = new ObjectOutputStream(baos);

    out.writeObject(complexObject);

    out.close();

    return baos.toByteArray();

}


}
