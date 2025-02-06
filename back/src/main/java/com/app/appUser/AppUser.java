package com.app.appUser;

import com.app.appUser.profile.Profile;
import com.app.policy.Policy;
import com.app.enums.Role;
import com.app.riskanalysis.RiskAnalysis;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AppUser implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "app_user_g")
    @SequenceGenerator(name = "app_user_g", sequenceName = "app_user_seq", allocationSize = 1)
    private Long id;

    @Size(min = 1, max = 255, message = "username is required length 1-255")
    @NotEmpty(message = "username is required")
    private String username;
    @Size(min = 1, max = 255, message = "password is required length 1-255")
    @NotEmpty(message = "password is required")
    private String password;

    private boolean enabled = true;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CLIENT;

    @OneToOne(cascade = CascadeType.ALL)
    private Profile profile = new Profile();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<RiskAnalysis> orderingsOwner = new ArrayList<>();
    @OneToMany(mappedBy = "marketing", cascade = CascadeType.ALL)
    private List<RiskAnalysis> orderingsMarketing = new ArrayList<>();
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Policy> policies = new ArrayList<>();

    public AppUser(String username) {
        this.username = username;
    }

    public void set(AppUser user) {

    }

}