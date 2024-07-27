package com.group.samrt.um.client.client.request.uml;

import io.github.jhipster.service.filter.StringFilter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UmApplicationRequestSearch {
    private int pageNumber;
    private int pageSize;
    private StringFilter fullname;
    private StringFilter username;
    private StringFilter phone;
    private StringFilter role;
    private StringFilter status;
}
