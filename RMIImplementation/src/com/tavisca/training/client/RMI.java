package com.tavisca.training.client;

import java.io.IOException;
import java.net.UnknownHostException;

public interface RMI {
    public String process(Item item) throws IOException;
    public String delete(Item item) throws IOException;

}
