package com.assignment.greengrocer.greengrocer.external;

import java.util.List;

public interface ExternalAdaptor {
    String getToken();
    List<String> getCategory();
    long getPrice(String name);
}
