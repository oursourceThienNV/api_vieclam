package com.group.samrt.um.client.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryBaseRequestById {
    private Long id;
    private String idString;
}
