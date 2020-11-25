# Tengine
Tengine在Nginx基础上做了一些增强
## Tengine和Nginx
Nginx是一个高性能的**Http**和**反向代理**服务器，也是一个IMAP/IP/SMTP代理服务器。7层网络的应用

### Nginx相对于Apache的优点
- 轻量级:同样起web服务，比apache占用更少的内存及资源
- 抗并发:Nginx处理请求是`异步非阻塞`的，而apache是`阻塞型`的。在高并发下nginx能保持低资源、低能耗、高性能
- 高度模块化设计:编写模块相对简单
- 社区活跃

### Apache相比于Nginx的优点
- rewrite:URIRewrite,可以将动态的URL重写为静态的URL,静态的URI对搜索引擎更加友好
- 模块数量多:可以满足各种需求
- 稳定

### Nginx解决的问题
- 高并发
- 负载均衡
- 高可用
- 虚拟主机
- 伪静态: URLRewrite
- 动静分离
