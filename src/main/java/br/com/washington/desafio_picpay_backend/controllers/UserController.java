package br.com.washington.desafio_picpay_backend.controllers;

import br.com.washington.desafio_picpay_backend.entity.User;
import br.com.washington.desafio_picpay_backend.models.UserVO;
import br.com.washington.desafio_picpay_backend.services.UserService;
import br.com.washington.desafio_picpay_backend.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/v1")
@Validated
@Tag(name = "User", description = "User Controller Requests")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Find one user by id", description = "Find one user by id", tags = "User", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    public UserVO findById(@PathVariable(value = "id") @NotNull @NotBlank long id){
       return service.findById(id);
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Find all users", description = "Find all users", tags = "User", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content =
                @Content(mediaType = MediaType.APPLICATION_JSON, array = @ArraySchema(schema = @Schema(implementation = UserVO.class)))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    public List<UserVO> findAll(){
        return service.findAll();
    }
    @Operation(summary = "Create a new user", description = "Create a new user", tags = "User", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public UserVO create(@RequestBody @NotBlank @NotNull User user){
        return service.create(user);
    }
}
