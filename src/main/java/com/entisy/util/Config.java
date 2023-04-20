package com.entisy.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Config {
    private boolean unknown_command;
    private boolean execution_error;

    Config() {}
}
