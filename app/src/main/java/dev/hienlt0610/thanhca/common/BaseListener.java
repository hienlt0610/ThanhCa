package dev.hienlt0610.thanhca.common;

/**
 * Created by hienl on 7/30/2017.
 */

public interface BaseListener<T> {
    void onSuccess(T data);

    void onError(String message);
}
