package md5;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Administrator on 2017/2/17.
 */
public class Test {
    public static void main(String[] args) {
        String plainPassword = "asdf1234";
        System.out.println(DigestUtils.md5Hex(plainPassword));

        String encryptedPassword="cedd7619d9a034cde2f547bebb00bd6g";
        System.out.println(encryptedPassword.matches("^[0-9a-fA-F]{32}$"));
    }
}
