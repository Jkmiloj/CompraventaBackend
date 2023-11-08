package com.BuyAndSell.Compraventa.controller.buy;
import com.BuyAndSell.Compraventa.domain.BuyDto;
import com.BuyAndSell.Compraventa.domain.serviceBuy.BuyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/compra")
public class BuyController {
    BuyService buyService;

    @GetMapping(value = "/traer-compras")
    public List<BuyDto> getAll() {
        return buyService.getAll();
    }

    @PostMapping(value = "/guardar")
    public Integer save(@RequestBody BuyDto buyDto) {
        return buyService.save(buyDto);
    }

}