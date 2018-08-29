/*
 * Based on code from https://snipplr.com/view/18368/
 * 
 * @author Sergey Iryupin
 * @version August 29, 2018
 */

import java.util.*;
import java.io.*;
import java.security.*;
import java.security.spec.*;

import javax.crypto.*;
import javax.crypto.spec.*;

public class KeysExercises {
 
    public static void main(String args[]) {
        KeysExercises keys = new KeysExercises();
        try {
            /*
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            KeyPair generatedKeyPair = keyGen.genKeyPair();
 
            System.out.println("Generated Key Pair");
            //keys.dumpKeyPair(generatedKeyPair);
            keys.SaveKeyPair("keys", generatedKeyPair);
            */
            KeyPair loadedKeyPair = keys.LoadKeyPair("keys", "RSA");
            System.out.println("Loaded Key Pair");
            //keys.dumpKeyPair(loadedKeyPair);

            //System.out.println(Arrays.toString(keys.encrypt(loadedKeyPair.getPublic(), "Hello World")));
            //new byte[]{24, -38, -39, 97, -79, -31, 21, 83, 113, 96, 63, -6, -57, -10, -25, 127, -118, 50, 38, -115, 74, -37, 4, -62, 120, -58, -99, -51, 101, -115, 102, -71, 79, 69, 35, 58, 62, -111, -122, 64, 15, 71, 70, 123, -92, 84, -114, 46, -49, -95, -18, -65, 4, 104, -36, -42, 121, 48, 2, -53, -94, -122, 38, -56, 85, -18, -93, 7, -30, 15, 87, 83, 51, -67, -88, 87, 3, -10, -34, 96, -115, 2, -115, 17, 31, 78, 122, -54, 29, 86, 99, 69, -91, -109, -114, 119, -25, 37, 99, 124, -9, 18, -25, 38, 99, 20, -12, 4, 1, -101, -8, 75, -110, -122, 81, 86, 83, -104, -7, 18, 58, -31, -22, -10, 54, 75, -127, -13});

            keys.decript(loadedKeyPair.getPrivate(),
                //new byte[]{24, -38, -39, 97, -79, -31, 21, 83, 113, 96, 63, -6, -57, -10, -25, 127, -118, 50, 38, -115, 74, -37, 4, -62, 120, -58, -99, -51, 101, -115, 102, -71, 79, 69, 35, 58, 62, -111, -122, 64, 15, 71, 70, 123, -92, 84, -114, 46, -49, -95, -18, -65, 4, 104, -36, -42, 121, 48, 2, -53, -94, -122, 38, -56, 85, -18, -93, 7, -30, 15, 87, 83, 51, -67, -88, 87, 3, -10, -34, 96, -115, 2, -115, 17, 31, 78, 122, -54, 29, 86, 99, 69, -91, -109, -114, 119, -25, 37, 99, 124, -9, 18, -25, 38, 99, 20, -12, 4, 1, -101, -8, 75, -110, -122, 81, 86, 83, -104, -7, 18, 58, -31, -22, -10, 54, 75, -127, -13});
                new byte[]{107,30,43,-75,-118,92,-27,-51,-29,-127,-66,115,54,69,-94,93,-95,-61,70,103,-102,103,75,-98,77,-25,50,109,-69,51,-46,13,-120,-99,-121,55,6,-112,105,64,-66,-24,-111,-9,-103,-72,115,97,15,118,72,80,14,76,43,-34,57,-66,-52,32,-11,105,-23,53,84,-122,34,37,108,115,87,10,87,5,81,-89,-36,-28,126,-83,-51,127,81,-126,-29,6,89,122,28,-85,-12,-14,119,83,-28,-52,-9,18,-21,-89,34,-78,-63,35,-109,106,89,-35,-67,-123,2,67,62,-96,-122,-8,125,-99,86,-75,33,-109,6,-19,-44,38,-107,-36});
                //keys.encrypt(loadedKeyPair.getPublic(), "Hello World"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(PublicKey publicKey, String message) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            return cipher.doFinal(message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void decript(PrivateKey privateKey, byte[] encrypted) {
        try {
            // RSA/ECB/PKCS1Padding
            // RSA/ECB/OAEPWithSHA-1AndMGF1Padding
            // RSA/ECB/OAEPWithSHA-256AndMGF1Padding
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            System.out.println(new String(cipher.doFinal(encrypted)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dumpKeyPair(KeyPair keyPair) {
        PublicKey pub = keyPair.getPublic();
        System.out.println("Public Key: " + getHexString(pub.getEncoded()));
 
        PrivateKey priv = keyPair.getPrivate();
        System.out.println("Private Key: " + getHexString(priv.getEncoded()));
    }

    private String getHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public void SaveKeyPair(String path, KeyPair keyPair) throws IOException {
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
 
        // Store Public Key
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
                publicKey.getEncoded());
        FileOutputStream fos = new FileOutputStream(path + "/public.key");
        fos.write(x509EncodedKeySpec.getEncoded());
        fos.close();
 
        // Store Private Key
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                privateKey.getEncoded());
        fos = new FileOutputStream(path + "/private.key");
        fos.write(pkcs8EncodedKeySpec.getEncoded());
        fos.close();
    }
 
    public KeyPair LoadKeyPair(String path, String algorithm)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        // Read Public Key
        File filePublicKey = new File(path + "/public.key");
        FileInputStream fis = new FileInputStream(path + "/public.key");
        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
        fis.read(encodedPublicKey);
        fis.close();
 
        // Read Private Key
        File filePrivateKey = new File(path + "/private.key");
        fis = new FileInputStream(path + "/private.key");
        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
        fis.read(encodedPrivateKey);
        fis.close();
 
        // Generate KeyPair
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
                encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);
 
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
                encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
 
        return new KeyPair(publicKey, privateKey);
    }

	private byte[] load(String file) throws FileNotFoundException, IOException {
		FileInputStream fis = new FileInputStream(file);
		byte[] buf = new byte[fis.available()];
		fis.read(buf);
		fis.close();
		return buf;
	}
}
