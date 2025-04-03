package com.thezz9.schedulerjpa.api.user.controller;

import com.thezz9.schedulerjpa.api.user.dto.UserCreateRequestDto;
import com.thezz9.schedulerjpa.api.user.dto.UserDeleteRequestDto;
import com.thezz9.schedulerjpa.api.user.dto.UserResponseDto;
import com.thezz9.schedulerjpa.api.user.dto.UserUpdateRequestDto;
import com.thezz9.schedulerjpa.api.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /** 유저 생성 */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserCreateRequestDto dto) {
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED);
    }

    /** 유저 전체 조회 */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    /** 유저 단건 조회 */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }

    /** 유저 수정 */
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long id,
                                                      @Valid @RequestBody UserUpdateRequestDto dto) {
        return new ResponseEntity<>(userService.updateUser(id, dto), HttpStatus.OK);
    }

    /** 유저 삭제 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @Valid @RequestBody UserDeleteRequestDto dto) {
        userService.deleteUser(id, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
