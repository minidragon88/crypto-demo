package vn.edu.hcmus.crypto.server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.hcmus.crypto.algorithm.AbstractAlgorithm;
import vn.edu.hcmus.crypto.algorithm.AdvancedEncryptionStandardAlgorithm;
import vn.edu.hcmus.crypto.algorithm.DataEncryptionAlgorithm;
import vn.edu.hcmus.crypto.algorithm.TripleDES;
import vn.edu.hcmus.crypto.server.model.UserOptions;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/communicate")
public class CommunicateController
{
    private final Logger logger = LoggerFactory.getLogger(CommunicateController.class);

    @PostMapping("/change-algorithm")
    public ResponseEntity<String> changeAlgorithm(@RequestHeader final Map<String, String> headers, @RequestBody final String userInput)
    {
        final UserOptions userOpts = UserOptions.getUserOptions();
        if (userInput.equals("DES")) {
            userOpts.setAlgorithm(new DataEncryptionAlgorithm(Arrays.asList(userOpts.getDiffieHellmanKeyExchange().generateSecretBySize(64))));
        }
        else if (userInput.equals("3DES")) {
            userOpts.setAlgorithm(new TripleDES(Arrays.asList(userOpts.getDiffieHellmanKeyExchange().generateSecretBySize(192))));
        }
        else {
            userOpts.setAlgorithm(new AdvancedEncryptionStandardAlgorithm(Arrays.asList(userOpts.getDiffieHellmanKeyExchange().generateSecretBySize(128))));
        }
        return new ResponseEntity<>("Algorithm changed successfully", HttpStatus.OK);
    }

    @PostMapping("/message")
    public ResponseEntity<String> message(@RequestHeader final Map<String, String> headers, @RequestBody final String message)
    {
        logger.info("Ecrypted message is : " + message);
        final AbstractAlgorithm algorithm = UserOptions.getUserOptions().getAlgorithm();
        final String plainText = algorithm.decrypt(message);
        logger.info("Derypted message is : " + plainText);
        return new ResponseEntity<>("Plaintext is " + plainText, HttpStatus.ACCEPTED);
    }
}
