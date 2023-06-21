package com.furkan.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.furkan.dto.request.AuthLoginDto;
import com.furkan.dto.request.RegisterDriverRequestDto;
import com.furkan.dto.request.RegisterPassengerRequestDto;
import com.furkan.exception.AuthException;
import com.furkan.exception.EErrorType;
import com.furkan.mapper.IAuthMapper;
import com.furkan.mapper.IRegisterMapper;
import com.furkan.rabbitmq.producer.DirectProducer;
import com.furkan.repository.IAuthRepository;
import com.furkan.repository.entity.Auth;
import com.furkan.utility.JwtTokenManager;
import com.furkan.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService extends ServiceManagerImpl<Auth,Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IAuthMapper authMapper;
    private final IRegisterMapper registerMapper;
    private DirectProducer directProducer;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, IAuthMapper authMapper, IRegisterMapper registerMapper, DirectProducer directProducer) {
        super(authRepository);
        this.authRepository=authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authMapper = authMapper;
        this.registerMapper = registerMapper;
        this.directProducer = directProducer;
    }




    public String multipartFileToString(MultipartFile file) {
        Map config = new HashMap();
        config.put("cloud_name", "dtr9fdvuc");
        config.put("api_key", "242484186824784");
        config.put("api_secret", "VrJPbIUlrXeiR5Rl-9Ghk3gWHuI");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }




    public Boolean saveDriver(RegisterDriverRequestDto dto) {
        if( authRepository.isEmail(dto.getEmail())){
            throw new AuthException(EErrorType.EMAIL_ALREADY_REGİSTERED);
        }
        save(authMapper.toAuth(dto));
        new Thread(()->{
            String avatarUrl=multipartFileToString(dto.getAvatar());
            String drivingLicenceUrl=multipartFileToString(dto.getDrivingLicence());
            directProducer.sendRegisterDriver(registerMapper.toModel(dto,avatarUrl,drivingLicenceUrl));
        }).start();

        return true;
    }

    public Boolean savePassenger(RegisterPassengerRequestDto dto) {
        if( authRepository.isEmail(dto.getEmail())){
            throw new AuthException(EErrorType.EMAIL_ALREADY_REGİSTERED);
        }
        save(authMapper.toAuth(dto));
        new Thread(()->{
            String avatarUrl = multipartFileToString(dto.getAvatar());
            directProducer.sendRegisterPassenger(registerMapper.toModel(dto,avatarUrl));
        }).start();
        return true;
    }
}
