package vn.edu.hcmus.crypto.algorithm;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import java.util.List;

public class TripleDES extends AbstractAlgorithm
{
    public TripleDES(final List<String> keys)
    {
        super(keys);
    }

    @Override
    public void initCipher()
    {
        cipherMode = "DESede/ECB/PKCS5Padding";
        try {
            final DESedeKeySpec dks = new DESedeKeySpec(keys.get(0).getBytes());
            final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            final SecretKey securekey = keyFactory.generateSecret(dks);
            encryptCipher = Cipher.getInstance(cipherMode);
            encryptCipher.init(Cipher.ENCRYPT_MODE, securekey);
            decryptCipher = Cipher.getInstance(cipherMode);
            decryptCipher.init(Cipher.DECRYPT_MODE, securekey);
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
