package vn.edu.hcmus.crypto.exchange;

import com.google.common.annotations.VisibleForTesting;

import javax.crypto.KeyAgreement;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

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

    public PublicKey decodePublicKeyFromString(final String keyString)
    {
        try {
            return KeyFactory.getInstance("DH").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(keyString)));
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String encodePublicKeyToString()
    {
        try {
            return Base64.getEncoder().encodeToString(publicKey.getEncoded());
        }
        catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String generateSecretBySize(final int size)
    {
        return Base64.getEncoder().encodeToString(secret).substring(0, size);
    }

    @VisibleForTesting
    public byte[] getSecret()
    {
        return secret;
    }
}
