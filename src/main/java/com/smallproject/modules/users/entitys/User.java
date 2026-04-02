package com.smallproject.modules.users.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDateTime;
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_catalogue_id")
    private Long userCatalogueId;
    private String name;

    @Email(message = "564167457612vbsh")
    private String email;
    private String password;
    private String phone;
    private String image;
    private String address;

    @Column(name="created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected  void onCreated(){
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void  onUpdated(){
        updatedAt = LocalDateTime.now();
    }
}
