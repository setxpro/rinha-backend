package com.setxpro.rinha.domain.dtos;

import java.util.List;
import java.util.UUID;

public record PersonDTO(UUID id, String nickname, String name, String birthday, List<String> stack) {
}
