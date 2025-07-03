# Mini-RPC Framework

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java Version](https://img.shields.io/badge/Java-8+-orange.svg)](https://www.oracle.com/java/)
[![Vert.x](https://img.shields.io/badge/Vert.x-4.5.1-purple.svg)](https://vertx.io/)
[![Nacos](https://img.shields.io/badge/Nacos-1.4.6-green.svg)](https://nacos.io/)

基于 Vert.x 的轻量级高性能 RPC 框架，深度整合 Spring Boot 生态，提供轻量级分布式服务调用的组件

## 📌 项目特点
- **高性能通信**：基于 Vert.x 的异步网络框架处理高并发请求
- **服务管理**：集成 Nacos 实现服务注册与发现
- **无侵入设计**：通过注解实现服务暴露与调用
- **配置灵活**：通过 YAML 配置文件动态调整参数
- **负载均衡**：内置随机算法
- **双模连接**：支持注册中心与直连两种服务发现模式

## 📐 核心架构

```mermaid
graph TD
    A[生产者] -->|注册服务| B(Nacos 注册中心)
    C[消费者] -->|发现服务| B
    C -->|调用| A
    D[Spring Boot 应用] --> E[Starter]
    E --> F[服务注册]
    E --> G[服务发现]
    E --> H[代理生成]

```
## 🏗️ 技术栈

| 组件 | 版本 | 功能 |
|------|------|------|
|  **Vert.x** | `4.5.1` | 异步网络框架 |
|  **Nacos** | `1.4.6` | 服务注册与发现 |
|  **Spring Boot** | `2.7.x` | 依赖注入与配置管理 |
