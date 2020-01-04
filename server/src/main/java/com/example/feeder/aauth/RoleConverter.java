package com.example.feeder.aauth;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {

    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null)
            return null;

        return role.getRoleName();
    }

    @Override
    public Role convertToEntityAttribute(final String dbData) {
        if (dbData == null)
            return null;

        return Stream.of(Role.values())
                .filter(role -> role.getRoleName().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
