package controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pojo.Product;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

@Qualifier
/**
 * @author yanglh
 * @ className:
 * @ description:
 * @ create 2021-03-17 10:01
 **/

/**
 * 简单理解@RestController相当于@ResponseBody ＋ @Controller合在一起的作用。
 * 1) 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
 * 2) 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
 * 如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 * 例如：
 * 1.使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面
 * 若返回json等内容到页面，则需要加@ResponseBody注解
 * 2.@RestController注解，相当于@Controller+@ResponseBody两个注解的结合，返回json数据不需要在方法前面加@ResponseBody注解了，但使用@RestController这个注解，就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
 * --------------------------------------------------------------------------
 * @Controller
 * 在SpringMVC中，控制器Controller负责处理由DispatcherServlet分发的请求，它把用户请求的数据经过业务处理层处理之后封装成一个Model，然后再把该Model返回给对应的View进行展示。
 * 在SpringMVC中提供了一个非常简便的定义Controller的方法，你无需继承特定的类或实现特定的接口，只需使用@Controller标记一个类是Controller，然后使用@RequestMapping和@RequestParam等一些注解用以定义URL请求和Controller方法之间的映射，这样的Controller就能被外界访问到。
 * 此外Controller不会直接依赖于HttpServletRequest和HttpServletResponse等HttpServlet对象，它们可以通过Controller的方法参数灵活的获取到。
 * @Controller用于标记在一个类上，使用它标记的类就是一个SpringMVC Controller对象。分发处理器将会扫描使用了该注解的类的方法，并检测该方法是否使用了@RequestMapping注解。
 * @Controller只是定义了一个控制器类，而使用@RequestMapping注解的方法才是真正处理请求的处理器。单单使用@Controller标记在一个类上还不能真正意义上的说它就是SpringMVC的一个控制器类，因为这个时候Spring还不认识它。那么要如何做Spring 才能认识它呢？这个时候就需要我们把这个控制器类交给Spring来管理。有两种方式：
 * （1）在SpringMVC的配置文件中定义MyController的Bean对象。
 * （2）在SpringMVC的配置文件中告诉Spring该到哪里去找标记为@Controller的Controller控制器。
 * ------------------------------------------------------------------
 * @RequestMapping
 * 说明：
 * （1）在@Target中有两个属性，分别为 ElementType.METHOD 和 ElementType.TYPE ，也就是说 @RequestMapping 可以在方法和类的声明中使用
 * （2）可以看到注解中的属性除了 name() 返回的字符串，其它的方法均返回数组，也就是可以定义多个属性值，例如 value() 和 path() 都可以同时定义多个字符串值来接收多个URL请求
 * 请求映射
 * 也就是通过它来指定控制器可以处理哪些URL请求，相当于Servlet中在web.xml中配置的映射作用。
 * RequestMapping是一个用来处理请求地址映射的注解，可用于类或方法上。用于类上，表示类中的所有响应请求的方法都是以该地址作为父路径。
 * RequestMapping注解有六个属性，下面我们把这些属性分成三类进行说明。
 * 1、value属性、method属性
 * value：指定请求的实际地址，指定的地址可以是URI Template模式
 * method：指定请求的method类型，例如GET、POST、PUT、DELETE等；
 * 2、consumes属性、produces属性
 * consumes：指定处理请求的提交内容类型（Content-Type），例如application/json、text/html等
 * produces：指定返回的内容类型，仅当Request请求头中的(Accept)类型中包含该指定类型才返回
 * 3、params属性、headers属性
 * params：指定request中必须包含某些参数值时，才让该方法处理
 * headers：指定request中必须包含某些指定的header值，才能让该方法处理请求
 * -------------------------------------------------------------------------
 * @Resource和@Autowired
 * @Resource和@Autowired都是做Bean的注入时使用，其实@Resource并不是Spring的注解，它的包是javax.annotation.Resource，需要导入，但是Spring支持该注解的注入。
 * 两者都可以写在字段和setter方法上。如果都写在字段上，那么就不需要再写setter方法。
 * @Autowired为Spring提供的注解，需要导入包org.springframework.beans.factory.annotation.Autowired，只按照byType注入。
 * @Autowired注解是按照类型（byType）装配依赖对象，默认情况下它要求依赖对象必须存在，如果允许null值，可以设置它的required属性为false。如果我们想使用按照名称（byName）来装配，可以结合@Qualifier注解一起使用。
 * @Resource默认按照ByName自动注入，由J2EE提供，需要导入包javax.annotation.Resource。
 * @Resource有两个重要的属性：name和type，而Spring将@Resource注解的name属性解析为Bean的名字，而type属性则解析为Bean的类型。
 * 所以，如果使用name属性，则使用byName的自动注入策略，而使用type属性时则使用byType自动注入策略。
 * 如果既不制定name也不制定type属性，这时将通过反射机制使用byName自动注入策略。
 * @Resource装配顺序：
 * （1）如果同时指定了name和type，则从Spring上下文中找到唯一匹配的bean进行装配，找不到则抛出异常。
 * （2）如果指定了name，则从上下文中查找名称（id）匹配的bean进行装配，找不到则抛出异常。
 * （3）如果指定了type，则从上下文中找到类似匹配的唯一bean进行装配，找不到或是找到多个，都会抛出异常。
 * （4）如果既没有指定name，又没有指定type，则自动按照byName方式进行装配；如果没有匹配，则回退为一个原始类型进行匹配，如果匹配则自动装配。
 * 备注：@Resource的作用相当于@Autowired，只不过@Autowired按照byType自动注入。
 * ----------------------------------------------------------------------
 * SpringMVC 使用 @ModelAttribute 和 @SessionAttributes 在不同的模型（model）和控制器之间共享数据。
 * @ModelAttribute
 * 可以应用在方法参数上或方法上，它的作用主要是
 * 当注解在方法参数上时会将注解的参数对象添加到Model中；
 * 当注解在请求处理方法Action上时会将该方法变成一个非请求处理的方法，但其它Action被调用时会首先调用该方法。
 * 当@ModelAttribute注解用于方法时，与其处于同一个处理类的所有请求方法执行前都会执行一次此方法，这可能并不是我们想要的，
 * 因此，我们使用更多的是将其应用在请求方法的参数上，而它的一部分功能与@RequestParam注解是一致的，只不过@RequestParam用于绑定单个参数值，而@ModelAttribute注解可以绑定所有名称匹配的，此外它自动将绑定后的数据添加到模型中
 *
 * @ModelAttribute和@RequestMapping同时注释一个方法
 * 这时这个方法的返回值并不是表示一个视图名称，而是model属性的值，视图名称由RequestToViewNameTranslator根据请求"/helloWorld"转换为helloWorld。
 * Model属性名称由@ModelAttribute(value="")指定，相当于在request中封装了key=attributeName，value=hi。
 *
 * 关于 @ModelAttribute 标记在方法上时对应的属性是存放在 session 中还是存放在模型中？
 * 答案是放在模型中，要放在session中，需要使用@SessionAttributes
 * -------------------------------------------------------------------------
 * @PathVariable
 * 用于将请求URL中的模板变量映射到功能处理方法的参数上，即取出uri模板中的变量作为参数。
 * --------------------------------------------------------------------------
 * @requestParam
 * 用于将请求参数区数据映射到功能处理方法的参数上。
 * 它有三个常用参数：defaultValue , required , value ；
 * defaultValue表示设置默认值，required通过boolean设置是否是必须要传入的参数（默认是true），value值表示接受的传入的参数类型。
 * --------------------------------------------------------------------------
 * @ResponseBody&@RequestBody
 * @Responsebody表示该方法的返回结果直接写入HTTP response body中。一般在异步获取数据时使用，在使用@RequestMapping后，返回值通常解析为跳转路径，加上@Responsebody后返回结果不会被解析为跳转路径，而是直接写入HTTP response body中。比如异步获取json数据，加上@Responsebody后，会直接返回json数据。
 * @RequestBody将HTTP请求正文插入方法中，使用适合的HttpMessageConverter将请求体写入某个对象
 * @RequestBody作用：
 * （1）该注解用于读取Request请求的body部分数据，使用系统默认配置的HttpMessageConverter进行解析，然后把相应的数据绑定到要返回的对象上；
 * （2）再把HttpMessageConverter返回的对象数据绑定到 controller中方法的参数上。
 * @RequestBody使用时机：
 * （1）GET、POST方式提时，根据request header Content-Type的值来判断：application/x-www-form-urlencoded，可选（即非必须，因为这种情况的数据@RequestParam, @ModelAttribute也可以处理，当然@RequestBody也能处理）；multipart/form-data，不能处理（即使用@RequestBody不能处理这种格式的数据）；其他格式，必须（其他格式包括application/json, application/xml等，这些格式的数据，必须使用@RequestBody来处理）；
 * （2）PUT方式提交时，根据request header Content-Type的值来判断：application/x-www-form-urlencoded，必须；multipart/form-data，不能处理；其他格式，必须；说明：request的body部分的数据编码格式由header部分的Content-Type指定；
 * @ResponseBody作用：
 * 该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
 * @ResponseBody使用时机：
 * 返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用
 * --------------------------------------------------------------------------
 * @Repository
 * 用于注解dao层，在daoImpl类上面注解。
 */
