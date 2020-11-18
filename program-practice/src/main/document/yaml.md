# YAML
一种直观的数据序列化格式，语法相对xml更加简洁

## 基本规则
1. 大小写敏感
2. 使用缩进来代表层级关系
3. 进制使用tab缩进，只能使用空格缩进
4. 缩进长度无限制，只要元素对齐就表示这些元素在同一个层级
5. 使用#表示注释
6. 字符串可以不用引号标注

### 优先级
如果同时存在properties和yml的配置文件:  
- ApplicationContext.properties  
- ApplicationContext.yml  
在properties声明的属性会覆盖yml的声明的属性（同一个属性）  
