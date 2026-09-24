package org.example.nebulastripedemo

import com.stripe.model.Event
import com.stripe.model.checkout.Session
import com.stripe.net.Webhook
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class StripeWebhookController {

    @PostMapping("/stripe/webhook")
    fun handleWebhook(
        @RequestBody payload: String,
        @RequestHeader("Stripe-Signature") signature: String
    ): ResponseEntity<String> {

        println("Stripe webhook payload:")
        println(payload)

        val webhookSecret = System.getenv("STRIPE_WEBHOOK_SECRET")

        val event: Event = Webhook.constructEvent(
            payload,
            signature,
            webhookSecret
        )

        when (event.type) {
            "checkout.session.completed" -> {
                val stripeObject =
                    event.dataObjectDeserializer.`object`.orElse(null)

                if (stripeObject is Session) {
                    println("Checkout completed: ${stripeObject.id}")
                    println("Payment status: ${stripeObject.paymentStatus}")
                }
            }

            else -> {
                println("Unhandled Stripe event: ${event.type}")
            }
        }

        return ResponseEntity.ok("received")
    }
}