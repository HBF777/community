package edu.uestc.community.controller;

import edu.uestc.community.mapper.QuestionMapper;
import edu.uestc.community.mapper.UserMapper;
import edu.uestc.community.model.Question;
import edu.uestc.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ){

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if (title == null || title.equals("")){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if (description == null || description.equals("")){
            model.addAttribute("error","表述不能为空");
            return "publish";
        }
        if (tag == null || tag.equals("")){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            return "index";
        }
        for (Cookie cookie : cookies){
            if (cookie.getName().equals("token")) {
                String token = cookie.getValue();
                user = userMapper.finByToken(token);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }
        if (user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtModified());
        questionMapper.create(question);
        return "redirect:/";
    }
}
