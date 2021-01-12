package vn.edu.hcmus.crypto.exchange;

import com.google.common.annotations.VisibleForTesting;

import javax.crypto.KeyAgreement;

import java.security.PrivateKey;
import java.security.PublicKey;

public class DiffieHellmanKeyExchange
{
    private final PublicKey publicKey;
    private final PrivateKey privateKey;
    private PublicKey otherPublicKey;
    private byte[] secret;

    public DiffieHellmanKeyExchange(final PublicKey selfPublicKey, final PrivateKey privateKey, final PublicKey otherPublicKey)
    {
        this.publicKey = selfPublicKey;
        this.privateKey = privateKey;
        this.otherPublicKey = otherPublicKey;
    }

    public DiffieHellmanKeyExchange(final PublicKey selfPublicKey, final PrivateKey privateKey)
    {
        this.publicKey = selfPublicKey;
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey()
    {
        return publicKey;
    }

    public void setOtherPublicKey(final PublicKey otherPublicKey)
    {
        this.otherPublicKey = otherPublicKey;
    }

    public void agreeSecretKey()
    {
        try {
            final KeyAgreement ka = KeyAgreement.getInstance("DH");
            ka.init(privateKey);
            ka.doPhase(otherPublicKey, true);
            secret = ka.generateSecret();
        }
        catch (final Exception e) {
            e.printStackTrace();
        }
    }

    @VisibleForTesting
    public byte[] getSecret()
    {
        return secret;
    }
}
