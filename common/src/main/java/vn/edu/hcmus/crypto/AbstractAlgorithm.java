package vn.edu.hcmus.crypto;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

public abstract class AbstractAlgorithm
{
    private List<String> keys = new ArrayList<>();
    private Cipher encryptCipher;
    private Cipher decryptCipher;

    public AbstractAlgorithm(final List<String> keys)
    {
        this.keys = keys;
        initCipher();
    }

    public abstract void initCipher();

    public String encrypt(final String message)
    {
        try {
            return new String(encryptCipher.doFinal(message.getBytes()));
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(final String encryptedMessage)
    {
        try {
            return new String(decryptCipher.doFinal(encryptedMessage.getBytes()));
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
