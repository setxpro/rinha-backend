package com.setxpro.rinha.domain.entities;

import com.setxpro.rinha.domain.dtos.StackDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name="stack")
@Getter
@Setter
@Table(name="stack")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Stack {

    private static final long SerialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private UUID personId;

    public Stack(StackDTO stackDTO) {
        this.name = stackDTO.name();
        this.personId = stackDTO.personId();
    }
}
