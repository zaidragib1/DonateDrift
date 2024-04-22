//package com.backend.DonateDrift.service;
//
//import com.paypal.api.payments.*;
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.PayPalRESTException;
//import org.springframework.stereotype.Component;
//
//import jakarta.servlet.http.HttpServletRequest;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class PayPalClient {
//    String clientId = "AT9S7AcKiV2KBxWR084wpCiqhrjMwbYwE6EwscMV7UnsposbxGlunl6XPjx4H79Cddm7BWvMang_PTSs";
//    String clientSecret = "EN-YU1VJnSC4eZKDzrFlcb-6RlvbT1QGDo8rnfg10ktljfSIGkMYqDeKgowoVo83_6y5X2vAyQVFdPOA";
//
//    public Map<String, Object> createPayment(String sum){
//        Map<String, Object> response = new HashMap<String, Object>();
//        Amount amount = new Amount();
//        amount.setCurrency("INR");
//        amount.setTotal(sum);
//        Transaction transaction = new Transaction();
//        transaction.setAmount(amount);
//        List<Transaction> transactions = new ArrayList<Transaction>();
//        transactions.add(transaction);
//
//        Payer payer = new Payer();
//        payer.setPaymentMethod("paypal");
//
//        Payment payment = new Payment();
//        payment.setIntent("sale");
//        payment.setPayer(payer);
//        payment.setTransactions(transactions);
//
//        RedirectUrls redirectUrls = new RedirectUrls();
//        redirectUrls.setCancelUrl("http://localhost:8080/cancel");
//        //returnUrl -> unde se redirectioneaza in aplicatie dupa ce user-ul confirma payment-ul
//        redirectUrls.setReturnUrl("http://localhost:8080/complete/payment");
//        payment.setRedirectUrls(redirectUrls);
//        Payment createdPayment;
//        try {
//            String redirectUrl = "";
//            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
//            createdPayment = payment.create(context); //poate genera PayPalRESTException
//            if(createdPayment!=null){ //daca s-a putut crea payment-ul
//                //linkul de redirectare va fi approval_url
//                List<Links> links = createdPayment.getLinks();
//                for (Links link:links) {
//                    if(link.getRel().equals("approval_url")){
//                        redirectUrl = link.getHref();
//                        break;
//                    }
//                }
//                response.put("status", "success");
//                response.put("redirect_url", redirectUrl);
//            }
//        } catch (PayPalRESTException e) {
//            System.out.println("S-a detectat o eroare in timpul etapei de creare a donatiei!");
//        }
//        return response;
//    }
//
//    //metoda care va completa payment-ul dupa ce user-ul il va confirma
//    public Map<String, Object> completePayment(HttpServletRequest req){
//        Map<String, Object> response = new HashMap();
//        Payment payment = new Payment();
//        payment.setId(req.getParameter("paymentId"));
//
//        PaymentExecution paymentExecution = new PaymentExecution();
//        paymentExecution.setPayerId(req.getParameter("PayerID"));
//        try {
//            APIContext context = new APIContext(clientId, clientSecret, "sandbox");
//            Payment createdPayment = payment.execute(context, paymentExecution);
//            if(createdPayment!=null){
//                response.put("status", "success");
//                response.put("payment", createdPayment);
//            }
//        } catch (PayPalRESTException e) {
//            System.err.println(e.getDetails());
//        }
//        return response;
//    }
//
//    public Map<String, Object> initiatePayment(Double amount) {
//        return null;
//    }
//}
