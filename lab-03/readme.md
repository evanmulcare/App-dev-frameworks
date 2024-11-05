Multiple Configuration Files
This is to demonstrate that you can use more than one configuration class.

Create a new configuration class annotated as @Configuration. Add @Import(your config from last week) at class level. 

Use this class in your main method and in the unit tests - annotate the test class as being a Spring Junit test @SpringJUnitConfig(classes = {YOUR CLASS.class})

Verify this works before you proceed. 

Internationalisation
💡Create another new configuration class. To this class add a MessageSource bean created from its implementation ResourceBundleMessageSource. Set the basename for the language files to whatever you want. The default is "messages".

💡Create a file for the default language (e.g. messages.properties) and for one other language of your choice, (messages_ISO_CODE.properties) containing one or more key/value pairs where the value is a word or phrase translated into different languages.

💡Return to the configuration class that has @Import() from above and add this configuration class to the @Import command
@Import({YourConfigClassFromLastWeek.class, LanguageConfigFromThisWeek.class})
Note the curly braces.

💡Create a new test class in the test package annotated with @SpringJunitConfig(YourLanguageConfigurationClass).
     Add unit tests to ensure the bean has been created and the languages work. Run these. Do they pass? 

💡Add code to the main to access the message source bean and demonstrate its use. Usually these messages would be in a view but for now printing them to the console suffices. 

Language not in Locale? 🚨
You can set your own Locale if you need it. 

In the following code we set up a new Locale for Irish. I have used the 'ga' code so when I specify 'irish' as the locale, Spring looks for the basename_ga.properties for example messages_ga.properties.

Locale irish = new Locale("ga", "IRELAND");
System.out.println(applicationContext.getMessage("welcome", null, irish));
Configure beans using annotations
The next change is radical so you might like to make a copy of your current version of the project to keep it safe.


1. Delete the configuration class from last week that included the CalculateCost and WeddingRepository beans.

2. Remove any references to that class.

3. Add @Repository to the MockWeddingRepositoryImpl class at class level. This instructs Spring to create a bean from this class. This will be the only bean in the context that implements the WeddingRepository interface, so will be the bean used for DI when we autowire a WeddingRepository bean.

4. Add @Service to the CalculateCostImplementation. This is at the service layer (applying business rules) so @Service is appropriate. This is the only implementation of the CalculateCost interface so will be use whenever Spring is asked to inject this dependency. Generate a constructor for the WeddingRepository field. Annotate this as @Autowired. This instructs Spring to find a suitable bean and wire it in here. 

6. Run the main. It does not work. Do you understand why? Read the error - it can't find any of the beans we need.

7. Return to your main config file (the one with the @Import and not much else) and add @ComponentScan({"your packages"}) at class level.

8. Run the main again - it should work. Do you understand what component scan has done? If not, please ask.

🎈Delete the constructor on CalculateCostImplementation. Run the main. Oops we broke it. Spring doesn't know what to do. 
🎈Add it in again. Run it again. All is good. This is because Spring sees that the constructor depends upon an injected object,
finds one that suits and injects for us. 
🎈So let's learn about setter autowiring. Delete the constructor. Java will automatically create a no-arguments constructor (@NoArgsConstructor) in the absence of any others. So now we have a no-arguments constructor, the tax_rate is ok but what about the WeddingRepository. 
🎈Generate a setter method for the WeddingRepository field (generate the code don't use Lombok's @Setter), add @Autowired to this, and run it again. It should work again. So we have moved from constructor based DI using autowiring to setter-based DI using autowiring.

Which should you use? Setter is used if the field is not required, but in this case it is required, so must be provided at construction, hence autowiring on the constructor is more appropriate. Field injection is never appropriate except in unit tests. 

Adding Autowired to the test class
1. Annotate the test class as being a Spring Junit test @SpringJUnitConfig(classes = {??????.class})
2. Remove the constructor.
3. Annotate the CalculateCost field as @Autowired. You may have to remove final on the field because this uses setter-based injection. 
4. Run the test again.

Do you understand what is happening? It is essential that you do. If not, please ask - it relates to field injection.

