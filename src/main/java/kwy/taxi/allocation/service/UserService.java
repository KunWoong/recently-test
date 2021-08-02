package kwy.taxi.allocation.service;

import kwy.taxi.allocation.model.User;
import kwy.taxi.allocation.repository.UserRepository;
import kwy.taxi.allocation.security.MyAuthTokenManager;
import kwy.taxi.allocation.security.MyToken;
import kwy.taxi.allocation.security.SimpleEncoder;
import kwy.taxi.allocation.utils.exception.CustomException;
import kwy.taxi.allocation.utils.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.regex.Pattern.matches;


@Service
public class UserService{

    private final UserRepository userRepository;

    private final MyAuthTokenManager tokenManager;

    private final SimpleEncoder simpleEncoder;

    public UserService(UserRepository userRepository, MyAuthTokenManager tokenManager, SimpleEncoder simpleEncoder) {
        this.userRepository = userRepository;
        this.tokenManager = tokenManager;
        this.simpleEncoder = simpleEncoder;
    }

    @Transactional
    public User createUser(User user){
        Optional<User> isExist = userRepository.findByEmail(user.getEmail());
        if(isExist.isPresent()){
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }
        if(user.getUserType() == null) throw new CustomException(ErrorCode.INVALID_USER_TYPE);
        if(!checkEmail(user.getEmail())) throw new CustomException(ErrorCode.INVALID_EMAIL_FORM);

        User created = new User.Builder()
                            .email(user.getEmail())
                            .password(encodePassword(user.getPassword()))
                            .userType(user.getUserType())
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();
        return userRepository.save(created);
    }

    private String encodePassword(String password){
        return simpleEncoder.encode(password);
    }

    public MyToken validateUser(User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        User found = byEmail.orElseThrow(() -> new CustomException(ErrorCode.INVALID_EMAIL_PASSWORD));
        if(!simpleEncoder.decode(found.getPassword()).equals(user.getPassword())){
            throw new CustomException(ErrorCode.INVALID_EMAIL_PASSWORD);
        }
        return tokenManager.generateToken(found);
    }

    private boolean checkEmail(String email){
        return matches("[\\w~\\-.+]+@[\\w~\\-]+(\\.[\\w~\\-]+)+", email);

    }
}
