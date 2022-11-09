package refactor.naver.reserve.reserveweb_refactor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @GetMapping(value = {"/", "mainpage"})
    public ModelAndView mainPage() {
        return new ModelAndView("page/mainpage");
    }

    @GetMapping("detail/{displayInfoId}")
    public ModelAndView detail(@PathVariable("displayInfoId") int displayInfoId) {
        ModelAndView mav = new ModelAndView("page/detail");
        mav.addObject("displayInfoId", displayInfoId);

        return mav;
    }

    @GetMapping("reserve/{displayInfoId}")
    public ModelAndView reserve(@PathVariable("displayInfoId") int displayInfoId) {
        ModelAndView mav = new ModelAndView("page/reserve");
        mav.addObject("displayInfoId", displayInfoId);

        return mav;
    }

    @GetMapping("loginPage")
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("page/login");
        mav.addObject("loginPage", true);

        return mav;
    }

    @GetMapping("myPage")
    public ModelAndView myPage() {
        ModelAndView mav = new ModelAndView("page/mypage");
        mav.addObject("myPage", true);

        return mav;
    }

    @GetMapping("myReserve")
    public ModelAndView myReserve() {
        ModelAndView mav = new ModelAndView("page/myreservation");

        return mav;
    }
}
