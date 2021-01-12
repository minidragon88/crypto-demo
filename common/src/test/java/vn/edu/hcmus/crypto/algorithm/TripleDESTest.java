package vn.edu.hcmus.crypto.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class TripleDESTest
{
    private final String secret = "abcdefgh12345678!@#$%^&*";

    @Test
    public void encryptAndDecrypt64bitsMessage()
    {
        final String message = "abcd1234";
        final TripleDES tripleDes = new TripleDES(Arrays.asList(secret));
        Assert.assertEquals(message, tripleDes.decrypt(tripleDes.encrypt(message)));
    }

    @Test
    public void encryptAndDecrypt32bitsMessage()
    {
        final String message = "abcd";
        final TripleDES dea = new TripleDES(Arrays.asList(secret));
        Assert.assertEquals(message, dea.decrypt(dea.encrypt(message)));
    }

    @Test
    public void encryptAndDecrypt128bitsMessage()
    {
        final String message = "abcd";
        final TripleDES tripleDes = new TripleDES(Arrays.asList(secret));
        Assert.assertEquals(message, tripleDes.decrypt(tripleDes.encrypt(message)));
    }

    @Test
    public void encryptAndDecryptEmptyMessage()
    {
        final String message = "";
        final TripleDES tripleDes = new TripleDES(Arrays.asList(secret));
        Assert.assertEquals(message, tripleDes.decrypt(tripleDes.encrypt(message)));
    }
}
