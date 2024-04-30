package com.example.demo.Service;

import com.example.demo.DAO.UserRepo;
import com.example.demo.Entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public ResponseEntity<Object> createUser(UserEntity userEntity){
        try{
            if(userRepo.findByEmail(userEntity.getEmail()) != null){
                return new ResponseEntity<>("User With email "+userEntity.getEmail() +" already exists. ", HttpStatus.CONFLICT);
            }else{
            userRepo.save(userEntity);
            userEntity.setStatus("Active");
            return new ResponseEntity<>("Successfully added user ", HttpStatus.CREATED);}
        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected Error  ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Object> findById(long id) {
        try{
            Optional<UserEntity> userEntity = userRepo.findById(id);
            if(userEntity.isPresent()) {
                if(userEntity.get().getStatus().equals("Active")){
                return new ResponseEntity<>(userEntity, HttpStatus.OK);}
            }
            return new ResponseEntity<>("User with user id "+id+" not found",HttpStatus.NOT_FOUND);


        }catch (Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("Unexpected error has occurred, check logs",HttpStatus.BAD_REQUEST);
        }
    }
    public ResponseEntity<Object> updateUser(UserEntity userEntity){
        try{
            UserEntity userToUpdate = userRepo.findByEmail(userEntity.getEmail());
            if(userToUpdate==null){
                return new ResponseEntity<>("user with email "+userEntity.getEmail()+" not found",HttpStatus.NOT_FOUND);
            }if(userEntity.getAge()!=0){
                userToUpdate.setAge(userEntity.getAge());
            }if(userEntity.getFirstName()!=null){
                userToUpdate.setFirstName(userEntity.getFirstName());
            }if(userEntity.getLastName()!=null){
                userToUpdate.setLastName(userEntity.getLastName());
            }if(userEntity.getPhoneNumber()!=null){
                userToUpdate.setPhoneNumber(userEntity.getPhoneNumber());
            }
            userRepo.save(userToUpdate);
            return new ResponseEntity<>(userToUpdate, HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected Error  ", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> deleteUser(UserEntity userEntity){
        try{
            UserEntity userToDelete = userRepo.findByEmail(userEntity.getEmail());
            if(userToDelete==null){
                return new ResponseEntity<>("user with email "+userEntity.getEmail()+" not found",HttpStatus.NOT_FOUND);
            }if(userToDelete.getStatus().isBlank() || userToDelete.getStatus().equals("Active") ){
                userToDelete.setStatus("Inactive");
                userRepo.save(userToDelete);
                return new ResponseEntity<>("user with email "+userEntity.getEmail()+" was deleted sucessflly ", HttpStatus.OK);
            }
            return new ResponseEntity<>("user with email "+userEntity.getEmail()+" was is already inc=active ", HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e.toString());
            return new ResponseEntity<>("An unexpected Error  ", HttpStatus.BAD_REQUEST);
        }
    }
}
