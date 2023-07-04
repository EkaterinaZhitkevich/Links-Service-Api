package com.example.links_converter_api.exception;

@Deprecated
public class NullShortLinkException extends RuntimeException{
    public NullShortLinkException(String message) {
        super(message);
    }
}
