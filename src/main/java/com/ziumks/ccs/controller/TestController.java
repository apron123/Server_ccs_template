package com.ziumks.ccs.controller;

import com.ziumks.ccs.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RequestMapping("/test")
@RestController("testController")
@Tag(name = "Test API ", description = "Test용 Api")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping("/post")
    @Operation(summary = "테스트 post api", description = "테스트")
    public void testPost(@RequestBody @Parameter(description = "테스트", required = true) String string) {
    }

    @GetMapping("{string}")
    @Operation(summary = "테스트 get api", description = "테스트")
    public String testGet(@PathVariable @Parameter(description = "테스트 패스", required = true) String string,
                              @RequestParam @Parameter(description = "테스트 파라미터", required = true) String test)
    {
        testService.testService();
        return string + " " + test;
    }

}
