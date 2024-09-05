package br.com.washington.desafio_picpay_backend.controllers;

import br.com.washington.desafio_picpay_backend.entity.Transactions;
import br.com.washington.desafio_picpay_backend.models.MessageTransaction;
import br.com.washington.desafio_picpay_backend.services.TransactionsService;
import br.com.washington.desafio_picpay_backend.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions/v1")
@Validated
@Tag(name = "Transactions", description = "Transactions Controller Request")
public class TransactionsController {

    @Autowired
    TransactionsService service;


    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    @Operation(summary = "Create a new transaction", description = "Create a new transaction", tags = "Transactions", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),

    })
    public ResponseEntity<MessageTransaction> createTransaction(@RequestBody @NotEmpty @NotNull Transactions transactions){
        return service.createTransaction(transactions);
    }
}
