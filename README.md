# A Tiny Spring Framework

Here, we will find an example of a simple framework that imitates the famous Spring. We will call it Tiny Spring. It should help us understand the basic concepts of the Spring Framework (and any other framework in Java or other languages).
It implements the most essential features of a framework—namely,  Dependency Injection (DI) and Aspect-Oriented Programming (AOP) concepts.
The project consists of the framework itself and a simple example of a REST API that uses it.

## The Application
The application's entry point is the `MyExampleApplication` class situated at the root package `cat.tecnocampus`, while the rest is in the `cat.tecnocampus.application` package.

#### Components
The application is a @RestController (MyController) that uses a @Service (MyService), which in turn uses a @Component (MyOtherService).

#### Dependency Injection
In the former sentence, *uses* means that the MyController has a dependency on MyService, that has a dependency on MyOtherService, and the framework injects all.

#### Application Context
Before injecting the dependencies, the framework creates an instance for each class and
registers them in the application context.

#### Aspect-Oriented Programming (AOP)
MyOtherService is annotated with @Validated and implements an interface (MyOtherServiceInterface) where the method `doSomething` is defined
and its parameter is annotated with @Length. The framework uses AOP to intercept the call to the method `doSomething` and check if the parameter
length is between a given number of characters interval.
Note that it is the class that is annotated with @Validated, but it is the method's parameter in the interface that needs to be annotated 
with @Length (not the parameter in the class). 
This is something that we plan to improve in the future.

## The Framework
It is composed of three main packages: the `core`, `validationAOP`, and `webModule`. It extensively uses Java annotations 
and the reflexion library.

### Core
The Core is in charge of the Dependency Injection. Logically, it follows the steps:

#### Component Scan
Looks for the application's components and store one instance in the context. It follows these steps:
1. Scan the packages to find the files that represent classes
2. Select the classes are annotated with @Component
3. Create an instance of the classes 
4. Store the instances in the application context. It uses a map where the key is the class name and the value is the instance of the class.

#### Dependency Injection
Sets the attributes annotated with @Autowired with the object of the attribute's type. It follows these steps:
6. Look for the class attributes annotated with @Autowired to inject the appropriate object (the dependency).
5. Get the type of the attribute and look for the instance in the application context.
6. Set the attribute with the instance.

#### Annotations
The framework uses the following annotations: **@Component**, **@Service**, **@RestController** and, **@Autowired**. They are used to identify 
the classes that are part of the application and the dependencies between them. Note that @Service is annotated with @Component, and 
@RestController is annotated with @Service. This way, Services and RestControllers are also components, and are automatically 
identified as such.

**@Autowire** is used to identify the attributes that are dependencies and need to be injected.

### ValidationAOP
The validation AOP is in charge of the *aspect* of validating the parameters of the methods. It uses Java dynamic proxies 
to intercept the method calls and check the parameters.

#### Create the Proxy
The framework creates a proxy for the classes annotated with @Validated. For creating a Dynamic Proxy, it is mandatory that the 
class being proxied implements an interface so that Java can create the proxy. It follows these steps:
1. During the scan process, it looks for the classes annotated with @Validated.
2. For each class, it creates a proxy that intercepts the method calls. In order to do that, it uses the Proxy class and the newProxyInstance method
that receives the class loader, the interfaces that the proxy implements, and the interceptor with the target object.


#### Intercept the Method Call
The *interceptor* is a Handler that implements the InvocationHandler interface. It follows these steps:
1. It is passed the method being called and its parameters.
2. Gets the parameter that is annotated with @Length.
3. Checks if the parameter length is between the given interval.
4. If the parameter is valid, it calls the method in the target object. Otherwise, it throws an exception.

We plan to add new annotations for validation in the near future.

### WebModule


## Bibliography 
### Other Tiny Spring Frameworks
I got inspiration from the following articles:
* https://akoserwal.medium.com/building-a-minimal-spring-framework-from-scratch-8faa73067cbc
* https://liangliangliangtan.github.io/2019-06-26-mini-spring-mvc.html

### AOP and dynamic proxy
* https://naveen-metta.medium.com/exploring-the-depths-of-dynamic-proxy-in-java-a-comprehensive-guide-f34fb45b38a3