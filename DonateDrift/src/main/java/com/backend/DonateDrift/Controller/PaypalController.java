package com.backend.DonateDrift.Controller;
//
//import com.backend.DonateDrift.service.PaypalService;
//import com.paypal.api.payments.Links;
//import com.paypal.api.payments.Payment;
//import com.paypal.base.rest.PayPalRESTException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.view.RedirectView;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//public class PaypalController {
//
//    private final PaypalService paypalService;
//
//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }
//
//    @PostMapping("/payment/create")
//    public RedirectView createPayment(
//            @RequestParam("method") String method,
//            @RequestParam("amount") String amount,
//            @RequestParam("currency") String currency,
//            @RequestParam("description") String description
//    ) {
//        try {
//            String cancelUrl = "http://localhost:8080/payment/cancel";
//            String successUrl = "http://localhost:8080/payment/success";
//            Payment payment = paypalService.createPayment(
//                    Double.valueOf(amount),
//                    currency,
//                    method,
//                    "sale",
//                    description,
//                    cancelUrl,
//                    successUrl
//            );
//
//            for (Links links: payment.getLinks()) {
//                if (links.getRel().equals("approval_url")) {
//                    return new RedirectView(links.getHref());
//                }
//            }
//        } catch (PayPalRESTException e) {
//            log.error("Error occurred:: ", e);
//        }
//        return new RedirectView("/payment/error");
//    }
//
//    @GetMapping("/payment/success")
//    public String paymentSuccess(
//            @RequestParam("paymentId") String paymentId,
//            @RequestParam("PayerID") String payerId
//    ) {
//        try {
//            Payment payment = paypalService.executePayment(paymentId, payerId);
//            if (payment.getState().equals("approved")) {
//                return "paymentSuccess";
//            }
//        } catch (PayPalRESTException e) {
//            log.error("Error occurred:: ", e);
//        }
//        return "paymentSuccess";
//    }
//
//    @GetMapping("/payment/cancel")
//    public String paymentCancel() {
//        return "paymentCancel";
//    }
//
//    @GetMapping("/payment/error")
//    public String paymentError() {
//        return "paymentError";
//    }
//}

import aj.org.objectweb.asm.ConstantDynamic;
import com.backend.DonateDrift.entity.Donor;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.entity.User;
import com.backend.DonateDrift.service.FundraiserService;
import com.backend.DonateDrift.service.PaypalService;
import com.paypal.api.payments.Links;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
public class PaypalController {
    @Autowired
    private PaypalService paypalService;
    @Autowired
    private FundraiserService fundraiserService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    //    @PostMapping("/payment/create")
//    public RedirectView createPayment(
//            @RequestParam("method") String method,
//            @RequestParam("amount") String amount,
//            @RequestParam("currency") String currency,
//            @RequestParam("description") String description
//    ) {
//        try {
//            String cancelUrl = "http://localhost:8080/payment/cancel";
//            String successUrl = "http://localhost:8080/payment/success";
//            Payment payment = paypalService.createPayment(
//                    Double.valueOf(amount),
//                    currency,
//                    method,
//                    "sale",
//                    description,
//                    cancelUrl,
//                    successUrl
//            );
//
////            for (Links links: payment.getLinks()) {
////                if (links.getRel().equals("approval_url")) {
////                    return new RedirectView(links.getHref());
////                }
////            }
//            for (Links links: payment.getLinks()) {
//                if (links.getRel().equals("approval_url")) {
//                    log.info("Redirecting to PayPal: {}", links.getHref());
//                    return new RedirectView(links.getHref());
//                }
//            }
//
//        } catch (PayPalRESTException e) {
//            log.error("Error occurred:: ", e);
//        }
//        return new RedirectView("/payment/error");
//    }
    @PostMapping("/payment/create/{fundraiserId}")
    public ResponseEntity<?> createPayment(
            @PathVariable Long fundraiserId,
            @RequestParam("method") String method,
            @RequestParam("name") String name,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description,
            HttpServletRequest request
    ) {
        try {
            String cancelUrl = "http://localhost:8080/payment/cancel";
            String successUrl = "http://localhost:8080/payment/success/" + fundraiserId;
            request.getSession().setAttribute("donorName", name);

            Payment payment = paypalService.createPayment(
                    Double.valueOf(amount),
                    "USD",
                    "paypal",
                    "sale",
                    description,
                    cancelUrl,
                    successUrl,
                    fundraiserId
            );

            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    log.info("Approval URL: {}", links.getHref());
                    return ResponseEntity.ok(Collections.singletonMap("url", links.getHref()));
                }
            }
        } catch (PayPalRESTException e) {
            log.error("Error occurred:: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment");
        }
        return ResponseEntity.badRequest().body("No approval URL found");
    }


