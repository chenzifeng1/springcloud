# Spring framework

## 编译源码

spring源码git仓库地址: [https://github.com/spring-projects/spring-framework/](https://github.com/spring-projects/spring-framework/)

之后可以根据自己需求下载不同版本分支的代码

![Spring%20framework%2080df3c81d678438c85eb0f8059dafbf0/Untitled.png](Spring%20framework%2080df3c81d678438c85eb0f8059dafbf0/Untitled.png)

我们也可以去把整个项目clone之后，去checkout到对应的tag版本

`git clone [https://github.com/spring-projects/spring-framework.git](https://github.com/spring-projects/spring-framework.git)`

clone之后我们可以根据tag的不同版本号进行chackout，在本次源码学习中，我选择的 `v5.2.3.RELEASE` 的tag，chackout命令如下

`git checkout v5.2.3.RELEASE`

编译下载好的代码：

- 先编译oxm子项目，在文件中打开cmd 命令： `gradlew :spring-oxm:compileTestJava`
- 之后编译其他的项目，命令：`gradlew build`

编译好之后，打开项目，会发现每个项目底下的文件结构如下

![Spring%20framework%2080df3c81d678438c85eb0f8059dafbf0/Untitled%201.png](Spring%20framework%2080df3c81d678438c85eb0f8059dafbf0/Untitled%201.png)

**build:** build目录下包括编译之后的class文件，依赖libs，日志logs，资源文件resources，临时文件tmp等。

**src:**下面就是我们的源码了

源码解析：

refresh() 方法，这个方法需要好好看一下

```java
@Override
public void refresh() throws BeansException, IllegalStateException {
   synchronized (this.startupShutdownMonitor) {
      // Prepare this context for refreshing.
      prepareRefresh();

      // Tell the subclass to refresh the internal bean factory.
      ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

      // Prepare the bean factory for use in this context.
      prepareBeanFactory(beanFactory);

      try {
         // Allows post-processing of the bean factory in context subclasses.
         postProcessBeanFactory(beanFactory);

         // Invoke factory processors registered as beans in the context.
         invokeBeanFactoryPostProcessors(beanFactory);

         // Register bean processors that intercept bean creation.
         registerBeanPostProcessors(beanFactory);

         // Initialize message source for this context.
         initMessageSource();

         // Initialize event multicaster for this context.
         initApplicationEventMulticaster();

         // Initialize other special beans in specific context subclasses.
         onRefresh();

         // Check for listener beans and register them.
         registerListeners();

         // Instantiate all remaining (non-lazy-init) singletons.
         finishBeanFactoryInitialization(beanFactory);

         // Last step: publish corresponding event.
         finishRefresh();
      }

      catch (BeansException ex) {
         if (logger.isWarnEnabled()) {
            logger.warn("Exception encountered during context initialization - " +
                  "cancelling refresh attempt: " + ex);
         }

         // Destroy already created singletons to avoid dangling resources.
         destroyBeans();

         // Reset 'active' flag.
         cancelRefresh(ex);

         // Propagate exception to caller.
         throw ex;
      }

      finally {
         // Reset common introspection caches in Spring's core, since we
         // might not ever need metadata for singleton beans anymore...
         resetCommonCaches();
      }
   }
}
```

refresh(）这个方法返回一个静态指定的ApplicationListener的集合。方法内部是一个整体的同步代码块，被synchronized()所包裹。接下来我们一个方法一个方法的去查看，看一下这个refresh方法到底做了什么。

- `prepareRefresh();`

```java
/**
	 * Prepare this context for refreshing, setting its startup date and
	 * active flag as well as performing any initialization of property sources.
	 */
	protected void prepareRefresh() {
		// Switch to active.
		this.startupDate = System.currentTimeMillis();
		this.closed.set(false);
		this.active.set(true);

		if (logger.isDebugEnabled()) {
			if (logger.isTraceEnabled()) {
				logger.trace("Refreshing " + this);
			}
			else {
				logger.debug("Refreshing " + getDisplayName());
			}
		}
```

注释上说这个方法为刷新准备上下文，设置它的启动时间和活动标志以及执行所有属性的初始化。我们可以这个方法上来为`startupDate` 属性赋值为当前时间的毫秒数。然后设置了将cloesed和active进行了设置。之后的代码实际上只是根据日志级别来打印不同的日志。

- `ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();`

```java
protected ConfigurableListableBeanFactory obtainFreshBeanFactory() {
   refreshBeanFactory();
   return getBeanFactory();
}
```

`refreshBeanFactory();` 这个方法是个抽象方法，我们需要看它的实现子类中的方法，他有两个实现子类，我们选择`AbstractRefreshableApplicationContext` 中的实现方法来看一下。

```java
/**
 * This implementation performs an actual refresh of this context's underlying
 * bean factory, shutting down the previous bean factory (if any) and
 * initializing a fresh bean factory for the next phase of the context's lifecycle.
 */
@Override
protected final void refreshBeanFactory() throws BeansException {
   if (hasBeanFactory()) {
      destroyBeans();
      closeBeanFactory();
   }
   try {
      DefaultListableBeanFactory beanFactory = createBeanFactory();
      beanFactory.setSerializationId(getId());
      customizeBeanFactory(beanFactory);
      loadBeanDefinitions(beanFactory);
      synchronized (this.beanFactoryMonitor) {
         this.beanFactory = beanFactory;
      }
   }
   catch (IOException ex) {
      throw new ApplicationContextException("I/O error parsing bean definition source for " + getDisplayName(), ex);
   }
}
```

注释：此实现对此上下文的基础bean工厂执行实际的刷新，关闭前一个bean工厂（如果有），并为上下文生命周期的下一阶段初始化一个新的bean工厂。这里就是看一下上个生命周期是否有beanfactory，如果有的话就销毁，关闭，没有的话就创建一个并且初始化。其中我们可以关注一个有意思的方法`customizeBeanFactory(beanFactory);` 这个方法的意思是定制化beanFactory，而这个方法的实现很简单：

```java
protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
   if (this.allowBeanDefinitionOverriding != null) {
      beanFactory.setAllowBeanDefinitionOverriding(this.allowBeanDefinitionOverriding);
   }
   if (this.allowCircularReferences != null) {
      beanFactory.setAllowCircularReferences(this.allowCircularReferences);
   }
}
```

就是判断beanFactory的两个属性是否被设置，如果我们在配置文件中设置了这两个属性，那么就在这里将这两个属性的值赋给beanFactory的对应的属性中去。这两个属性分别是   

`allowBeanDefinitionOveriding` : 允许Bean定义信息被重写

`allowCircularReferences` : 允许引入循环依赖