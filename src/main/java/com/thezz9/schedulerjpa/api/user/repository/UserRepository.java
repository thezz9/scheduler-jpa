package com.thezz9.schedulerjpa.api.user.repository;

import com.thezz9.schedulerjpa.api.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    /** 유저 단건 조회 */
    default User findUserByIdOrElseThrow(Long id) {
        return findUserById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "아이디 " + id + "에 해당하는 사용자가 존재하지 않습니다."));
    }

}

