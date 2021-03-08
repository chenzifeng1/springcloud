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

注释上说这个方法为刷新准备上下文，设置它的启动时间和活动标志以及执行所有属性的初始化。我们可以这个方法上来为`startupDate`
属性赋值为当前时间的毫秒数。然后设置了将cloesed和active进行了设置。之后的代码实际上只是根据日志级别来打印不同的日志。

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

注释：此实现对此上下文的基础bean工厂执行实际的刷新，关闭前一个bean工厂（如果有），并为上下文生命周期的下一阶段初始化一个新的bean工厂。这里就是看一下上个生命周期是否有beanfactory，如果有的话就销毁，关闭，没有的话就创建一个并且初始化。其中我们可以关注一个有意思的方法`customizeBeanFactory(beanFactory);`
这个方法的意思是定制化beanFactory，而这个方法的实现很简单：

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

之后看一下加载bean定义信息这个方法，方法注释如下：

通过将委派给一个或多个bean定义读取器，将bean定义加载到给定的bean工厂中。

`loadBeanDefinitions(beanFactory);`

我们可以看一下如何让通过beanDefinationReader来完成bean定义信息加载的，下面是这个抽象方法的实现类

```java
protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException, IOException {
   // Create a new XmlBeanDefinitionReader for the given BeanFactory.
   XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

   // Configure the bean definition reader with this context's
   // resource loading environment.
   beanDefinitionReader.setEnvironment(this.getEnvironment());
   beanDefinitionReader.setResourceLoader(this);
   beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(this));

   // Allow a subclass to provide custom initialization of the reader,
   // then proceed with actually loading the bean definitions.
   initBeanDefinitionReader(beanDefinitionReader);
   loadBeanDefinitions(beanDefinitionReader);
}
```

上述代码进行几个操作：

1. 为给定的BeanFactory初始化一个XmlBeanDefinitionReader的对象，初始化的时候会有很多操作，暂时先不去深究。
2. 为这个XmlBeanDefinitionReader对象设置一些属性：
    1. private Environment environment(系统环境属性)：设置在读取bean定义时要使用的环境。 最常用于评估概要文件信息，以确定应读取哪些bean定义，应省略哪些。
    2. private ResourceLoader resourceLoader(资源加载器)：设置ResourceLoader以用于资源位置。
       如果指定ResourcePatternResolver，则Bean定义读取器将能够将资源模式解析为Resource数组。
    3. private EntityResolver entityResolver(资源实体解析器)：设置要用于解析的SAX实体解析器。
3. initBeanDefinitionReader(beanDefinitionReader);

   注释：初始化用于加载此上下文的Bean定义的Bean定义读取器。 默认实现为空。 可以在子类中重写，例如，用于关闭XML验证或使用其他XmlBeanDefinitionParser实现

    ```java
    protected void initBeanDefinitionReader(XmlBeanDefinitionReader reader) {
       reader.setValidating(this.validating);
    }
    ```

   在这个方法中调用了reader中设置验证的方法，参数是Boolean类型。我们可以看看这个设置验证方法的实现。

    ```java
    public void setValidating(boolean validating) {
       this.validationMode = (validating ? VALIDATION_AUTO : VALIDATION_NONE);
       this.namespaceAware = !validating;
    }
    ```

   这个方法设置了校验模式，是否启用Xml验证，默认是true。如果参数是false，就会启用命名空间Aware，一边在这种情况下能正确处理架构的命名空间。这里先记一下，看看这两个值在之后哪里使用了，结合使用场景来对这两个属性理解。

