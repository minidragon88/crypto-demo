package vn.edu.hcmus.crypto.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.edu.hcmus.crypto.exchange.DiffieHellmanKeyExchange;
import vn.edu.hcmus.crypto.exchange.ExchangeUtils;

import java.security.KeyPair;
import java.util.Map;
@RestController
@RequestMapping("/key")
public class KeyController
{
    private final DiffieHellmanKeyExchange dhke = getDiffieHellmanObject();

    @GetMapping
    public String hello()
    {
        return "Hello!";
    }
    
    @GetMapping("/public-key")
    public String getPublicKey()
    {
        return dhke.encodePublicKeyToString();
    }

    @PostMapping("/register-public-key")
    public ResponseEntity<String> register(@RequestHeader final Map<String, String> headers, @RequestBody final String publicKey)
    {
        return new ResponseEntity<>("abc", HttpStatus.OK);
    }
    
    public DiffieHellmanKeyExchange getDiffieHellmanObject()
    {
        final KeyPair kp = ExchangeUtils.getKeyPairGenerator().generateKeyPair();
        return new DiffieHellmanKeyExchange(kp.getPublic(), kp.getPrivate());
    }
}
