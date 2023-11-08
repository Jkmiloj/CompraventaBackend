package com.BuyAndSell.Compraventa.persistence.repositoryBuy;

import com.BuyAndSell.Compraventa.domain.BuyDto;

import java.util.List;

public interface BuyRepository {

    List<BuyDto> getAll();

    Integer save(BuyDto buyDto);

}
