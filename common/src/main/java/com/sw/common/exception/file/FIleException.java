package com.sw.common.exception.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FIleException extends RuntimeException{

    private final String message;

}
