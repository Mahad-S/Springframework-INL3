package se.yrgo.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import se.yrgo.services.calls.CallHandlingService;
import se.yrgo.domain.Call;

public class SimpleClient {

    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("application.xml");

        // ✅ FIXED: use type instead of string
        CallHandlingService service =
                context.getBean(CallHandlingService.class);

        System.out.println("Spring started OK: " + service);

        try {
            Call call = new Call();
            call.setTimeAndDate(new java.util.Date());
            call.setNotes("Test call");

            service.recordCall("OB74", call, new java.util.ArrayList<>());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}