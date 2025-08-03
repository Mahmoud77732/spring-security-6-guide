# ssecurity_oauth2_22

A Spring Boot application focused on demonstrating concepts and flows of OAuth 2.0 and OpenID Connect.

---

## Table of Contents

- [Introduction](#introduction)
- [What is OAuth 2.0?](#what-is-oauth-20)
- [OAuth 2.0 Terminology](#oauth-20-terminology)
- [OAuth 2.0 Grant Types](#oauth-20-grant-types)
  - [Authorization Code Grant](#1-authorization-code-grant)
  - [Client Credentials Grant](#2-client-credentials-grant)
  - [Device Code Grant](#3-device-code-grant)
  - [Refresh Token Grant](#4-refresh-token-grant)
  - [PKCE](#5-pkce)
  - [Implicit Grant (Legacy)](#6-implicit-grant-legacy)
  - [Password Grant (Legacy)](#7-password-grant-legacy)
- [OpenID Connect](#openid-connect)
- [References](#references)

---

## Introduction

OAuth 2.0 is an open authorization protocol built on IETF standards and licensed by the Open Web Foundation. It enables applications to obtain limited access to user accounts on an HTTP service, such as Google, without exposing user credentials.

---

## What is OAuth 2.0?

OAuth 2.0 allows you to give one app permissions to access your data from another app, or use features in another app on your behalf, **without sharing your password**. Think of the OAuth token as an "access card" that provides limited access, not the master key.

**Scenario Example:**
- A PhotoEditor app wants to let users log in with their Google account.
- Google issues a CLIENT ID & CLIENT SECRET to PhotoEditor.
- The user clicks "Sign up with Google" and is redirected to Google's login page.
- After authentication and consent, Google issues an access token and refresh token to PhotoEditor.
- PhotoEditor uses the access token to access Google Photos on behalf of the user.

---

## OAuth 2.0 Terminology

- **Resource Owner:** The user who owns the resource being accessed.
- **Client:** The application requesting access (e.g., PhotoEditor).
- **Authorization Server:** The server authorizing the client (e.g., Google Auth).
- **Resource Server:** The server providing the resource (e.g., Google Photos).
- **Scopes:** Permissions the client is requesting.

---

## OAuth 2.0 Grant Types

OAuth 2.0 specifies several grant types for different use cases:

### 1. Authorization Code Grant

**Most common grant type. Used to access protected resources on behalf of a user.**

**Flow:**
1. User wants to import Google Photos via PhotoEditor.
2. PhotoEditor redirects user to GoogleAuth for authorization.
3. User logs in and consents.
4. GoogleAuth redirects back with an authorization code.
5. PhotoEditor exchanges the code for an access token (and refresh token).
6. PhotoEditor fetches photos using the access token.

### 2. Client Credentials Grant

**Used for machine-to-machine (M2M) communication, without user interaction.**

**Flow:**
1. PhotoEditor requests a token from GoogleAuth using its client credentials.
2. GoogleAuth returns an access token.
3. PhotoEditor accesses protected resources using the token.

### 3. Device Code Grant

**Used for devices without a browser (e.g., Smart TVs, CLI tools).**

**Flow:**
1. Device requests device and user codes from GoogleAuth.
2. User enters the user code on another device.
3. Device polls GoogleAuth for the access token.

### 4. Refresh Token Grant

**Used to obtain a new access token using a long-lived refresh token.**

**Flow:**
1. Client attempts to use an expired access token.
2. Client requests a new access token using the refresh token.
3. GoogleAuth returns a new access token (and optionally a new refresh token).

> **Note:** Making refresh tokens never expire is not recommended. The `offline_access` scope is required for non-expiring refresh tokens.

### 5. PKCE (Proof Key for Code Exchange)

**Used by public clients (mobile/web apps) to prevent code interception.**

**Flow:**
1. Client sends a code challenge with the authorization request.
2. After user authentication, client exchanges the code and code verifier for an access token.

### 6. Implicit Grant (Legacy)

**Legacy method, now deprecated. Access token is returned directly in the browser.**

> **Note:** Use Authorization Code + PKCE instead.

### 7. Password Grant (Legacy)

**User provides username and password directly to the client.**

> **Note:** Considered insecure and deprecated. Avoid in modern applications.

---

## OpenID Connect

OpenID Connect is a protocol that sits on top of OAuth 2.0, adding authentication capabilities. While OAuth 2.0 provides authorization via access tokens, OpenID Connect introduces an ID token containing user identity information.

**Key Points:**
- Standardizes scopes: `openid`, `profile`, `email`, `address`
- Uses JWT for ID tokens
- Exposes a standardized `/userinfo` endpoint

**Flow Differences:**
- Initial request includes the `openid` scope.
- Final exchange returns both an access token and an ID token.

---

## References

- [OAuth 2.0 RFC](https://datatracker.ietf.org/doc/html/rfc6749)
- [OpenID Connect](https://openid.net/connect/)
- [Spring Security OAuth 2.0 Documentation](https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html)

---

> For more details, see the [OAuth 2.0 specification](https://datatracker.ietf.org/doc/html/rfc6749) and the [OpenID Connect specification](https://openid.net/connect/).