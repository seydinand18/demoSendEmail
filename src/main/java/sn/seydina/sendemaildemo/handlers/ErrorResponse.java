package sn.seydina.sendemaildemo.handlers;

import java.util.Map;

public record ErrorResponse (
        Map<String, String> errors
){ }
