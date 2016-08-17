package moe.haruue.goodhabits.network.exception;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class RedrockApiException extends RuntimeException {

    public RedrockApiException() {
    }

    public RedrockApiException(String detailMessage) {
        super(detailMessage);
    }

    public RedrockApiException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public RedrockApiException(Throwable throwable) {

    }

}
