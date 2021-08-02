package kwy.taxi.allocation.security;

import kwy.taxi.allocation.utils.exception.CustomException;
import kwy.taxi.allocation.utils.exception.ErrorCode;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MyToken {

    private String accessToken;

    public MyToken() {
    }

    public MyToken(String authorization){
        this.accessToken = authorization;
    }

    public boolean validateTokenType(String correctType){
        return accessToken.startsWith(correctType);
    }
    public boolean validateToken(){
        String[] data = accessToken.split(" ");
        try{
            String dueDate = data[1].split(",")[3];
            return LocalDateTime.now().isBefore(LocalDateTime.parse(dueDate));
        }catch(Exception ex){
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        }
    }
    public String[] parseIdUserType(){
        String[] data = accessToken.split(" ");
        try{
            return Arrays.copyOfRange(data[1].split(","), 1, 3);
        }catch(Exception ex){
            throw new CustomException(ErrorCode.INVALID_AUTH_TOKEN);
        }
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
