package com.furkan.service;

import com.furkan.dto.request.AuthLoginDto;
import com.furkan.dto.request.AuthSaveDto;
import com.furkan.exception.AuthException;
import com.furkan.exception.EErrorType;
import com.furkan.mapper.IAuthMapper;
import com.furkan.rabbitmq.messagemodel.ModelFanout;
import com.furkan.rabbitmq.producer.ProducerFanoutMessage;
import com.furkan.rabbitmq.producer.ProducerDirectSave;
import com.furkan.repository.IAuthRepository;
import com.furkan.repository.entity.Auth;
import com.furkan.repository.entity.Type;
import com.furkan.utility.JwtTokenManager;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManagerImpl<Auth,Long> {
    private final IAuthRepository authRepository;
    private final ProducerDirectSave producerDirectSave;
    private final ProducerFanoutMessage producerFanoutMessage;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository, ProducerDirectSave producerDirectSave, ProducerFanoutMessage producerFanoutMessage, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository=authRepository;
        this.producerDirectSave = producerDirectSave;
        this.producerFanoutMessage = producerFanoutMessage;
        this.jwtTokenManager = jwtTokenManager;
    }

    public void save(AuthSaveDto dto){
        Auth auth=IAuthMapper.INSTANCE.toAuth(dto);
        if (authRepository.findByUsername(auth.getUsername()).stream().count()!=0)
            throw new AuthException(EErrorType.AUTH_USERNAME_ERROR);
        save(auth);
        if (auth.getType()== Type.DRIVER){
            producerDirectSave.sendMessageSaveDriver(IAuthMapper.INSTANCE.ToModel(auth));
        }
        else if( auth.getType()==Type.PASSENGER){
            producerDirectSave.sendMessageSavePassenger(IAuthMapper.INSTANCE.ToModel(auth));
        }
    }

    public String doLogin(AuthLoginDto dto){
        Optional<Auth> auth =  authRepository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
        if(auth.isEmpty())
            throw new AuthException(EErrorType.AUTH_PARAMETRE_ERROR);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if(token.isEmpty())
            throw new AuthException(EErrorType.INVALID_TOKEN);
        return token.get();
    }

    public void  fanoutDeneme(String mesaj){
        producerFanoutMessage.sendFanoutMessage(ModelFanout.builder()
                        .mesaj(mesaj)
                .build());
    }

    public String apideneme(String mesaj){
        return "disardan aldigimiz mesajı service icinde bu yazının sonuna ekledik: "+mesaj;
    }



}
