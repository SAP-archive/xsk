import com.sap.xsk.xssecurestore.ds.facade.XSKSecureCryptoFacade;
import org.junit.Test;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import static org.junit.Assert.assertArrayEquals;


public class XSKSecureStoreCryptoFacadeTest {
    private static final String HMAC_MD_5 = "HmacMD5";
    private static final String HMAC_SHA_1 = "HmacSHA1";
    private static final String HMAC_SHA_256 = "HmacSHA256";
    private static final String SHA256 = "SHA-256";
    private static final String MD5 = "MD5";
    private static final String SHA1 = "SHA1";

    @Test
    public void testMD5EncryptHashWithKey() throws NoSuchAlgorithmException, InvalidKeyException {
        var hash = XSKSecureCryptoFacade.encryptHash("value", "key", MD5, HMAC_MD_5);
        byte[] encryptedHash = {1, 67, 62, -3, 95, 22, 50, 126, -92, -77, 17, 68, 87, 44, 103, -10};
        assertArrayEquals(hash, encryptedHash);
    }
    @Test
    public void testMD5EncryptHashWithoutKey() throws NoSuchAlgorithmException, InvalidKeyException {
        var hash = XSKSecureCryptoFacade.encryptHash("value", null, MD5, HMAC_MD_5);
        byte[] encryptedHash = {32, 99, -63, 96, -115, 110, 11, -81, -128, 36, -100, 66, -30, -66, 88, 4};
        assertArrayEquals(hash, encryptedHash);
    }
    @Test
    public void testSHA1EncryptHashWithKey() throws NoSuchAlgorithmException, InvalidKeyException {
        var hash = XSKSecureCryptoFacade.encryptHash("value", "key", SHA1, HMAC_SHA_1);
        byte[] encryptedHash = {87, 68, 58, 76, 5, 35, 80, -92, 70, 56, -125, 93, 100, -3, 102, -126, 47, -127, 51, 25};
        assertArrayEquals(hash, encryptedHash);
    }
    @Test
    public void testSHA1EncryptHashWithoutKey() throws NoSuchAlgorithmException, InvalidKeyException {
        var hash = XSKSecureCryptoFacade.encryptHash("value", null, SHA1, HMAC_SHA_1);
        byte[] encryptedHash = {-13, 43, 103, -57, -30, 99, 66, -81, 66, -17, -85, -58, 116, -44, 65, -36, -96, -94, -127, -59};
        assertArrayEquals(hash, encryptedHash);
    }
    @Test
    public void testSHA256EncryptHashWithKey() throws NoSuchAlgorithmException, InvalidKeyException {
        var hash = XSKSecureCryptoFacade.encryptHash("value", "key", SHA256, HMAC_SHA_256);
        byte[] encryptedHash = {-112, -5, -4, -15, 94, 116, -93, 107, -119, -37, -37, 42, 114, 29, -102, -20, -1, -33, -35, -36, 92, -125, -30, 127, 117, -110, 89, 79, 113, -109, 36, -127};
        assertArrayEquals(hash, encryptedHash);
    }
    @Test
    public void testSHA256EncryptHashWithoutKey() throws NoSuchAlgorithmException, InvalidKeyException {
        var hash = XSKSecureCryptoFacade.encryptHash("value", null, SHA256, HMAC_SHA_256);
        byte[] encryptedHash = {-51, 66, 64, 77, 82, -83, 85, -52, -6, -102, -54, 74, -36, -126, -118, -91, -128, 10, -39, -45, -123, -96, 103, 31, -68, -65, 114, 65, 24, 50, 6, 25};
        assertArrayEquals(hash, encryptedHash);
    }

}
