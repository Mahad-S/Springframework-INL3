package se.yrgo.services;

import java.util.Collection;

import se.yrgo.domain.Call;
import se.yrgo.domain.Action;

import se.yrgo.services.calls.CallHandlingService;
import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.diary.DiaryManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;

public class CallHandlingServiceImpl implements CallHandlingService {

    private CustomerManagementService customerService;
    private DiaryManagementService diaryService;

    public void setCustomerService(CustomerManagementService customerService) {
        this.customerService = customerService;
    }

    public void setDiaryService(DiaryManagementService diaryService) {
        this.diaryService = diaryService;
    }

    @Override
    public void recordCall(String customerId,
                           Call newCall,
                           Collection<Action> actions)
            throws CustomerNotFoundException {

        customerService.recordCall(customerId, newCall);

    }
}