# AutoTest_Android

📱 基于Appium的Android UI自动化测试框架

## 📋 简介

轻量级Android UI自动化测试框架，基于POM设计模式，使用Appium+TestNG实现高效的移动应用测试。

## 🔧 技术栈

- Java
- Appium
- TestNG
- Maven
- POI
- ExtentReports

## 📁 项目结构

```
.
├── apps/                  # 测试APK存放目录
├── src/
│   ├── main/java/
│   │   ├── driver/        # 驱动配置
│   │   ├── flow/          # 测试流程
│   │   ├── page/          # 页面对象模型
│   │   └── tools/         # 工具类
│   └── test/java/         # 测试用例
└── testImage/             # 测试图片资源
```

## ✨ 特性

- 页面对象模型设计
- 流程化测试步骤
- 灵活配置的Appium驱动
- 数据驱动测试支持
- 美观的测试报告

## 🚀 快速开始

```java
@Test
public void testDemo() {
    ConfigAppium config = new ConfigAppium();
    config.androidConfig("deviceId", "app.apk", "androidVersion");
    
    // 执行测试流程
    TestFlow flow = new TestFlow();
    flow.runTestCase();
}
```

## 📌 使用要求

- JDK 1.7+
- Appium Server
- Android SDK
- 配置好的Android设备或模拟器

## 📄 许可证

MIT
