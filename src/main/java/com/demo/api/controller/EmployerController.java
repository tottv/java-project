package com.demo.api.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.api.common.controller.AbstractResponseController;
import com.demo.api.dto.in.PageDtoIn;
import com.demo.api.entity.jpa.Employer;
import com.demo.api.service.EmployerService;

@RestController
@RequestMapping(value = "/employers",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
public class EmployerController extends AbstractResponseController {
    @Autowired
    private final EmployerService employerService;

    public EmployerController(EmployerService employerService) {
        this.employerService = employerService;
    }

    @GetMapping(value = "", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> list(@Valid PageDtoIn pageDtoIn) {
      return responseEntity(() -> {
          return this.employerService.list(pageDtoIn);
      });
    }
    
    @GetMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
      return responseEntity(() -> {
        return this.employerService.get(id);
      });
    }

    @PostMapping(value = "/add", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> add(@RequestBody Employer item) {
        return responseEntity(() -> {
            return employerService.add(item);
        });
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @RequestBody Employer item
    ) {
        return responseEntity(() -> {
            return employerService.update(id, item);
        });
    }
    @DeleteMapping(value = "/{id}", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return responseEntity(() -> {
            employerService.delete(id);
            return null;
        });
    }
}
