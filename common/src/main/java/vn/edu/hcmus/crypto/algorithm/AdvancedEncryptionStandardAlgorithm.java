package vn.edu.hcmus.crypto.algorithm;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.List;

public class AdvancedEncryptionStandardAlgorithm extends AbstractAlgorithm
{
    public AdvancedEncryptionStandardAlgorithm(final List<String> keys)
    {
        super(keys);
    }

    @Override
    public void initCipher()
    {
        cipherMode = "AES/ECB/PKCS5Padding";
        try {
            final SecretKeySpec secret = new SecretKeySpec(keys.get(0).getBytes(), "AES");
            encryptCipher = Cipher.getInstance(cipherMode);
            encryptCipher.init(Cipher.ENCRYPT_MODE, secret);
            decryptCipher = Cipher.getInstance(cipherMode);
            decryptCipher.init(Cipher.DECRYPT_MODE, secret);
        }
        catch (final Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
