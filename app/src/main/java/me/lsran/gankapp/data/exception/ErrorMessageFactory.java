package me.lsran.gankapp.data.exception;

import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }

    /**
     * Creates a String representing an error message.
     *
     * @param exception An exception used as a condition to retrieve the correct error message.
     * @return {@link String} an error message.
     */
    public static String create(Exception exception) {
        String message = null;

        if (exception instanceof JsonSyntaxException) {  // 数据格式化错误
            message = "Data error";
        } else if (exception instanceof HttpException) {    // http异常
            message = "Http error";
        } else if (exception instanceof UnknownHostException || exception instanceof ConnectException || exception instanceof SocketTimeoutException) { // 未连接网络、DNS错误和超时
            message = "Network error";
        } else {
            message = "未知异常";
        }

        return message;
    }
}
