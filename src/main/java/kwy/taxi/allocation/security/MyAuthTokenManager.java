package kwy.taxi.allocation.security;


import kwy.taxi.allocation.config.TokenConfig;
import kwy.taxi.allocation.model.User;
import kwy.taxi.allocation.repository.UserRepository;
import kwy.taxi.allocation.utils.exception.CustomException;
import kwy.taxi.allocation.utils.exception.ErrorCode;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class MyAuthTokenManager {

    private final TokenConfig tokenConfig;

    private final SimpleEncoder simpleEncoder;

    private final UserRepository userRepository;

    public MyAuthTokenManager(TokenConfig tokenConfig, SimpleEncoder simpleEncoder, UserRepository userRepository) {
        this.tokenConfig = tokenConfig;
        this.simpleEncoder = simpleEncoder;
        this.userRepository = userRepository;
    }

    public boolean validateToken(String authorization) {
        String decoded = simpleEncoder.decode(authorization);
        MyToken token = new MyToken(decoded);

        if(token.validateTokenType(tokenConfig.tokenType) && token.validateToken()) return true;

        throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
    }
    public String[] parseIdUserTypeFromToken(String authorization){
        String decoded = simpleEncoder.decode(authorization);
        MyToken myToken = new MyToken(decoded);
        return myToken.parseIdUserType();
    }
    public MyToken generateToken(User user){

        StringBuilder sb = new StringBuilder();
        sb.append(tokenConfig.tokenType).append(" ");
        sb.append(tokenConfig.tokenIssuer).append(",");
        sb.append(String.valueOf(user.getId())).append(",");
        sb.append(user.getUserType().name()).append(",");
        sb.append(LocalDateTime.now().plusHours(10L).toString());
        String result = sb.toString();
        return new MyToken(simpleEncoder.encode(result));
    }

    public boolean validateUserId(int userId){
        Optional<User> byUserId = userRepository.findById(userId);
        return byUserId.isPresent();
    }
}
