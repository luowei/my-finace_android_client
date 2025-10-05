# my-finace_android_client

个人理财记账 Android 客户端应用

## 项目简介

这是一个基于 Android 平台的个人理财记账客户端应用程序。应用提供了简单的登录界面和日常开支记录功能，通过网络 API 将数据提交到云端服务器（百度云 BAE 平台）进行存储和管理。

## 技术栈

- **开发语言**: Java
- **开发平台**: Android
- **最低 SDK 版本**: API Level 10 (Android 2.3.3)
- **构建工具**: Ant / Android SDK
- **网络通信**: HttpURLConnection
- **后端服务**: 百度云 BAE (Baidu App Engine)
- **开发工具**: IntelliJ IDEA / Android Studio

## 功能特性

### 1. 用户登录
- 用户名和密码输入
- 数据传递到主界面

### 2. 日常开支记录
- **金额输入**: 记录消费金额
- **星期输入**: 记录消费星期
- **日期输入**: 记录消费日期
- **描述输入**: 添加消费备注说明
- **数据提交**: 通过 HTTP 请求提交到云端服务器

### 3. 网络通信
- 使用 HttpURLConnection 发送 GET 请求
- 处理服务器响应
- 错误处理和异常捕获
- Toast 消息提示

## 项目结构

```
my-finace_android_client/
├── src/                              # 源代码目录
│   └── com/
│       └── rootls/
│           ├── finance/              # 主要业务逻辑
│           │   ├── MyActivity.java      # 登录界面 Activity
│           │   └── OtherActivity.java   # 记账界面 Activity
│           └── util/                 # 工具类
│               └── URLHelper.java       # URL 辅助工具类
├── res/                              # 资源文件目录
│   ├── layout/                       # 布局文件
│   │   ├── main.xml                     # 登录界面布局
│   │   └── other.xml                    # 记账界面布局
│   ├── values/                       # 值资源
│   └── drawable/                     # 图片资源
├── AndroidManifest.xml               # Android 清单文件
├── build.xml                         # Ant 构建脚本
├── project.properties                # 项目配置
├── proguard-project.txt              # ProGuard 混淆配置
├── ant.properties                    # Ant 属性配置
├── .gitignore
└── README.md
```

## 主要文件说明

### 1. MyActivity.java (登录界面)

主要功能：
- 用户名和密码输入框
- 登录按钮点击事件处理
- 使用 Intent 传递用户信息到 OtherActivity

**核心代码**:
```java
Intent intent = new Intent(MyActivity.this, OtherActivity.class);
intent.putExtra("username", username_editText.getText().toString());
intent.putExtra("password", password_editText.getText().toString());
startActivity(intent);
```

### 2. OtherActivity.java (记账界面)

主要功能：
- 接收登录界面传递的用户信息
- 提供金额、星期、日期、描述输入框
- 添加按钮提交数据到云端
- HTTP 网络请求处理

**API 接口**:
```java
// 后端服务地址（百度云 BAE）
http://luowei.duapp.com/finance/addDaytip

// 请求参数
?money={金额}&week={星期}&tipDateStr={日期}&desc={描述}
```

**网络请求代码**:
```java
URL url = new URL(finance_add_url + "?money=" + money +
                  "&week=" + week + "&tipDateStr=" + date +
                  "&desc=" + desc);
HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
InputStream in = new BufferedInputStream(urlConnection.getInputStream());
String result = readInStream(in);
```

### 3. URLHelper.java

URL 辅助工具类，提供 URL 相关的辅助方法。

### 4. AndroidManifest.xml

应用配置清单：
- **包名**: com.rootls.finance
- **最低 SDK**: API Level 10 (Android 2.3.3)
- **权限**:
  - INTERNET: 网络访问
  - ACCESS_WIFI_STATE: WiFi 状态访问
  - CALL_PHONE: 拨打电话
  - READ_CONTACTS: 读取联系人
  - 等多种系统权限

## 依赖要求

### 开发环境
- **JDK**: 1.6 或更高
- **Android SDK**: API Level 10 或更高
- **开发工具**:
  - IntelliJ IDEA
  - Android Studio
  - Eclipse + ADT

### 运行环境
- **Android 系统**: 2.3.3 (Gingerbread) 或更高版本

## 安装和运行

### 1. 环境准备

```bash
# 安装 Android SDK
# 配置 ANDROID_HOME 环境变量

# 检查 Android SDK 工具
android list target
```

### 2. 克隆项目

```bash
git clone <repository-url>
cd my-finace_android_client
```

### 3. 导入项目

#### 方法 1: 使用 Android Studio
1. 打开 Android Studio
2. File → Open → 选择项目目录
3. 等待 Gradle 同步完成
4. 运行项目

#### 方法 2: 使用 IntelliJ IDEA
1. 打开 IntelliJ IDEA
2. File → Open → 选择项目目录
3. 配置 Android SDK
4. 运行项目

