package com.boot.entities;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Entity
@Builder
public class SocailLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long socialLinkId;
    private String title;
    private String link;
    @ManyToOne
    private Contact contact;

}
