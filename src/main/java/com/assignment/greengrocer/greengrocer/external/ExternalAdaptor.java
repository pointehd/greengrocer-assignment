package com.assignment.greengrocer.greengrocer.external;

import com.assignment.greengrocer.greengrocer.model.PriceResponse;
import java.util.List;

public interface ExternalAdaptor {
    String getToken();
    List<String> getCategory();
    PriceResponse getPrice(String name);
}
