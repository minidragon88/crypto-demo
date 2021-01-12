package vn.edu.hcmus.crypto.exchange;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
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
    public void testDHWithKeyObjects()
    {
        final KeyPair clientKeys = kpg.generateKeyPair();
        final KeyPair serverKeys = kpg.generateKeyPair();

        final DiffieHellmanKeyExchange clientDH = new DiffieHellmanKeyExchange(clientKeys.getPublic(), clientKeys.getPrivate(), serverKeys.getPublic());
        final DiffieHellmanKeyExchange serverDH = new DiffieHellmanKeyExchange(serverKeys.getPublic(), serverKeys.getPrivate(), clientKeys.getPublic());
        clientDH.agreeSecretKey();
        serverDH.agreeSecretKey();
        Assert.assertEquals(Base64.getEncoder().encodeToString(clientDH.getSecret()), Base64.getEncoder().encodeToString(serverDH.getSecret()));
    }

    @Test
    public void testDHWithEncodedStringKey() throws InvalidKeySpecException, NoSuchAlgorithmException
    {
        final KeyPair clientKeys = kpg.generateKeyPair();
        final KeyPair serverKeys = kpg.generateKeyPair();

        final DiffieHellmanKeyExchange clientDH = new DiffieHellmanKeyExchange(clientKeys.getPublic(), clientKeys.getPrivate());
        final DiffieHellmanKeyExchange serverDH = new DiffieHellmanKeyExchange(serverKeys.getPublic(), serverKeys.getPrivate());

        final PublicKey serverPublic = clientDH.decodePublicKeyFromString(serverDH.encodePublicKeyToString());
        final PublicKey clientPublic = serverDH.decodePublicKeyFromString(clientDH.encodePublicKeyToString());

        clientDH.setOtherPublicKey(serverPublic);
        serverDH.setOtherPublicKey(clientPublic);

        clientDH.agreeSecretKey();
        serverDH.agreeSecretKey();
        Assert.assertEquals(Base64.getEncoder().encodeToString(clientDH.getSecret()), Base64.getEncoder().encodeToString(serverDH.getSecret()));
    }
}
