import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class md5Test {
    @Test
    public void t1() {
        // Scanner sc = new Scanner(System.in);
        String s = "1234";
        s = DigestUtils.md5Hex(s);
        System.out.println(s);
    }
}
