package com.setxpro.rinha.domain.entities;

import com.setxpro.rinha.domain.dtos.PersonDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name="person")
@Getter
@Setter
@Table(name="person")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Person {
    private static final long SerialVersionUID = 1L;

    @Id
    private UUID id;

    @NotBlank(message = "O apelido é obrigatório")
    @Size(max = 32, message = "O apelido deve ter no máximo 32 caracteres")
    private String nickname;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "A data de nascimento é obrigatória")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Formato de data inválido. Use AAAA-MM-DD")
    private String birthday;

    @Size(max = 5, message = "A lista de stacks deve ter no máximo 5 elementos")
    @Size(max = 32, message = "Cada elemento da lista de stacks deve ter no máximo 32 caracteres")
    @ElementCollection
    private List<String> stack;
   public Person(PersonDTO personDTO) {
       this.id = personDTO.id();
       this.nickname = personDTO.nickname();
       this.name = personDTO.name();
       this.birthday = personDTO.birthday();
       this.stack = personDTO.stack();
   }
}
