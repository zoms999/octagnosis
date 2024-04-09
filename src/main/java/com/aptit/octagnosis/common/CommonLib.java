package com.aptit.octagnosis.common;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CommonLib {
    
    // Http Header 에 값을 추가 아래에 예제 메소드 => 사용안함 => 로컬에서 Header 를 못읽어서...
    // public ResponseEntity<Map<String, Object>> GetOrgList(@RequestBody OrgParm orgParm) {
    public HttpHeaders getHeader() {
        
        // 현재 요청된 URL을 얻기 위해 HttpServletRequest를 사용합니다.
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String currentUrl = request.getRequestURI();
        
        // HttpHeaders를 사용하여 헤더를 설정합니다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Call-Id", currentUrl);
        
        return headers;
    }
    
    // 현재시간을 문자열로 추출
    public String getDateStr(String type) {
        String Pattern = "";
        switch (type)
        {
            case "YMD":
                Pattern = "yyyyMMdd";
                break;
            case "YMDHMS":
                Pattern = "yyyyMMddHHmmss";
                break;
            default:
                Pattern = "yyyyMMdd";
                break;
        }
        
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Pattern);
        return now.format(formatter);
    }
}


