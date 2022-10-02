package refactor.naver.reserve.reserveweb_refactor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("sample")
public class SampleController {

    @GetMapping("method1")
    public Map<String, String> method1() {
        return Map.of("key1", "value1", "key2", "value2");
    }
}
