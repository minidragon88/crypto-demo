package vn.edu.hcmus.crypto.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class DataEncryptionAlgorithmTest
{
    private final String secret = "12345678";

    @Test
    public void encryptAndDecrypt64bitsMessage()
    {
        final String message = "abcd1234";
        final DataEncryptionAlgorithm dea = new DataEncryptionAlgorithm(Arrays.asList(secret));
        Assert.assertEquals(message, dea.decrypt(dea.encrypt(message)));
    }

    @Test
    public void encryptAndDecrypt32bitsMessage()
    {
        final String message = "abcd";
        final DataEncryptionAlgorithm dea = new DataEncryptionAlgorithm(Arrays.asList(secret));
        Assert.assertEquals(message, dea.decrypt(dea.encrypt(message)));
    }

    @Test
    public void encryptAndDecrypt128bitsMessage()
    {
        final String message = "abcd";
        final DataEncryptionAlgorithm dea = new DataEncryptionAlgorithm(Arrays.asList(secret));
        Assert.assertEquals(message, dea.decrypt(dea.encrypt(message)));
    }

    @Test
    public void encryptAndDecryptEmptyMessage()
    {
        final String message = "";
        final DataEncryptionAlgorithm dea = new DataEncryptionAlgorithm(Arrays.asList(secret));
        Assert.assertEquals(message, dea.decrypt(dea.encrypt(message)));
    }
}
