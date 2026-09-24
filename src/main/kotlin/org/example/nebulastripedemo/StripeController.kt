package org.example.nebulastripedemo

import com.stripe.Stripe
import com.stripe.model.checkout.Session
import com.stripe.param.checkout.SessionCreateParams
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class StripeController {

    @PostMapping("/create-checkout-session")
    fun createCheckoutSession(): Map<String, String?> {

        Stripe.apiKey = System.getenv("STRIPE_SECRET_KEY")

        val params =
            SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setUiMode(SessionCreateParams.UiMode.ELEMENTS)
                .setReturnUrl(
                    "https://stripe.chrismccallum.co.uk/return?session_id={CHECKOUT_SESSION_ID}"
//                      "http://18.175.168.24:8080/return?session_id={CHECKOUT_SESSION_ID}"
//                    "http://localhost:8080/return?session_id={CHECKOUT_SESSION_ID}"
                )
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setQuantity(1)
                        .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("gbp")
                                .setUnitAmount(1000)
                                .setProductData(
                                    SessionCreateParams.LineItem.PriceData.ProductData
                                        .builder()
                                        .setName("Nebula Stripe Test")
                                        .build()
                                )
                                .build()
                        )
                        .build()
                )
                .build()

        val session = Session.create(params)

        return mapOf(
            "clientSecret" to session.clientSecret
        )
    }
}