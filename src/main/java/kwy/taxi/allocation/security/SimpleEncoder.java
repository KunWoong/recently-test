package kwy.taxi.allocation.security;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class SimpleEncoder {
    public String encode(String original){
        return Base64.getEncoder().encodeToString(original.getBytes(StandardCharsets.UTF_8));
    }
    public String decode(String encrypted){
        return new String(Base64.getDecoder().decode(encrypted));
    }
}
