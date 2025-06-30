# Mini-RPC Framework

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java Version](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![Vert.x](https://img.shields.io/badge/Vert.x-4.5.1-purple.svg)](https://vertx.io/)

基于 Vert.x 的高性能 RPC 框架，深度整合 Spring Boot 生态，提供企业级分布式服务调用解决方案。

## 核心架构

```mermaid

![架构图](document/images/deepseek_mermaid_20250630_42cda7.png)


graph TD
    A[服务提供者] -->|注册服务| B(Nacos 注册中心)
    C[服务消费者] -->|发现服务| B
    C -->|调用| A
    D[Spring Boot 应用] --> E[Mini-RPC Starter]
    E --> F[服务注册]
    E --> G[服务发现]
    E --> H[代理生成]





Copyright 2025 Bling Team
