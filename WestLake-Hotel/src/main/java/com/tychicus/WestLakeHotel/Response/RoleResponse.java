package com.tychicus.WestLakeHotel.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class RoleResponse {

    private Long id;

    private String name;

    public RoleResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
