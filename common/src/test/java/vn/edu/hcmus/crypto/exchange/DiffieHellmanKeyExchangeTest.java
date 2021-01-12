package vn.edu.hcmus.crypto.exchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class DiffieHellmanKeyExchangeTest
{
    KeyPairGenerator kpg;
    @Before
    public void setUp()
    {
        try {
            kpg = KeyPairGenerator.getInstance("DH");
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
        kpg.initialize(1024);
    }

    @Test
    public void testDiffie()
    {
        final KeyPair clientKeys = kpg.generateKeyPair();
        final KeyPair serverKeys = kpg.generateKeyPair();
        final DiffieHellmanKeyExchange clientDH = new DiffieHellmanKeyExchange(clientKeys.getPublic(), clientKeys.getPrivate(), serverKeys.getPublic());
        final DiffieHellmanKeyExchange serverDH = new DiffieHellmanKeyExchange(serverKeys.getPublic(), serverKeys.getPrivate(), clientKeys.getPublic());
        clientDH.agreeSecretKey();
        serverDH.agreeSecretKey();
        Assert.assertEquals(Base64.getEncoder().encodeToString(clientDH.getSecret()), Base64.getEncoder().encodeToString(serverDH.getSecret()));
    }
}
