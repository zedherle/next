package com.zed.next.domain;

public abstract class Usecase<Q extends Usecase.RequestValues, P extends Usecase.ResponseValue> {
    public Q mRequestValues;

    public UseCaseCallback<P> mUseCaseCallback;

    public void run() {
        executeUseCase(mRequestValues);
    }
    protected abstract void executeUseCase(Q requestValues);
    /**
     * Data passed to a request.
     */
    public interface RequestValues {
    }
    /**
     * Data received from a response.
     */
    public interface ResponseValue {
    }
    public interface UseCaseCallback<R> {
        void onSuccess(R response);
        void onError();
    }
}