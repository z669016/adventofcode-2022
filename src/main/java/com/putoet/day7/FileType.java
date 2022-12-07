package com.putoet.day7;

public enum FileType {
    DIRECTORY, FILE;

    @Override
    public String toString() {
        return switch (this) {
            case DIRECTORY -> "dir";
            case FILE -> "file";
        };
    }
}
