package se.yrgo.services.calls;

import java.util.Collection;

import se.yrgo.domain.Call;
import se.yrgo.domain.Action;
import se.yrgo.services.customers.CustomerNotFoundException;

public interface CallHandlingService {

    void recordCall(String customerId,
                    Call newCall,
                    Collection<Action> actions)
            throws CustomerNotFoundException;
}