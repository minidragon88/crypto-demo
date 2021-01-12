package vn.edu.hcmus.crypto.exchange;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public final class ExchangeUtils
{
    private ExchangeUtils() {}

    public static KeyPairGenerator getKeyPairGenerator()
    {
        try {
            final KeyPairGenerator kpg = KeyPairGenerator.getInstance("DH");
            kpg.initialize(2048);
            return kpg;
        }
        catch (final NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}
