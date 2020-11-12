package org.example;

/** Hello world! */
public class App {
  public static void main(String[] args) {
    long before = System.currentTimeMillis();
    String checksum =
        HMAC.SHA256.toHexString("/folder/somefile.txt", "key", "utf-8");
    long after = System.currentTimeMillis();
    long executionTime = (after - before);

    System.out.println("MAC checksum:" + checksum);
    System.out.println("Computation time: " + executionTime + " ms");
  }
}