//    @GetMapping("/payment/success")
//    public String paymentSuccess(
//            @RequestParam("paymentId") String paymentId,
//            @RequestParam("PayerID") String payerId) {
//        try {
//            log.info("Executing payment with Payment ID: {} and Payer ID: {}", paymentId, payerId);
//            Payment payment = paypalService.executePayment(paymentId, payerId);
//            log.info("Payment executed: {}", payment);
//            if (payment.getState().equals("approved")) {
//                return "paymentSuccess";
//            } else {
//                log.warn("Payment not approved: state={}", payment.getState());
//                return "paymentError";
//            }
//        } catch (PayPalRESTException e) {
//            log.error("Error occurred while executing payment: ", e);
//            return "paymentError";
//        }
//    }

//    @GetMapping("/payment/success")
//    public String paymentSuccess(
//            @RequestParam("paymentId") String paymentId,
//            @RequestParam("PayerID") String payerId) {
//        try {
//            Payment payment = paypalService.executePayment(paymentId, payerId);
//            if ("approved".equals(payment.getState())) {
//                // Extract fundraiser ID from the payment description
//                String description = payment.getTransactions().get(0).getDescription();
//                Long fundraiserId = Long.parseLong(description.split(" - Fundraiser ID: ")[1]); // Assuming fundraiser ID is parsed from description
//
//                // Extract the donation amount from the payment details
//                double amountDonated = Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal());
//
//                // Retrieve the fundraiser from the database
//                Fundraiser fundraiser = fundraiserService.findFundraiserById(fundraiserId);
//                if (fundraiser != null) {
//                    // Update the fundraiser's raised amount
//                    long updatedRaisedAmount = fundraiser.getRaisedAmount() + (long) amountDonated;
//                    fundraiser.setRaisedAmount(updatedRaisedAmount);
//                    fundraiserService.saveOrUpdateFundraiser(fundraiser);
//
//                    return "redirect:/paymentSuccess";
//                } else {
//                    log.error("Fundraiser not found with ID: {}", fundraiserId);
//                    return "redirect:/paymentError";  // Redirect to an error page or handle as necessary
//                }
//            } else {
//                log.warn("Payment not approved: state={}", payment.getState());
//                return "redirect:/paymentError";
//            }
//        } catch (Exception e) {
//            log.error("Error occurred while executing payment: ", e);
//            return "redirect:/paymentError";
//        }
//    }


    @GetMapping("/payment/success/{fundraiserId}")
    public String paymentSuccess(
            @PathVariable Long fundraiserId,
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId,
            Authentication authentication,
            HttpServletRequest request) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if ("approved".equals(payment.getState())) {

                String name = (String) request.getSession().getAttribute("donorName");  // Retrieve name from session
                if (name == null) {
                    name = "Anonymous";  // Default name if not found
                }
                double amountDonated = Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal());
                String donationDescription = payment.getTransactions().get(0).getDescription();

                Fundraiser fundraiser = fundraiserService.findFundraiserById(fundraiserId);
                if (fundraiser != null) {
                    long updatedRaisedAmount = fundraiser.getRaisedAmount() + (long) amountDonated;
                    fundraiser.setRaisedAmount(updatedRaisedAmount);

                    // Create new donor entry
                    Donor newDonor = new Donor();
                    newDonor.setName(name);
                    newDonor.setAmount((long) amountDonated);
                    newDonor.setComment(donationDescription);
                    newDonor.setDonatedAt(LocalDateTime.now());
                    newDonor.setFundraiser(fundraiser);

                    // Save the donor
                    fundraiser.getDonors().add(newDonor);
                    fundraiserService.saveOrUpdateFundraiser(fundraiser);

                    return "redirect:/paymentSuccess";
                } else {
                    log.error("Fundraiser not found with ID: {}", fundraiserId);
                    return "redirect:/paymentError";
                }
            } else {
                log.warn("Payment not approved: state={}", payment.getState());
                return "redirect:/paymentError";
            }
        } catch (Exception e) {
            log.error("Error occurred while executing payment: ", e);
            return "redirect:/paymentError";
        }
    }



    @GetMapping("/payment/cancel")
    public String paymentCancel() {
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError() {
        return "paymentError";
    }
}
