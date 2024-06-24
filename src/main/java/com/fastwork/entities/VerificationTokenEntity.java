package com.fastwork.entities;

import com.fastwork.enums.TypeToken;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification_token")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificationTokenEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

    private String token;

    private Date expiryDate;

    @Enumerated(EnumType.STRING)
    private TypeToken type;

    public VerificationTokenEntity(String token, UserEntity user, TypeToken type) {
        this.token = token;
        this.user = user;
        this.expiryDate = calculateExpiryDate(720);
        this.type = type;
    }

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }
}
