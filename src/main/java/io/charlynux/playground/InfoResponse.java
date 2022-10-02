package io.charlynux.playground;

import java.util.Objects;

public class InfoResponse {

    private final Boolean error;
    private final String message;
    private final Info data;

    private InfoResponse(Boolean error, String message, Info data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public static InfoResponse success(Info data) {
        return new InfoResponse(false, null, data);
    }

    public static InfoResponse failure(String message) {
        return new InfoResponse(true, message, null);
    }

    @Override
    public String toString() {
        return "InfoResponse{" +
                "error=" + error +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InfoResponse that = (InfoResponse) o;
        return Objects.equals(error, that.error)
                && Objects.equals(message, that.message)
                && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(error, message, data);
    }
}
