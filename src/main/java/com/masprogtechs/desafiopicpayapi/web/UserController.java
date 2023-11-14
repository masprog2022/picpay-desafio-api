package com.masprogtechs.desafiopicpayapi.web;

import com.masprogtechs.desafiopicpayapi.domain.User;
import com.masprogtechs.desafiopicpayapi.dtos.UserDTO;
import com.masprogtechs.desafiopicpayapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Usuário", description = "Endpoints para gerenciar usuários" )
public class UserController {

    private final UserService userService;
    @Operation(summary = "Registar um usuário", description = "Registar um usuário",
            responses = {
                    @ApiResponse(responseCode = "201", description = "usuário criado com sucesso",
                            content = @Content(mediaType = "application/json"))
            })
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody  UserDTO userDTO){
       User newUser = userService.createUser(userDTO);
       return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
    @Operation(summary = "Listar todos usuários", description = "Listar todos usuários",

            responses = {
                    @ApiResponse(responseCode = "200", description = "Todos usuários localizados com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)))
            })
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){

        List<User> users = userService.getAllUsers();

        return  new ResponseEntity<>(users, HttpStatus.OK);
    }

}
