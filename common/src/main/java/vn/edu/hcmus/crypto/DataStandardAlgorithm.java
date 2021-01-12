package vn.edu.hcmus.crypto;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.List;

public class DataStandardAlgorithm extends AbstractAlgorithm
{
    public DataStandardAlgorithm(final List<String> keys)
    {
        super(keys);
    }

    @Override
    public void initCipher()
    {
        cipherMode = "DES/ECB/PKCS5PADDING";
        final SecretKeySpec keySpec = new SecretKeySpec(keys.get(0).getBytes(), "DES");
        try {
            encryptCipher = Cipher.getInstance(cipherMode);
            encryptCipher.init(Cipher.ENCRYPT_MODE, keySpec);
            decryptCipher = Cipher.getInstance(cipherMode);
            decryptCipher.init(Cipher.DECRYPT_MODE, keySpec);
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
