# tiny-spring
简化版spring

## 大致流程：
1. 首先加载xml文件，将解析xml文件，封装成一个`BeanDefinition` 这一步不进行bean的实例化，只得到名称，类型，属性
2. 第一步完成之后，会在`beanFactory` 进行注册，(也就是放到另外一个map与放到一个list中)
3. 之后优先初始化实现`BeanPostProcessor` 的类，放入到一个集合中，BeanPostProcessor接口实现了一个完成的bean的生命周期中after和before的工作。
4. 第三步完成之后，遍历第二步的list，去得到bean，在这时候，就会涉及到be an的一个初始化，首先要去第二步的map中得到 `BeanDefinition` ,去判断一下其中的bean 是否被实例化过，没有的话就创建一个实例，
5. 创建实例分为两步来完成，一步是创建一个空的实例，其中属性没有被初始化过，完成后，将实例 放入到`BeanDefinition` 中，**这是为了避免由于循环依赖导致类无法加载的问题** ，在这之后，再进行一个bean属性的加载（**但这里也AOP生成代理对象的时候 会出现一个问题**）
6. 在这之后，会生成一个bean实例后，会遍历第三步的集合，进行bean的after与before的操作，在after的时候，要判断这个bean是否会被拦截，要是被拦截之后，就要生成一个代理对象，生成的代理对象要再次被放入`BeanDefinition`中，
7. 在这之后，基本上bean的实例化就完成了 
8. 后续通过`ApplicationContext` 来得到bean,在调用方法的时候，会判断方法是否会被拦截，会被拦截的话，会直接执行方法，不会的话就直接执行了