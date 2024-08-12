package com.backend.DonateDrift.Controller;

//import com.backend.DonateDrift.entity.Donor;
//import com.backend.DonateDrift.entity.Fundraiser;
//import com.backend.DonateDrift.service.FundraiserService;
//import com.backend.DonateDrift.service.PaypalService;
//import com.paypal.api.payments.Links;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.security.core.Authentication;
//import com.paypal.api.payments.Payment;
//import com.paypal.base.rest.PayPalRESTException;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.time.LocalDateTime;
//import java.util.Collections;
//
//@Controller
//@RequiredArgsConstructor
//@Slf4j
//@CrossOrigin(origins = "http://localhost:3000")
//public class PaypalController {
//    @Autowired
//    private PaypalService paypalService;
//    @Autowired
//    private FundraiserService fundraiserService;
//
//    @GetMapping("/")
//    public String home() {
//        return "index";
//    }
//
//
//    @PostMapping("/payment/create/{fundraiserId}")
//    public ResponseEntity<?> createPayment(
//            @PathVariable Long fundraiserId,
//            @RequestParam("method") String method,
//            @RequestParam("name") String name,
//            @RequestParam("amount") String amount,
//            @RequestParam("currency") String currency,
//            @RequestParam("description") String description,
//            HttpServletRequest request
//    ) {
//        try {
//            String cancelUrl = "http://localhost:8080/payment/cancel";
//            String successUrl = "http://localhost:8080/payment/success/" + fundraiserId;
//            request.getSession().setAttribute("donorName", name);
//
//            Payment payment = paypalService.createPayment(
//                    Double.valueOf(amount),
//                    "USD",
//                    "paypal",
//                    "sale",
//                    description,
//                    cancelUrl,
//                    successUrl,
//                    fundraiserId
//            );
//
//            for (Links links : payment.getLinks()) {
//                if (links.getRel().equals("approval_url")) {
//                    log.info("Approval URL: {}", links.getHref());
//                    return ResponseEntity.ok(Collections.singletonMap("url", links.getHref()));
//                }
//            }
//        } catch (PayPalRESTException e) {
//            log.error("Error occurred:: ", e);
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment");
//        }
//        return ResponseEntity.badRequest().body("No approval URL found");
//    }
//
//
//
//    @GetMapping("/payment/success/{fundraiserId}")
//    public String paymentSuccess(
//            @PathVariable Long fundraiserId,
//            @RequestParam("paymentId") String paymentId,
//            @RequestParam("PayerID") String payerId,
//            Authentication authentication,
//            HttpServletRequest request) {
//        try {
//            Payment payment = paypalService.executePayment(paymentId, payerId);
//            if ("approved".equals(payment.getState())) {
//
//                String name = (String) request.getSession().getAttribute("donorName");  // Retrieve name from session
//                if (name == null) {
//                    name = "Anonymous";
//                }
//                double amountDonated = Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal());
//                String donationDescription = payment.getTransactions().get(0).getDescription();
//
//                Fundraiser fundraiser = fundraiserService.findFundraiserById(fundraiserId);
//                if (fundraiser != null) {
//                    long updatedRaisedAmount = fundraiser.getRaisedAmount() + (long) amountDonated;
//                    fundraiser.setRaisedAmount(updatedRaisedAmount);
//
//                    // Create new donor entry
//                    Donor newDonor = new Donor();
//                    newDonor.setName(name);
//                    newDonor.setAmount((long) amountDonated);
//                    newDonor.setComment(donationDescription);
//                    newDonor.setDonatedAt(LocalDateTime.now());
//                    newDonor.setFundraiser(fundraiser);
//
//                    // Save the donor
//                    fundraiser.getDonors().add(newDonor);
//                    fundraiserService.saveOrUpdateFundraiser(fundraiser);
//
//                    return "redirect:/paymentSuccess";
//                } else {
//                    log.error("Fundraiser not found with ID: {}", fundraiserId);
//                    return "redirect:/paymentError";
//                }
//            } else {
//                log.warn("Payment not approved: state={}", payment.getState());
//                return "redirect:/paymentError";
import com.backend.DonateDrift.entity.Donor;
import com.backend.DonateDrift.entity.Fundraiser;
import com.backend.DonateDrift.exception.UserException;
import com.backend.DonateDrift.service.FundraiserService;
import com.backend.DonateDrift.service.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
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

    @PostMapping("/payment/create/{fundraiserId}")
    public ResponseEntity<?> createPayment(
            @PathVariable Long fundraiserId,
            @RequestParam("method") String method,
            @RequestParam("userId") Long userId,
            @RequestParam("name") String name,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description,
            HttpServletRequest request) {
        try {
            String encodedName = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());
            // Ensure these URLs are leading to your frontend routes that handle the logic post-payment
            String cancelUrl = "http://localhost:3000/payment/cancel" + fundraiserId;
            String successUrl = "http://localhost:8080/payment/success/" + fundraiserId
                    + "?donorName=" + encodedName + "&userId=" + userId;  // Ensure userId is appended

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
        } catch (PayPalRESTException | UnsupportedEncodingException e) {
            log.error("Error occurred:: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating payment");
        }
        return ResponseEntity.badRequest().body("No approval URL found");
    }



    @GetMapping("/payment/success/{fundraiserId}")
    public String paymentSuccess(
            @PathVariable Long fundraiserId,
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId,
            @RequestParam("donorName") String encodedName,
            @RequestParam("userId") Long userId,  // Directly use this parameter
            HttpServletRequest request) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if ("approved".equals(payment.getState())) {
                String name = URLDecoder.decode(encodedName, StandardCharsets.UTF_8.toString());

                double amountDonated = Double.parseDouble(payment.getTransactions().get(0).getAmount().getTotal());
                String donationDescription = payment.getTransactions().get(0).getDescription();

                Fundraiser fundraiser = fundraiserService.findFundraiserById(fundraiserId);
                if (fundraiser != null) {
                    long updatedRaisedAmount = fundraiser.getRaisedAmount() + (long) amountDonated;
                    fundraiser.setRaisedAmount(updatedRaisedAmount);

                    Donor newDonor = new Donor();
                    newDonor.setName(name);
                    newDonor.setUserId(userId);  // Use the directly passed userId
                    newDonor.setAmount((long) amountDonated);
                    newDonor.setComment(donationDescription);
                    newDonor.setDonatedAt(LocalDateTime.now());
                    newDonor.setFundraiser(fundraiser);

                    fundraiser.getDonors().add(newDonor);
                    fundraiserService.saveOrUpdateFundraiser(fundraiser);

                    return "redirect:http://localhost:3000/payment/success/" + fundraiserId + "/" + amountDonated;
                } else {
                    log.error("Fundraiser not found with ID: {}", fundraiserId);
                    return "redirect:http://localhost:3000/payment/Error" + fundraiserId;
                }
            } else {
                log.warn("Payment not approved: state={}", payment.getState());
                return "redirect:http://localhost:3000/payment/Error" + fundraiserId;
            }
        } catch (Exception e) {
            log.error("Error occurred while executing payment: ", e);
            return "redirect:http://localhost:3000/payment/Error" + fundraiserId;
        }
    }

    @GetMapping("/payment/cancel/{fundraiserId}")
    public String paymentCancel(
            @PathVariable Long fundraiserId) {
        return "redirect:http://localhost:3000/payment/Cancelled" + "/" + fundraiserId;
    }


}
