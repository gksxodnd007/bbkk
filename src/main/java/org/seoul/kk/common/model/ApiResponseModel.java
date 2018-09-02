package org.seoul.kk.common.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponseModel<T> {

    private int code;
    private String msg;
    private T result;

}
