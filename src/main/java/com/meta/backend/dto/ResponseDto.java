package com.meta.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class ResponseDto<T> {

    private boolean success;
    private String errorCode;
    private T data;

    ResponseDto(boolean success){
        this.success = success;
    }

    public static <T> ResponseDto<T> ok(T data) {
        var response = new ResponseDto<T>(true);
        response.data = data;
        return response;
    }

    public static <T> ResponseDto<T> fail(String errorCode) {
        return fail(errorCode, null);
    }

    public static <T> ResponseDto<T> fail(String errorCode, T data){
        return new ResponseDto<>(false, errorCode, data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o instanceof ResponseDto) {
            ResponseDto<?> that = (ResponseDto<?>) o;
            return success == that.success &&
                    Objects.equals(errorCode, that.errorCode) &&
                    Objects.equals(data, that.data);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, errorCode, data);
    }
}
