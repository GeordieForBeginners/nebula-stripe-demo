package org.example.nebulastripedemo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReturnController {

    @GetMapping("/return")
    fun returnPage(
        @RequestParam("session_id") sessionId: String
    ): String {
        return "Payment completed. Checkout Session: $sessionId"
    }
}