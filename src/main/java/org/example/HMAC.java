package org.example;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public enum HMAC {
    MD5("HmacMD5"),
    SHA1("HmacSHA1"),
    SHA256("HMACSHA256");

    private final String algorithm;
    private HMAC(String algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Computes the HMAC code and returns a hex formated low case string for the
     * specified content and secret key.
     *
     * @param content the content specified to generate the HMAC code
     * @param secret the secret key used in the generation
     * @param charset the char-set used to convert string to byte array
     * @return returns a hex encoded low case string.
     */
    public String toHexString(String content, String secret, String charset) {
        try {
            byte[] data = content.toString().getBytes(charset);
            byte[] key = secret.getBytes(charset);

            Mac mac = Mac.getInstance(algorithm);
            SecretKeySpec secret_key = new SecretKeySpec(key, algorithm);
            mac.init(secret_key);
            byte[] hashBytes = mac.doFinal(data);

            return DatatypeConverter.printHexBinary(hashBytes).toLowerCase();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

}
