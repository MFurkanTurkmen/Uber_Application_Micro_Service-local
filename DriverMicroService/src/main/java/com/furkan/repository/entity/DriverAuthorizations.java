package com.furkan.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class DriverAuthorizations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long driverId;
    @Enumerated(EnumType.STRING)
    Autho autho;
}
