package br.com.washington.desafio_picpay_backend.models;

import br.com.washington.desafio_picpay_backend.entity.Transactions;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@JsonPropertyOrder({ "id", "firstName", "lastName", "email", "document", "balance", "accountType"})
public class UserVO extends RepresentationModel<UserVO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private long key;

    @JsonProperty
    private String firstName;

    @JsonProperty
    private String lastName;

    @JsonProperty
    private String document;

    @JsonProperty
    private String email;

    @JsonIgnore
    private String password;

    @JsonProperty
    private AccountType accountType;

    @JsonProperty
    private BigDecimal balance;

    public UserVO(){}



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserVO userVO = (UserVO) o;
        return key == userVO.key && Objects.equals(firstName, userVO.firstName) && Objects.equals(lastName, userVO.lastName) && Objects.equals(document, userVO.document) && Objects.equals(email, userVO.email) && Objects.equals(password, userVO.password) && accountType == userVO.accountType && Objects.equals(balance, userVO.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, firstName, lastName, document, email, password, accountType, balance);
    }
}
