package com.example.demo;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@SpringBootApplication
@Controller
public class Kuchou {

    public static void main(String[] args) {
        SpringApplication.run(Kuchou.class, args);
    }

    record HaikuItem(String id, String haiku, String date, int likes) {
    }

    Kuchou(HaikuDao dao) {
        this.dao = dao;
    }

    // 俳句リスト
    private final HaikuDao dao;

    @RequestMapping("/haiku")
    String haikulist(Model model) {List<HaikuItem> haikuItems = dao.findAll();
        model.addAttribute("haikuList", haikuItems);
        return "haiku";
    }

    @RequestMapping("/addhaiku")
    String addHaiku(@RequestParam("haiku") String haiku,
            @RequestParam("date") String date) {

        // 未入力なら登録しない
        if (haiku.equals("")) {
            return "redirect:/haiku";
        }

        String id = UUID.randomUUID().toString().substring(0, 8);

        HaikuItem item = new HaikuItem(id, haiku, date, 0);

        dao.add(item);

        return "redirect:/haiku";
    }

    @RequestMapping("/like")
    String like(@RequestParam("id") String id) {

        dao.change(id);

        return "redirect:/haiku";
    }
}