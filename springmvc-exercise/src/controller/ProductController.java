package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pojo.Product;

/**
 * @author yanglh
 * @ className:
 * @ description:
 * @ create 2021-03-15 15:35
 **/
@Controller
public class ProductController {

    @RequestMapping("/addProduct")
    public ModelAndView add (Product product) throws Exception {
        return new ModelAndView ("showProduct");
    }
}
