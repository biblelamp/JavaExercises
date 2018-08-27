import java.security.*;
import javax.crypto.*;

public class RSAEncryptDecrypt {
 
    public static void main(String[] args)
            throws NoSuchAlgorithmException,
                   NoSuchPaddingException,
                   InvalidKeyException,
                   IllegalBlockSizeException,
                   BadPaddingException {

        String text =
                  "Lorem ipsum dolor sit amet, consectetur adipisicing elit";
      
        Cipher cipher = Cipher.getInstance("RSA");
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize( 2048 );
        KeyPair kp = keyGen.genKeyPair();

        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();
       
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] x = cipher.doFinal(text.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] y = cipher.doFinal(x);
       
        System.out.println("\nText is:\n" + text);
        System.out.println("\nEncrypt text:\n" + new String(x));
        System.out.println("\nDecrypt text:\n" + new String(y));
    }
}
