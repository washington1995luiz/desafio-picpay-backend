package br.com.washington.desafio_picpay_backend.mapper;

import br.com.washington.desafio_picpay_backend.entity.User;
import br.com.washington.desafio_picpay_backend.models.UserVO;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ParseMapper {

    private static final ModelMapper mapper = new ModelMapper();

    static {
        mapper.createTypeMap(User.class, UserVO.class).addMapping(User::getId, UserVO::setKey);
        mapper.createTypeMap(UserVO.class, User.class).addMapping(UserVO::getKey, User::setId);
    }

    public static <O,D> D parseObject(O origin, Class<D> destination){
        return mapper.map(origin, destination);
    }

    public static <O,D> List<D> parseListObject(List<O> origin, Class<D> destination){
        List<D> destinationObjects = new ArrayList<>();
        for(O o : origin){
               destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }
}
