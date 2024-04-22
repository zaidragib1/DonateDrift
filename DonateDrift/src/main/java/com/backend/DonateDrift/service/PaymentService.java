//package com.backend.DonateDrift.service;
//
//import com.backend.DonateDrift.dtos.PaymentForFundraiser;
//import com.backend.DonateDrift.entity.Donor;
//import com.backend.DonateDrift.entity.Fundraiser;
//import com.backend.DonateDrift.entity.Payment;
//import com.backend.DonateDrift.entity.User;
//import com.backend.DonateDrift.exception.UserException;
//import com.backend.DonateDrift.repository.PaymentRepository;
//import com.stripe.Stripe;
//import com.stripe.exception.*;
//import com.stripe.model.Event;
//import com.stripe.model.PaymentIntent;
//import com.stripe.param.PaymentIntentCreateParams;
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
//
//@Slf4j
//@Service
//public class PaymentService {
////    @Autowired
////    private PaymentRepository paymentRepository;
////
////    @Autowired
////    private PayPalClient payPalClient;
////
////    public Payment savePayment(Payment payment) {
////        // Save the payment details to your database
////        return paymentRepository.save(payment);
////    }
////
////    public Map<String, Object> initiatePayPalPayment(Double amount) {
////        // Call PayPalClient to initiate payment and return the payment details
////        return payPalClient.initiatePayment(amount);
////    }
////
////    public void completePayPalPayment(HttpServletRequest request) {
////        // Call PayPalClient to complete payment and update payment status accordingly
////        payPalClient.completePayment(request);
////    }
//
//    private final FundraiserService fundraiserService;
//    private final UserService userService;
//    private final DonorService donorService;
//
//    @Value("${stripe-secret-key}")
//    private String stripeSecretKey;
//
//    @Value("${stripe-public-key}")
//    private String stripePublicKey;
//
//    @Autowired
//    public PaymentService(FundraiserService fundraiserService, UserService userService, DonorService donorService) {
//        this.fundraiserService = fundraiserService;
//        this.userService = userService;
//        this.donorService = donorService;
//    }
//
//    @PostConstruct
//    public void init() {
//        Stripe.apiKey = stripeSecretKey;
//    }
//
//
//    public PaymentIntent getPaymentIntentForFundraiser(PaymentForFundraiser paymentForFundraiser, Double donationAmount) throws StripeException, UserException {
//        PaymentIntent intent = null;
//        try {
//
//            Long donorId = paymentForFundraiser.getDonorId();
//            Long fundraiserId = paymentForFundraiser.getFundraiserId();
//
//            Donor donor = donorService.getDonorById(donorId);
//            Fundraiser fundraiser = fundraiserService.findFundraiserById(fundraiserId);
//
//            if (fundraiser == null) {
//                log.error("Couldn't found the fundraiser {}", fundraiserId);
//                return null;
//            }
//
//            // Update raisedAmount by adding the donated amount
//            Double currentRaisedAmount = fundraiser.getRaisedAmount() != null ? fundraiser.getRaisedAmount() : 0.0;
//            fundraiser.setRaisedAmount((long) (currentRaisedAmount + donationAmount));
//
//            // Save the updated Fundraiser object back to the database
//            fundraiserService.saveOrUpdateFundraiser(fundraiser);
//
//            PaymentIntentCreateParams params =
//                    PaymentIntentCreateParams.builder()
//                            .setAmount(Math.round(donationAmount * 100))
//                            .setCurrency("INR")
//
//                            .setAutomaticPaymentMethods(
//                                    PaymentIntentCreateParams.AutomaticPaymentMethods
//                                            .builder()
//                                            .setEnabled(true)
//                                            .build()
//                            )
//                            .build();
//
//            intent = PaymentIntent.create(params);
//
//        } catch (CardException e) {
//            log.error("Card exception occurred, status: {}, message: {}, for: {}, exception: {}", e.getCode(), e.getMessage(), paymentForFundraiser, e);
//            throw e;
//        } catch (RateLimitException e) {
//            // Too many requests made to the API too quickly
//            log.error("Too many requests made to the API too quickly, for: {}, exception: {}", paymentForFundraiser, e);
//            throw e;
//        } catch (InvalidRequestException e) {
//            // Invalid parameters were supplied to Stripe's API
//            log.error("Invalid parameters were supplied to Stripe's API, for: {}, exception: {}", paymentForFundraiser, e);
//            throw e;
//        } catch (AuthenticationException e) {
//            // Authentication with Stripe's API failed
//            // (maybe you changed API keys recently)
//            log.error("Authentication with Stripe's API failed, for: {}, exception: {}", paymentForFundraiser, e);
//            throw e;
//        } catch (StripeException e) {
//            // Display a very generic error to the user, and maybe send
//            // yourself an email
//            log.error("Exception occurred with Stripe payment gateway: {}", e);
//            throw e;
//        } catch (Exception e) {
//            log.error("Exception occurred: {}", e);
//            throw e;
//        }
//
//        return intent;
//    }
//
//    @Async
//    public void handlePostPaymentEvents(Event event) throws UserException {
//        PaymentIntent intent = (PaymentIntent) event.getData().getObject();
//        log.info(event.getType());
//
//        Map<String, String> metaData = intent.getMetadata();
//        log.info("Metadata: {}", metaData);
//        Long fundraiserId = Long.valueOf(metaData.getOrDefault("fundraiserId", null));
//
//        if (fundraiserId == null) {
//            log.error("FundraiserId is missing in metadata");
//            return;
//        }
//
//        Fundraiser fundraiser = fundraiserService.findFundraiserById(fundraiserId);
//
//        if (fundraiser == null) {
//            log.error("Couldn't find the fundraiser {}", fundraiserId);
//            return;
//        }
//
//        Double donationAmount = (double) intent.getAmount() / 100; // Convert to rupees
//
//        try {
//            switch (event.getType()) {
//                case "payment_intent.succeeded" : {
//                    try {
//                        Double currentRaisedAmount = fundraiser.getRaisedAmount() != null ? fundraiser.getRaisedAmount() : 0.0;
//                        fundraiser.setRaisedAmount((long) (currentRaisedAmount + donationAmount));
//
//                        fundraiserService.saveOrUpdateFundraiser(fundraiser);
//
//                        log.info("Donation succeeded for fundraiser {} with donation amount {}", fundraiser.getId(), donationAmount);
//                    } catch (Exception e) {
//                        log.error("Exception occurred while processing successful donation: {}", e);
//                    }
//                    break;
//                }
//
//                case "payment_intent.payment_failed" : {
//                    log.error("Donation payment failed for fundraiser {}.", fundraiser.getId());
//                    break;
//                }
//
//                case "payment_intent.canceled" : {
//                    log.error("Donation payment Cancelled for fundraiser {}.", fundraiser.getId());
//                    break;
//                }
//
//                default:
//                    log.warn("Unhandled event type: {}", event.getType());
//            }
//        } catch (Exception e) {
//            log.error("Exception occurred: {}", e);
//            // Handle any exception occurred during payment processing
//        }
//
//    }
//}
