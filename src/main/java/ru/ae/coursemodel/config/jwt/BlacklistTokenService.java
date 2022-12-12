package ru.ae.coursemodel.config.jwt;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Getter
public class BlacklistTokenService {

    private final List<String> blacklistTokens = new ArrayList<>();

    public void addBlackList(String token) {
        blacklistTokens.add(token);
    }

    public boolean isTokenExistInBlacklist(String token) {
        log.info("Check is token exists in blacklist : {}", token);

        boolean isExists = false;
        for(String tokenValue : blacklistTokens) {
            if(tokenValue.equals(token)) {
                isExists = true;
                break;
            }
        }
        log.info("Token is {} blacklisted", isExists ? "" : "NOT" );
        return isExists;
    }

}
