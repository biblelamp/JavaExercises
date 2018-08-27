/*
 * form http://qaru.site/questions/39813/load-rsa-public-key-from-file
 * open .der file online - http://lapo.it/asn1js/
 */
import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.security.spec.*;

public class PublicKeyReader {

  public static void main(String[] args) throws Exception {
	  System.out.println(get("./..//Public/pubkey.der"));
  }

  public static PublicKey get(String filename) throws Exception {
    byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

    X509EncodedKeySpec spec =
      new X509EncodedKeySpec(keyBytes);
    KeyFactory kf = KeyFactory.getInstance("RSA");
    return kf.generatePublic(spec);
  }
}
