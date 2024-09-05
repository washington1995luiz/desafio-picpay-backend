package br.com.washington.desafio_picpay_backend.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "transactions")
public class Transactions implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userSender;

    @Column(nullable = false)
    private String userReceive;

    @Column
    private LocalDateTime timestamp;

    @Column(nullable = false)
    private BigDecimal value;

    @ManyToMany
    @JoinTable(
            name = "user_transactions",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> user = new HashSet<>();

    public Transactions() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserSender() {
        return userSender;
    }

    public void setUserSender(String userSender) {
        this.userSender = userSender;
    }

    public String getUserReceive() {
        return userReceive;
    }

    public void setUserReceive(String userReceive) {
        this.userReceive = userReceive;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Set<User> getUser() {
        return user;
    }

    public void setUser(Set<User> user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return id == that.id && Objects.equals(userSender, that.userSender) && Objects.equals(userReceive, that.userReceive) && Objects.equals(timestamp, that.timestamp) && Objects.equals(value, that.value) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userSender, userReceive, timestamp, value, user);
    }
}
