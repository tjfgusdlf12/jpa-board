package jpaTestBoard.jpaboard.Common.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadResulException extends RuntimeException {
    private String errMsg;
    public BadResulException(String errMsg) {
        super(errMsg);
        this.errMsg =errMsg;
    }
}
