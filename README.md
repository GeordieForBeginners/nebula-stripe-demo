# Stripe Checkout Kotlin Demo

A small learning project demonstrating a Stripe payment flow using Kotlin, Gradle, Spring Boot and Stripe Checkout Sessions with embedded payment components.

## Technologies

- Kotlin
- Gradle
- Spring Boot
- Tomcat
- Stripe Java SDK
- Stripe.js
- Stripe Checkout Sessions
- Stripe Webhooks

## Configuration

The application expects the following environment variables:

STRIPE_SECRET_KEY
STRIPE_WEBHOOK_SECRET

The Stripe publishable test key is used by the browser-side Stripe.js integration.

Do not commit Stripe secret keys or webhook signing secrets to source control.

## Running locally
Start the Spring Boot application:

./gradlew bootRun

On Windows Command Prompt or PowerShell:

gradlew.bat bootRun

The application runs on:

http://localhost:8080

## Webhooks

For local webhook testing, use the Stripe CLI:

stripe listen --forward-to localhost:8080/stripe/webhook

The webhook controller currently handles:

checkout.session.completed

Other Stripe events are logged but not processed.

## Test payments

This project is intended for Stripe test mode only.

Use Stripe's published test card details when testing payment flows.

## Purpose

This repository is a personal learning project intended to explore modern Stripe integration patterns using Kotlin and Spring Boot.

## What the project demonstrates

- Creating a Stripe Checkout Session from a Kotlin backend
- Returning a Checkout Session client secret to the browser
- Rendering Stripe embedded payment components
- Confirming a test payment
- Redirecting back to the application after payment
- Receiving and verifying Stripe webhook events
- Handling `checkout.session.completed`
- Reading the resulting payment status

## Application flow

```text
Browser
  ↓
Spring Boot / Tomcat
  ↓
Stripe Checkout Session
  ↓
Stripe embedded payment component
  ↓
Customer confirms payment
  ↓
Stripe
  ↓
Webhook
  ↓
Spring Boot webhook controller