4. loadBeanDefinitions(beanDefinitionReader);

   注释：根据给定的XmlBeanDefinitionReader来加载bean定义信息，由于bean Factory的生命周期是由`refreshBeanFactory()`
   这个方法来处理，因此`loadBeanDefinitions()`这个方法应该只用来加载或者是注册bean定义信息

    ```java
    protected void loadBeanDefinitions(XmlBeanDefinitionReader reader) throws BeansException, IOException {
       Resource[] configResources = getConfigResources();
       if (configResources != null) {
          reader.loadBeanDefinitions(configResources);
       }
       String[] configLocations = getConfigLocations();
       if (configLocations != null) {
          reader.loadBeanDefinitions(configLocations);
       }
    }
    ```

   这里主要看参数为字符数组的`loadBeanDefinitions(String...locations)` ,这个getConfigLocations()
   方法返回一系列资源地址，子类可以重写这个方法设置，以提供一组资源位置以从中加载bean定义。比如说，我在Resources下新建了一个配置文件“myApplicationContext.xml”,在getConfigLocation的时候会读取到这个配置文件的文件名，在之后加载过程中会读取该配置文件中的bean配置信息。

   重点看一下loadBeanDefinitions(String... locations)这个方法吧，看我们的配置文件中的信息是如何被加载进去的。

    ```java
    @Override
    public int loadBeanDefinitions(String... locations) throws BeanDefinitionStoreException {
       Assert.notNull(locations, "Location array must not be null");
       int count = 0;
       for (String location : locations) {
          count += loadBeanDefinitions(location);
       }
       return count;
    }
    ```

   遍历传进来的`locations` ,比如说我只配置了一个`applicationContext.xml` 配置文件，这里遍历加载的时候只会加载这一个配置文件里面的bean定义信息。我们看一下如何加载的。

    ```java
    @Override
    public int loadBeanDefinitions(String location) throws BeanDefinitionStoreException {
       return loadBeanDefinitions(location, null);
    }
    ```

   这里其实对loadBeanDefinitions进行了多次重载，继续查看

    ```java
    public int loadBeanDefinitions(String location, @Nullable Set<Resource> actualResources) throws BeanDefinitionStoreException {
       ResourceLoader resourceLoader = getResourceLoader();
       if (resourceLoader == null) {
          throw new BeanDefinitionStoreException(
                "Cannot load bean definitions from location [" + location + "]: no ResourceLoader available");
       }

       if (resourceLoader instanceof ResourcePatternResolver) {
          // Resource pattern matching available.
          try {
             Resource[] resources = ((ResourcePatternResolver) resourceLoader).getResources(location);
             int count = loadBeanDefinitions(resources);
             if (actualResources != null) {
                Collections.addAll(actualResources, resources);
             }
             if (logger.isTraceEnabled()) {
                logger.trace("Loaded " + count + " bean definitions from location pattern [" + location + "]");
             }
             return count;
          }
          catch (IOException ex) {
             throw new BeanDefinitionStoreException(
                   "Could not resolve bean definition resource pattern [" + location + "]", ex);
          }
       }
       else {
          // Can only load single resources by absolute URL.
          Resource resource = resourceLoader.getResource(location);
          int count = loadBeanDefinitions(resource);
          if (actualResources != null) {
             actualResources.add(resource);
          }
          if (logger.isTraceEnabled()) {
             logger.trace("Loaded " + count + " bean definitions from location [" + location + "]");
          }
          return count;
       }
    }
    ```

   看看资源加载器是不是资源模式加载器，这个资源加载器在`loadBeanDefinitions(DefaultListableBeanFactory beanFactory)`
   这个方法内设置过`beanDefinitionReader.setResourceLoader(this)`
   我们现在用到的资源加载器就是在这里设置的，通过断点调试，可以看出两个地方的ResourceLoader是同一个对象。既然resourceLoader是ResourcePatternResolver的实例，那么第一个判断语句为true，我们继续看之后怎么执行。

   `Resource[] resources = ((ResourcePatternResolver) resourceLoader).getResources(location);`
   这个接口方法的真正执行的方法是`PathMatchingResourcePatternResolver`
   的实现方法,具体内容先略过，这个方法返回了一个Resource数组，之后又调用了loadBeanDefinitions的其他重载方法。之后一系列的重载方法的调用，在这个过程中，我们可以关注一下主要参数的变化。

   String[]→String→Resource[]→Resource→EncodedResource→
   开始真正的做加载BeanDefinitions操作的方法`doLoadBeanDefinitions(InputSource inputSource, Resource resource)`
   。看一下`doLoadBeanDefinitions` 这个方法内容:

    ```java
    Document doc = doLoadDocument(inputSource, resource);
    int count = registerBeanDefinitions(doc, resource);
    if (logger.isDebugEnabled()) {
       logger.debug("Loaded " + count + " bean definitions from " + resource);
    }
    return count;
    ```

   这个方法会把配置文件转化成Document对象doc，该对象里面基本都是节点信息

   ![Spring%20framework%2080df3c81d678438c85eb0f8059dafbf0/Untitled%202.png](Spring%20framework%2080df3c81d678438c85eb0f8059dafbf0/Untitled%202.png)

   之后根据Document对象的内容注册BeanDefinition，重点看一下这个方法如何完成注册的。

    ```java
    public int registerBeanDefinitions(Document doc, Resource resource) throws BeanDefinitionStoreException {
       BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader();
       int countBefore = getRegistry().getBeanDefinitionCount();
       documentReader.registerBeanDefinitions(doc, createReaderContext(resource));
       return getRegistry().getBeanDefinitionCount() - countBefore;
    }
    ```

   这个方法先创建了一个Reader（BeanDefinitionDocumentReader）对象，之后统计了一下BeanDefinition的个数。BeanDefinition的注册具体是在   `documentReader.registerBeanDefinitions(doc, createReaderContext(resource));`
   这个方法内完成的。我们来看一下如何实现：这个接口的实现方法调用了`doRegisterBeanDefinitions(Element root)` 这个方法，在spring框架中（java方法命名规范也应该如此）以`doXX`
   开头的方法基本都是进行实际操作的方法。这个方法中模拟了一组委托（实际不需要，具体作用可以见注释），之后调用了一个重要的方法`parseBeanDefinitions(root, this.delegate);`
   开始解析BeanDefinitions。

    ```java
    protected void parseBeanDefinitions(Element root, BeanDefinitionParserDelegate delegate) {
       if (delegate.isDefaultNamespace(root)) {
          NodeList nl = root.getChildNodes();
          for (int i = 0; i < nl.getLength(); i++) {
             Node node = nl.item(i);
             if (node instanceof Element) {
                Element ele = (Element) node;
                if (delegate.isDefaultNamespace(ele)) {
                   parseDefaultElement(ele, delegate);
                }
                else {
                   delegate.parseCustomElement(ele);
                }
             }
          }
       }
       else {
          delegate.parseCustomElement(root);
       }
    }
    ```

   先判断一下给定的节点是否指明了默认命名空间，在我们配置文件中没有引用其他的标签，解析的元素都是在默认命名空间里的。获取root的子节点的集合，然后遍历这个集合。遍历的时候看一下正在遍历的元素节点是否是Element，不是的话就不继续解析了。之后会根据元素节点是否属于默认命名空间来调用不同的解析方法`parseCustomElement()`
   /`parseCustomElement()` 。默认空间的元素一共四种，在parseCustomElement方法中可以很好的看出

    ```java
    private void parseDefaultElement(Element ele, BeanDefinitionParserDelegate delegate) {
       if (delegate.nodeNameEquals(ele, IMPORT_ELEMENT)) {
          importBeanDefinitionResource(ele);
       }
       else if (delegate.nodeNameEquals(ele, ALIAS_ELEMENT)) {
          processAliasRegistration(ele);
       }
       else if (delegate.nodeNameEquals(ele, BEAN_ELEMENT)) {
          processBeanDefinition(ele, delegate);
       }
       else if (delegate.nodeNameEquals(ele, NESTED_BEANS_ELEMENT)) {
          // recurse
          doRegisterBeanDefinitions(ele);
       }
    }
    ```

   四种元素类型：

    1. public static final String IMPORT_ELEMENT = "import";
    2. public static final String ALIAS_ELEMENT = "alias";
    3. public static final String BEAN_ELEMENT = "bean"
    4. public static final String NESTED_BEANS_ELEMENT = "beans"

   根据不同元素类型调用不同的解析方法，这里具体不看了，需要再补充。

   这里我们可以看一下spring如何解析非默认命名空间的元素的，看一下`parseCustomElement` 的实现，这个方法也调用了自己的重载方法。

    ```java
    @Nullable
    public BeanDefinition parseCustomElement(Element ele, @Nullable BeanDefinition containingBd) {
    		//获取命名空间
       String namespaceUri = getNamespaceURI(ele);
       if (namespaceUri == null) {
          return null;
       }
    		//根据命名空间找到对应的NamespaceHandler
       NamespaceHandler handler = this.readerContext.getNamespaceHandlerResolver().resolve(namespaceUri);
       if (handler == null) {
          error("Unable to locate Spring NamespaceHandler for XML schema namespace [" + namespaceUri + "]", ele);
          return null;
       }
       return handler.parse(ele, new ParserContext(this.readerContext, this, containingBd));
    }
    ```

   由于不是默认的命名空间，所以我们得先获取元素所在的命名空间，之后再根据命名空间来获取对应的NamespaceHandler