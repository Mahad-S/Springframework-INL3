package se.yrgo.services.calls;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import se.yrgo.domain.Call;
import se.yrgo.domain.Action;

import se.yrgo.services.customers.CustomerManagementService;
import se.yrgo.services.diary.DiaryManagementService;
import se.yrgo.services.customers.CustomerNotFoundException;

@Service
@Transactional
public class CallHandlingServiceImpl implements CallHandlingService {

    @Autowired
    private CustomerManagementService customerService;

    @Autowired
    private DiaryManagementService diaryService;

    @Override
    public void recordCall(String customerId,
                           Call newCall,
                           Collection<Action> actions)
            throws CustomerNotFoundException {

        customerService.recordCall(customerId, newCall);

        for (Action a : actions) {
            diaryService.recordAction(a);
        }
    }
}