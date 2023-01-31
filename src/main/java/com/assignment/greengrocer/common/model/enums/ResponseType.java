package com.assignment.greengrocer.common.model.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ResponseType {
    SUCCESS,
    EXTERNAL_API_ERROR,
    USER_REQUEST_ERROR
}
