package vn.edu.hcmus.crypto.algorithm;

import javax.crypto.Cipher;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public abstract class AbstractAlgorithm
{
    protected List<String> keys = new ArrayList<>();
    protected Cipher encryptCipher;
    protected Cipher decryptCipher;
    protected String cipherMode;

    public AbstractAlgorithm(final List<String> keys)
    {
        this.keys = keys;
        initCipher();
    }

    public abstract void initCipher();

    public String encrypt(final String message)
    {
        try {
            return Base64.getEncoder().encodeToString(encryptCipher.doFinal(message.getBytes()));
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(final String encryptedMessage)
    {
        try {
            return new String(decryptCipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<String> getKeys()
    {
        return keys;
    }

    public void setKeys(final List<String> keys)
    {
        this.keys = keys;
    }

    public Cipher getEncryptCipher()
    {
        return encryptCipher;
    }

    public void setEncryptCipher(final Cipher encryptCipher)
    {
        this.encryptCipher = encryptCipher;
    }

    public Cipher getDecryptCipher()
    {
        return decryptCipher;
    }

    public void setDecryptCipher(final Cipher decryptCipher)
    {
        this.decryptCipher = decryptCipher;
    }
}
