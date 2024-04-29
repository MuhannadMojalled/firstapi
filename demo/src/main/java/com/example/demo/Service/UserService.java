package com.example.demo.Service;

import com.example.demo.DAO.UserRepo;
import com.example.demo.Entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public ResponseEntity<Object> createUser(UserEntity userEntity){
        try{
            if(userRepo.findByEmail(userEntity.getEmail()) != null){
                return new ResponseEntity<>("User With email"+userEntity.getEmail() +"already exists", HttpStatus.CONFLICT);
            }else{
            userRepo.save(userEntity);
            return new ResponseEntity<>("Successfully added user", HttpStatus.CREATED);}
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected  ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