@RestController
@RequestMapping("/test")
@SessionAttributes(names = "product", types={Product.class})
public class IndexController {

    /*
    @Resource
    Product product;
    */

    /**
     * @ModelAttribute注释返回void值方法
     * 它把请求参数（/helloWorld?abc=text）加入到一个名为attributeName的model属性中，在它执行后helloWorld被调用，返回视图名helloWorld和model已由@ModelAttribute方法生产好了
     * @param abc
     * @param model
     */
    @ModelAttribute
    public void populateModel(@RequestParam(required = false) String abc, Model model) {
        model.addAttribute("attributeName", abc);
    }

    /**返回具体类型的方法
     * @return
     */
    @ModelAttribute("str")
    public String getModel()
    {
        return "aaa" ;
    }
    @RequestMapping(value = "/helloWorld")
    public String helloWorld() {
        return "test/helloWorld";
    }

    @RequestMapping("/getproduct")
    public String sayHello(@ModelAttribute("product") Product product, HttpServletRequest request) throws IOException
    {
        //return "Hello " + world + " ! My name is " + product.getProductName() + ", price is " + product.getProductPrice();
        HttpSession session = request.getSession();
        Enumeration enume = session.getAttributeNames();
        StringBuilder s = new StringBuilder(" Name is " + product.getProductName() + ", price is " + product.getProductPrice() + "\r");
        while (enume.hasMoreElements())
        {
           s.append(enume.nextElement()).append("\r");
        }
        return s.toString();
    }

    @ModelAttribute("product")
    public Product getProduct()
    {
        return new Product(1,"aaa","200");
    }


    @RequestMapping(value="/product/{productId}",method = RequestMethod.GET)
    public String getProduct(@PathVariable("productId") String productId){
        return productId;
    }
}
