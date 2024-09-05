package br.com.washington.desafio_picpay_backend.services;

import br.com.washington.desafio_picpay_backend.controllers.UserController;
import br.com.washington.desafio_picpay_backend.entity.User;
import br.com.washington.desafio_picpay_backend.exceptions.ExceptionResponse;
import br.com.washington.desafio_picpay_backend.exceptions.config.MessageException;
import br.com.washington.desafio_picpay_backend.exceptions.ResourceNotFoundException;
import br.com.washington.desafio_picpay_backend.exceptions.UserAlreadyExistsException;
import br.com.washington.desafio_picpay_backend.mapper.ParseMapper;
import br.com.washington.desafio_picpay_backend.models.UserVO;
import br.com.washington.desafio_picpay_backend.repositories.UserRepository;
import br.com.washington.desafio_picpay_backend.services.external.ExternalVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {

    @Autowired
    UserRepository repository;


    public UserVO findById(long id){
        var user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MessageException.USER_NOT_FOUND_FOR_THIS_ID));
        var vo = ParseMapper.parseObject(user, UserVO.class);
        vo.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        return vo;
    }

    public List<UserVO> findAll(){
        var users = ParseMapper.parseListObject(repository.findAll(), UserVO.class);
        users.forEach(u -> u.add(linkTo(methodOn(UserController.class).findById(u.getKey())).withSelfRel()));
        if(users.isEmpty()) throw new ResourceNotFoundException(MessageException.NO_USER_RECORD_IN_DATABASE);
        return users;
    }

    public UserVO create(User user) {
        var userVO = ParseMapper.parseObject(user, UserVO.class);
        Optional<User> document = repository.findByDocument(userVO.getDocument());
        if(document.isPresent()) throw new UserAlreadyExistsException(MessageException.USER_ALREADY_EXISTS_FOR_THIS_DOCUMENT);
        Optional<User> email = repository.findByEmail(userVO.getEmail());
        if(email.isPresent()) throw new UserAlreadyExistsException(MessageException.USER_ALREADY_EXISTS_FOR_THIS_EMAIL);

        return this.save(userVO);
    }

    public UserVO updateBalance(UserVO userVO){
        var vo = ParseMapper.parseObject(userVO, UserVO.class);
        vo.setBalance(userVO.getBalance());
        return this.save(vo);
    }

    private UserVO save(UserVO userVO){
        User entity = ParseMapper.parseObject(userVO, User.class);
        var persisted = ParseMapper.parseObject(repository.save(entity), UserVO.class);
        return persisted.add(linkTo(methodOn(UserController.class).findById(persisted.getKey())).withSelfRel());
    }

    public UserVO findByDocument(String document){
        var user = repository.findByDocument(document).orElseThrow(() -> new ResourceNotFoundException(MessageException.USER_NOT_FOUND_FOR_THIS_ID));
        var vo = ParseMapper.parseObject(user, UserVO.class);
        vo.add(linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel());
        return vo;
    }

}