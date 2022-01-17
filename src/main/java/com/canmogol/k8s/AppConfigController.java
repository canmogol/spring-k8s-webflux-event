//package com.canmogol.k8s;
//
//import com.canmogol.k8s.config.AppConfig;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//@Slf4j
//public class AppConfigController {
//
//    private final AppConfig config;
//
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(value = "/")
//    public String get() {
//        log.info("KeyValuePairs:" + config.getKeyValuePairs());
//        return config.getKeyValuePairs().toString();
//    }
//
//}