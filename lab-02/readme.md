Use the application from last week.

By the end of this lab you should be able to

create beans using @Configure classes with @Bean methods
configure beans using dependency inject
access the beans from the container in main() using two approaches (new AnnotationConfigApplicationContext() and SpringApplication.run())
write (basic) unit tests for the beans
add post-construct and pre-destroy interceptors to your beans
Preliminary Work
Take a look at the dependencies in the pom - you have been given all you need to start with Spring including Spring tests using the Spring framework. However up until now you have only used the testing and lombok dependencies. So now you will begin with a little Spring.

Create Beans
Create a class, marking it as a source of Spring beans using @Configuration at class level. You might name it Config or SpringConfig or AppConfig - something meaningful.
Add a method whose signature promises to return a WeddingRepository object (interface) and the body of which returns an implementation of WeddingRepository ie. new MockWeddingRepositoryImpl() . Mark this as a Spring bean by annotating the method as @Bean. You might run your project just to make sure you haven't made a mistake.
Similar to (2), create a CalculateCost (interface) Spring bean using methods and @Bean. The method returns a CalculateCostImplementation class which has the WeddingRepository wired into it.
Remove the code in main() - it has served to remind you of the usual way to instantiate objects in Java. But now we are changing to the Spring way. Add the following code to main():
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(YOUR_CONFIG_CLASS);
Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
Through the AnnotationConfigApplicationContext class we have access to the beans in Spring's application context.
The second line displays each of the bean's names just for your information.
FYI -> This could also be written as:

for (String name : context.getBeanDefinitionNames()) {
System.out.println(name);
}
Note the names of the beans - Spring has named them, but do you recognise the convention used? Spring will always follow convention, naming the beans for us, so if you are happy with convention than you don't have to explicitly name them - convention over configuration saves you time. 4. Add code to main() to access the WeddingRepository bean using context.getBean().
The argument is a choice - you can get a bean by its name or its class. The former is useful if you have more than one bean of the same class but requires that you add the class as a second argument. Try both ways.
Having accessed the bean use its findById() method to print the details of wedding RS342.

5. Move your attention to the Lab1ApplicationTests constructor. Delete the body of this constructor.
   Create an ApplicationContext object as follows:
   AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(YOUR_CONFIG_CLASS);

This gives the test class access to the beans in Spring's application context.
Use the .getBean() method of AnnotationConfigApplicationContext to access the CalculateCost bean in the context.
You can access by type or name.

6. Add a new test method to verify that the CalculateCost bean has been created - is not null.
7. Run the tests as they are - they should pass.
8. Modify the main public method to access the CalculateCost bean through the application context instead of by plain old java. This is very similar to the set up for the tests above.

@PostConstruct
Add a method annotated as @PostConstruct to the CalculateCostImplementation class, logging a message from the bean after creation and before destruction.
Use @Slf4j class-level annotation to create the logger.
Run the tests again - do you see the logged message?
Run the main - do you see the logged message?

@PreDestroy
Create a method annotated as @PreDestroy to the CalculateCostImplementation class, again logging a message.
Run the tests - do you see it?
Run the main - do you see it?
Maybe you don't see the destroy message? This is because the context is not a controlled shutdown - yes the application context is shut down but only because the application ends not because we have shut it down in a controlled way.
Add
context.close()
to the end of the main.
Run the main again.

Using SpringApplication.run()
Now we will run the application a different way.

In main() delete

AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(YOUR_CONFIG_CLASS);

and replace it with

ApplicationContext context = SpringApplication.run(YOUR_CONFIG_FILENAME.class);

This process to load the context includes a shutdown hook at close so we don't have to add the close command.

Banner
Create a banner.txt file in resources folder and type whatever wish into it - run the program again. Can you see what banner.txt does?

If you put banner.txt anywhere else it won't work - Spring runs by convention over configuration and it expects to find the banner file in resources. Why not use an ASCII text generator to create your own banner.
