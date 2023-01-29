package com.assignment.greengrocer.common.model.out;

import com.assignment.greengrocer.common.model.enums.ResponseType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Response<T> {

    private ResponseType responseType;
    private T data;

    public static Response<String> error(ResponseType responseType, String errorMessage) {
        return new Response(responseType, errorMessage);
    }

    public static <T> Response<T> success(T data) {
        return new Response(ResponseType.SUCCESS, data);
    }

}
