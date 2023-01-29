package com.assignment.greengrocer.greengrocer;

import com.assignment.greengrocer.greengrocer.external.GreengrocerType;
import com.assignment.greengrocer.greengrocer.service.GreengrocerService;
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

    @GetMapping("/{greengrocerType}")
    public String getToken(@PathVariable String greengrocerType) {
        return greengrocerService.getToken(GreengrocerType.of(greengrocerType));
    }
}
