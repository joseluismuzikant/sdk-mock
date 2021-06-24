package com.veritran.sdk.exceptions;

public final class UndefinedEntrypoint extends RuntimeException {

    @Override
    public String getMessage() {
        return "Undefined Entrypoint";
    }
}