#### 方法 3: 使用 Ant 命令行
```bash
# 更新项目配置
android update project -p . -t android-10

# 编译项目
ant debug

# 安装到设备
adb install -r bin/my-finace_android_client-debug.apk
```

### 4. 运行应用

```bash
# 连接 Android 设备或启动模拟器
adb devices

# 在 IDE 中点击运行按钮，或使用命令
adb shell am start -n com.rootls.finance/.MyActivity
```

## 使用说明

### 登录界面
1. 输入用户名
2. 输入密码
3. 点击"登录"按钮进入记账界面

### 记账界面
1. **金额输入**: 输入消费金额（支持小数）
2. **星期输入**: 输入消费星期（如：星期一）
3. **日期输入**: 输入消费日期（如：2014-04-06）
4. **描述输入**: 输入消费说明（如：午餐）
5. 点击"添加"按钮提交数据
6. 等待服务器响应，查看 Toast 提示

## 权限说明

应用请求的主要权限：

| 权限 | 用途 |
|------|------|
| INTERNET | 访问网络，提交数据到云端服务器 |
| ACCESS_WIFI_STATE | 检查 WiFi 连接状态 |
| CHANGE_NETWORK_STATE | 更改网络状态 |
| CALL_PHONE | 拨打电话（预留功能） |
| READ_CONTACTS | 读取联系人（预留功能） |
| VIBRATE | 设备振动 |
| WRITE_SETTINGS | 写入系统设置 |

## API 接口说明

### 添加日常开支记录

**接口地址**:
```
http://luowei.duapp.com/finance/addDaytip
```

**请求方法**: GET

**请求参数**:
| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| money | Float | 是 | 消费金额 |
| week | String | 是 | 星期 |
| tipDateStr | String | 是 | 日期 |
| desc | String | 是 | 描述 |

**请求示例**:
```
http://luowei.duapp.com/finance/addDaytip?money=50.5&week=星期一&tipDateStr=2014-04-06&desc=午餐
```

**响应格式**:
服务器返回处理结果（具体格式取决于后端实现）

## 后端服务

项目后端服务部署在百度云 BAE (Baidu App Engine) 平台：
- **域名**: luowei.duapp.com
- **路径**: /finance/addDaytip
- **平台**: 百度云 BAE（可能已下线，需要迁移到其他平台）

**注意**: 百度云 BAE 服务可能已经停止运营，如需使用需要：
1. 更换后端服务器
2. 修改 `OtherActivity.java` 中的 `finance_add_url` 地址
3. 重新编译和部署

## 开发注意事项

### 1. 网络请求在主线程
当前代码在主线程中执行网络请求，在 Android 3.0+ 会抛出 `NetworkOnMainThreadException` 异常。

**建议修改**:
```java
// 使用 AsyncTask 或线程执行网络请求
new AsyncTask<Void, Void, String>() {
    @Override
    protected String doInBackground(Void... params) {
        // 网络请求代码
        return result;
    }

    @Override
    protected void onPostExecute(String result) {
        // 更新 UI
        Toast.makeText(OtherActivity.this, result, Toast.LENGTH_LONG).show();
    }
}.execute();
```

### 2. 数据验证
当前代码缺少输入数据验证，建议添加：
- 金额格式验证
- 日期格式验证
- 空值检查

### 3. 错误处理
增强错误处理机制：
- 网络连接失败处理
- 服务器错误响应处理
- 用户友好的错误提示

### 4. 安全性
- 密码应加密传输
- 使用 HTTPS 替代 HTTP
- 添加数据签名验证

## 技术改进建议

1. **使用现代网络库**:
   - Retrofit + OkHttp
   - Volley

2. **数据持久化**:
   - 添加本地数据库（SQLite）
   - 离线数据同步

3. **UI 优化**:
   - 使用 Material Design
   - 添加日期选择器
   - 改进布局和交互

4. **功能扩展**:
   - 查看历史记录
   - 数据统计和图表
   - 分类管理
   - 预算提醒

5. **架构优化**:
   - 使用 MVP 或 MVVM 架构
   - 依赖注入（Dagger）
   - RxJava 响应式编程

## 已知问题

1. 网络请求在主线程执行（Android 3.0+ 会崩溃）
2. 缺少输入验证
3. HTTP 明文传输，安全性低
4. 后端服务可能已下线
5. 缺少错误处理机制

## 版本历史

- **v1.0**: 初始版本，基本登录和记账功能

## 许可证

本项目为个人学习项目，仅供学习参考使用。

## 作者

- luowei
- 创建日期: 2014-04-06

## 相关资源

- [Android 开发者官网](https://developer.android.com/)
- [百度云 BAE 文档](https://cloud.baidu.com/)（已停止服务）
- [Android 网络编程](https://developer.android.com/training/basics/network-ops)
