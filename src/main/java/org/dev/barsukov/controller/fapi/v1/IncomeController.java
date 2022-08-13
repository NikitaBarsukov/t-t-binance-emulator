package org.dev.barsukov.controller.fapi.v1;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dev.barsukov.service.CommonHolderService;
import org.dev.barsukov.service.TransactionService;
import org.dev.barsukov.service.dto.TransactionDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/fapi/v1/income")
public class IncomeController {
    private final TransactionService transactionService;
    private final CommonHolderService answerService;

    @ApiOperation(value = "Creates an history.")
    @GetMapping()
    public List<TransactionDto> getIncome(@RequestHeader("X-MBX-APIKEY") String apiKey,
                                          @RequestParam(required = false) String symbol,
                                          @RequestParam(required = false) String incomeType,
                                          @RequestParam(required = false) Long startTime,
                                          @RequestParam(required = false) Long endTime,
                                          @RequestParam(required = false) Integer limit,
                                          @RequestParam(required = false) Long recvWindow,
                                          @RequestParam(required = false) Long timestamp) {
        return transactionService.getAllTransactions(apiKey,
                symbol,
                incomeType,
                startTime,
                endTime,
                limit);

    }


    @ApiOperation(value = "Emulates async request for history downloading.")
    @GetMapping("asyn")
    public Object getIncomeAsync(@RequestHeader("X-MBX-APIKEY") String apiKey) {
        return answerService.getIncomeAsyncReq(apiKey);
    }
}