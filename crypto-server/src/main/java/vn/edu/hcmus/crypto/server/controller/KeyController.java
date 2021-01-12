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
import vn.edu.hcmus.crypto.server.model.UserOptions;

import java.security.PublicKey;
import java.util.Map;
@RestController
@RequestMapping("/key")
public class KeyController
{
    @GetMapping
    public String hello()
    {
        return "Hello!";
    }

    @GetMapping("/public-key")
    public String getPublicKey()
    {
        return UserOptions.getUserOptions().getDiffieHellmanKeyExchange().encodePublicKeyToString();
    }

    @PostMapping("/register-key")
    public ResponseEntity<String> registerKey(@RequestHeader final Map<String, String> headers, @RequestBody final String publicKey)
    {
        final DiffieHellmanKeyExchange dhke = UserOptions.getUserOptions().getDiffieHellmanKeyExchange();
        final PublicKey clientPublicKey = dhke.decodePublicKeyFromString(publicKey);
        dhke.setOtherPublicKey(clientPublicKey);
        return new ResponseEntity<>("Register successfully", HttpStatus.OK);
    }

    @PostMapping("/agree-key")
    public ResponseEntity<String> acceptKey(@RequestHeader final Map<String, String> headers, @RequestBody final String publicKey)
    {
        final DiffieHellmanKeyExchange dhke = UserOptions.getUserOptions().getDiffieHellmanKeyExchange();
        if (dhke.getPublicKey() == null) {
            return new ResponseEntity<>("Must register your key first", HttpStatus.BAD_REQUEST);
        }
        dhke.agreeSecretKey();
        return new ResponseEntity<>("Key agreed", HttpStatus.OK);
    }
}
