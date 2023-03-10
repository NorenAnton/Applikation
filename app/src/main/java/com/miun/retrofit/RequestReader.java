package com.miun.retrofit;

public interface RequestReader<T> {
    public void run(T container);
}
