package com.assignment.greengrocer.greengrocer;

import com.assignment.greengrocer.common.model.out.Response;
import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import com.assignment.greengrocer.greengrocer.model.PriceResponse;
import com.assignment.greengrocer.greengrocer.service.GreengrocerService;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/greengrocer")
@RestController
public class GreengrocerController {

    private final GreengrocerService greengrocerService;

    @GetMapping
    public Response<List<GreengrocerType>> get() {
        return Response.success(Arrays.asList(GreengrocerType.values()));
    }

    @GetMapping("/{greengrocerType}")
    public Response<List<String>> getProduct(@PathVariable String greengrocerType) {
        return Response.success(greengrocerService.getProduct(GreengrocerType.of(greengrocerType)));
    }

    @GetMapping("/{greengrocerType}/{name}")
    public Response<PriceResponse> getToken(
        @PathVariable String greengrocerType,
        @PathVariable String name) {
        return Response.success(
            greengrocerService.getPrice(GreengrocerType.of(greengrocerType), name));
    }
}
