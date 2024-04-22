//package com.backend.DonateDrift.Controller;
//
//import com.backend.DonateDrift.dtos.StripeChargeDto;
//import com.backend.DonateDrift.dtos.StripeTokenDto;
//import com.backend.DonateDrift.service.StripeService;
//import lombok.AllArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/public/stripe")
//@AllArgsConstructor
//public class StripeApi {
//    private final StripeService stripeService;
//
//
//    @PostMapping("/card/token")
//    @ResponseBody
//    public StripeTokenDto createCardToken(@RequestBody StripeTokenDto model) {
//        return stripeService.createCardToken(model);
//    }
//
//    @PostMapping("/charge")
//    @ResponseBody
//    public StripeChargeDto charge(@RequestBody StripeChargeDto model) {
//        return stripeService.charge(model);
//    }
//
//}
