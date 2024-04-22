//package com.backend.DonateDrift.Controller;
//
//import com.backend.DonateDrift.entity.User;
//import com.backend.DonateDrift.service.FundraiserService;
//import com.backend.DonateDrift.service.PayPalClient;
//import com.backend.DonateDrift.service.PaymentService;
//import com.backend.DonateDrift.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import jakarta.servlet.http.HttpServletRequest;
//import java.util.Map;
//
//@Controller
//public class PaymentController {
//    @Autowired
//    private PaymentService paymentService;
//
//    @PostMapping("/donate")
//    public ResponseEntity<Map<String, Object>> initiatePayment(@RequestParam("amount") Double amount) {
//        Map<String, Object> paymentDetails = paymentService.initiatePayPalPayment(amount);
//        return new ResponseEntity<>(paymentDetails, HttpStatus.OK);
//    }
//
//    @GetMapping("/complete/payment")
//    public ResponseEntity<String> completePayment(HttpServletRequest request) {
//        paymentService.completePayPalPayment(request);
//        return new ResponseEntity<>("Payment completed successfully", HttpStatus.OK);
//    }
//
//}
