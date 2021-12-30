package br.com.systemsgs.controller;

import br.com.systemsgs.jwt.JWTTokenProvider;
import br.com.systemsgs.repository.UserRepository;
import br.com.systemsgs.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "/api/v1/login")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;

    }

    @RequestMapping(value = "/testSecurity")
    public String teste(){
        return "Deu certo o teste";
    }

    @PostMapping(produces = { "application/json", "application/xml", "application/x-yaml"}, consumes = { "application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> login (@RequestBody UserVO userVO){
        try{
            var username = userVO.getUserName();
            var password = userVO.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = userRepository.findByUserName(username);
            var token = "";

            if(user != null){
                token = jwtTokenProvider.createToken(username, user.getRoles());
            } else {
              throw new UsernameNotFoundException("Usuário não Encontrado!");
            }

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("token", token);

            return ok(model);

        }catch(AuthenticationException exception){
            throw new BadCredentialsException("Usuário ou Senha Inválidos!");
        }
    }

}
