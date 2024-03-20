package com.kcj.management.shop.model.dto;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestApiRequest {
    private RestApiRequestCode code;
    private String message;
    private Object result;

    public RestApiRequest(Object o) {
        if(o == null) {
            code = RestApiRequestCode.VALUE_NOT_FOUND;
        } else {
            code = RestApiRequestCode.SUCCESS;
        }
        message = code.getMessage();
        result = o;
    }
}
