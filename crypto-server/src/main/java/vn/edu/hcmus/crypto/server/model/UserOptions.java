package vn.edu.hcmus.crypto.server.model;

import vn.edu.hcmus.crypto.algorithm.AbstractAlgorithm;
import vn.edu.hcmus.crypto.exchange.DiffieHellmanKeyExchange;
import vn.edu.hcmus.crypto.exchange.ExchangeUtils;

import java.security.KeyPair;

public class UserOptions
{
    private static final UserOptions INSTANCE = new UserOptions();
    private UserOptions() {}

    private final DiffieHellmanKeyExchange dhke = createDH();
    private AbstractAlgorithm algorithm;

    public static UserOptions getUserOptions()
    {
        return INSTANCE;
    }

    public DiffieHellmanKeyExchange getDiffieHellmanKeyExchange()
    {
        return dhke;
    }

    public AbstractAlgorithm getAlgorithm()
    {
        return algorithm;
    }

    public void setAlgorithm(final AbstractAlgorithm algorithm)
    {
        this.algorithm = algorithm;
    }

    private DiffieHellmanKeyExchange createDH()
    {
        final KeyPair kp = ExchangeUtils.getKeyPairGenerator().generateKeyPair();
        return new DiffieHellmanKeyExchange(kp.getPublic(), kp.getPrivate());
    }
}