Autowiring on the constructor
1. Remove the @Autowired field injection.
2. Run the tests again. The tests fail - why?
3. Add a constructor for the test class which injects the CalculateCost field as a parameter.
4. Annotate this constructor method as @Autowired.
5. Run the tests again.

Do you understand what has happened here? If so then you understand the difference between field, setter and constructor DI.

Profiles
It is common to use profiles to change configurations. 

Create a second that implements the WeddingRepository. Remember these simulate data access and are utter nonsense - we are just using them to learn about Spring.

@ToString
@Repository
public class MockWeddingRepositoryImplEmpty implements WeddingRepository {
    Map<String, Wedding> weddings = new HashMap<>();

    public MockWeddingRepositoryImplEmpty() {
        List<Guest> guests = new ArrayList<>();
        Wedding wedding = new Wedding("RS342",
                new Person(89, "Minnie", "Mouse"),
                new Person(100, "Mickey", "Mouse"),
                56.00, guests);
        weddings.put(wedding.getWeddingId(), wedding);
    }

    @Override
    public Optional<Integer> getNumberOfGuests(String id) {
            return Optional.of(0);
    }

    @Override
    public Optional<Wedding> findById(String id) {
        return Optional.ofNullable(weddings.get(id));
    }
}
Run the main() - it fails. Why? 

Once you understand why, we can fix it. 

Add @Profile at class level to the two implementations of WeddingRepository, giving them each a different profile name. 

In application.profiles set the active profile you wish to use. 

Ensure this works. 

In testing, set the active profile to the one that activates the populated findAll() request. 

@Value
We have seen how to inject objects into a bean. Let's see how we might inject a value into a bean. This was not part of the lecture and is new information. 

⚡We have hardcoded the tax rate into CalculateCostImplementation. Remove this value (just the value, not the property).

⚡Run the tests - costWeddingIncVat() test fails because tax_rate is not defined.

⚡Add @Value("0.15") to the field. Run the main and tests - they should work. When you import the @Value class please ensure it is the Spring @Value you import.

Great - but this isn't much different to just using the assignment statement. Let's refactor the code so that the value for the VAT rate is set in a central file, the application properties file.
⚡Create a file called application.properties in the resources folder. Add the key/value pair vat.rate=0.15

⚡Modify the @Value command to use a placeholder: @Value("${vat.rate}").

⚡Add @PropertySource("classpath:application.properties") to the configuration class that you created last week.

⚡Run the main. When Spring is asked to apply placeholders it looks for the file called application.properties in the resources folder because we have specified it as the source of the properties. If you don't like that name - although why wouldn't you? It's a perfectly nice name - you can call it whatever you want but when we come to Spring Boot application.properties is the conventional name so I suggest we use it now.

The classpath is a list of locations (directories and JAR files) where the Java runtime looks for classes and resources. Resources placed in these locations are accessible during execution. By convention, resources are placed in directories like src/main/resources in a Maven project.

What if the VAT rate changes? Go into the application.properties and edit the value to 0.12.

⚡Run the main - all good.

⚡Run the tests - they fail because of government policy not because our code is wrong. For this reason it is sometimes better to have separate properties files for tests.

⚡Create a new folder in the test folder. Mark this as a resources root (right click on the folder -> Mark As -> Resources root).

⚡Create a new file called application-test.properties and add the key/value pair for vat.rate with value 0.15 (our original value).

⚡Annotate the class to specify the property source as follows
@TestPropertySource("classpath:application-test.properties")

⚡Re-run the tests. All should be happy again because the unit test uses the local application properties. 

Do you understand all that you have done. You should now understand what the following annotations do for you:

Class Level
@SpringJUnitConfig
@PropertySource
@TestPropertySource (same as @PropertySource but used in tests to define a different source of properties)
@Configuration
@Import
@Repository
@Service
@Component (not used in this lab but appropriate for generic beans - you could replace @Service and @Repository with @Component
and it will still work)
@ComponentScan
@Setter
@Getter
@ToString
@Profile
@NoArgsConstructor
@AllArgsConstructor
Method Level
@Bean
@Autowired
@Test
@PostConstruct
@PreDestroy
@Profile
Field Level
@Value 