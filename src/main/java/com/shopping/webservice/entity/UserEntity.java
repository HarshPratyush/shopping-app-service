package com.shopping.webservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shopping.webservice.entity.audit.Auditable;
import com.shopping.webservice.enums.AuthenticationProvider;
import com.shopping.webservice.enums.UserType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "user_details")
@Data
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String email;

    @Column(name = "image_url")
    private String imageUrl;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private AuthenticationProvider provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;
}
