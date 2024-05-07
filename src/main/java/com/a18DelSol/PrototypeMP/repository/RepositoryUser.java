package com.a18DelSol.PrototypeMP.repository;

import com.a18DelSol.PrototypeMP.model.ModelUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RepositoryUser extends CrudRepository<ModelUser, Integer> {
    ModelUser findByUserMail(String userMail);

    @Query(nativeQuery=true,
    value="SELECT * FROM model_user"
    + " WHERE (:userName is null OR user_name regexp :userName)"
    + " AND (:userPass is null OR user_pass regexp :userPass)"
    + " AND (:userMail is null OR user_mail regexp :userMail)"
    + " AND (:userBirth is null OR user_birth = :user_birth)")
    Iterable<ModelUser> findUser(Optional<String> userName, Optional<String> userPass, Optional<String> userMail, Optional<LocalDate> userBirth);
}
