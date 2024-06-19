package com.taha.payment.handler;

import java.util.Map;

public record ErrorResponse (
        Map<String,String> errors
)
{


}
