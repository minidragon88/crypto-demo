package vn.edu.hcmus.crypto.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AdvancedEncryptionStandardAlgorithmTest
{
    private final String secret = "abcdefgh12345678!@#$%^&*";

    @Test
    public void encryptAndDecrypt64bitsMessage()
    {
        final String message = "abcd1234";
        final AdvancedEncryptionStandardAlgorithm aes = new AdvancedEncryptionStandardAlgorithm(Arrays.asList(secret));
        Assert.assertEquals(message, aes.decrypt(aes.encrypt(message)));
    }

    @Test
    public void encryptAndDecrypt32bitsMessage()
    {
        final String message = "abcd";
        final AdvancedEncryptionStandardAlgorithm aes = new AdvancedEncryptionStandardAlgorithm(Arrays.asList(secret));
        Assert.assertEquals(message, aes.decrypt(aes.encrypt(message)));
    }

    @Test
    public void encryptAndDecrypt128bitsMessage()
    {
        final String message = "abcd1234efgh5678";
        final AdvancedEncryptionStandardAlgorithm aes = new AdvancedEncryptionStandardAlgorithm(Arrays.asList(secret));
        Assert.assertEquals(message, aes.decrypt(aes.encrypt(message)));
    }

    @Test
    public void encryptAndDecryptEmptyMessage()
    {
        final String message = "";
        final AdvancedEncryptionStandardAlgorithm aes = new AdvancedEncryptionStandardAlgorithm(Arrays.asList(secret));
        Assert.assertEquals(message, aes.decrypt(aes.encrypt(message)));
    }
}
