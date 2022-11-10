package com.franklin.testeb2ml.request;

import com.franklin.testeb2ml.model.InstallmentsDefinition;
import com.franklin.testeb2ml.model.RequestPaymentAndProduct;
import com.franklin.testeb2ml.service.selicservice.SelicService;
import com.franklin.testeb2ml.util.DecimalFormatter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    public static void main(String[] args) {

    }

    @PostMapping("/parcelas")
    public List<InstallmentsDefinition> getResult(@RequestBody RequestPaymentAndProduct requestPaymentAndProduct) throws Exception {

        if(requestPaymentAndProduct.getProduct().getPrice() <= 0){
            throw new Exception("Preço inválido");
        }

        if((requestPaymentAndProduct.getPayment().getEntry() > requestPaymentAndProduct.getProduct().getPrice())||
                (requestPaymentAndProduct.getPayment().getEntry() < 0)){
            throw new Exception("Entrada inválida");
        }

        if((requestPaymentAndProduct.getPayment().getInstallments() > 12)||(requestPaymentAndProduct.getPayment().getInstallments() <= 0)){
            throw new Exception("Parcelas somente de 1 a 12. Parcela inserida: " + requestPaymentAndProduct.getPayment().getInstallments());
        }

        List<InstallmentsDefinition> list = new ArrayList<>();
        Double selic = DecimalFormatter.decimalFormatter(Double.valueOf(SelicService.getSelic().getValor()));

        for(int i = 0; i < requestPaymentAndProduct.getPayment().getInstallments(); i++){
            InstallmentsDefinition installmentsDefinition = new InstallmentsDefinition();
            Double newPrice;
            if(requestPaymentAndProduct.getPayment().getInstallments() > 6){
                newPrice = (requestPaymentAndProduct.getProduct().getPrice() - requestPaymentAndProduct.getPayment().getEntry())/
                        requestPaymentAndProduct.getPayment().getInstallments();

                installmentsDefinition.setPrice(newPrice + (newPrice * selic));
                installmentsDefinition.setInstallmentNum(i+1);
                installmentsDefinition.setTax(DecimalFormatter.decimalFormatter(newPrice * selic));

                list.add(installmentsDefinition);
            }else {
                newPrice = (requestPaymentAndProduct.getProduct().getPrice() - requestPaymentAndProduct.getPayment().getEntry())/
                        requestPaymentAndProduct.getPayment().getInstallments();

                installmentsDefinition.setPrice(newPrice);
                installmentsDefinition.setInstallmentNum(i+1);
                installmentsDefinition.setTax(Double.parseDouble("0"));

                list.add(installmentsDefinition);
            }

        }

        return list;
    }

}
