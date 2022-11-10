package com.franklin.testeb2ml.teste;

import com.franklin.testeb2ml.service.selicservice.SelicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/teste")
@RestController
public class arroz {

    @GetMapping("/arroz")
    public ResponseEntity<?> codigoUfsLiceHoje() throws Exception {
        return ResponseEntity.ok(SelicService.getSelic());
    }

}
