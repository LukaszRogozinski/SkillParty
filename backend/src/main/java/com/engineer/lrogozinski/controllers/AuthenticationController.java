package com.engineer.lrogozinski.controllers;

import com.engineer.lrogozinski.config.JwtTokenUtil;
import com.engineer.lrogozinski.domain.UsedToken;
import com.engineer.lrogozinski.security.AuthToken;
import com.engineer.lrogozinski.security.LoginUser;
import com.engineer.lrogozinski.services.UsedTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsedTokenService usedTokenService;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws Exception {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.genurutuTokenutu(authentication);
        List<UsedToken> usedTokens = usedTokenService.findAll();
        for(int i=0; i<usedTokens.size(); i++){
            if(usedTokens.get(i).getToken()
                    .equals(token)){
                throw new Exception("current token was used before");
            }
        }
        usedTokenService.save(new UsedToken(token, new Date()));
        return ResponseEntity.ok(new AuthToken(token));
    }

}
