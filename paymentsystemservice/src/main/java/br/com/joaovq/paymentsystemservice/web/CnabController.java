package br.com.joaovq.paymentsystemservice.web;

import br.com.joaovq.paymentsystemservice.domain.CnabService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("cnab")
public class CnabController {

    private CnabService cnabService;

    public CnabController(CnabService cnabService) {
        this.cnabService = cnabService;
    }

    @CrossOrigin(origins = {"http://localhost:9090"})
    @PostMapping("upload")
    public String upload(@RequestParam("file") MultipartFile file) throws Exception {
        cnabService.uploadCnabFile(file);
        return "Processamento iniciado!";
    }

}
