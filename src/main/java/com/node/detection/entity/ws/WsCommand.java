package com.node.detection.entity.ws;

import lombok.Data;

@Data
public class WsCommand {

    private String command;
    private String source;
    private String password;
    private String value;
}
