package kwy.taxi.allocation.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenConfig {

    @Value("${my.token.type}")
    public String tokenType;

    @Value("${my.token.issuer}")
    public String tokenIssuer;

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }
}
