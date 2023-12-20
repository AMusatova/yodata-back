package ru.lanit.bpm.yodata.app.api;


public class DuplicateEntityException extends Exception {
    public DuplicateEntityException(String msg) {
        super(msg);
    }
}
