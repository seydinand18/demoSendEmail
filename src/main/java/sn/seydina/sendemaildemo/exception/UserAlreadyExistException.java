package sn.seydina.sendemaildemo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class UserAlreadyExistException extends RuntimeException {
    private String msg;
}
