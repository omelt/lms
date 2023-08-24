package com.lc.lms.control;


import com.lc.lms.annotate.IsAdmin;
import com.lc.lms.util.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class PageController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String page(String bookName, String bookAuthor, String bookPress){
        return "redirect:/login";
    }
}
