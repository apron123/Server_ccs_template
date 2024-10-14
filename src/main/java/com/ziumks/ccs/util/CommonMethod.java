package com.ziumks.ccs.util;

import com.google.gson.Gson;
import com.ziumks.ccs.model.dto.BulkResponseDTO;
import com.ziumks.ccs.model.dto.WidgetEventDTO;
import com.ziumks.ccs.util.exception.HttpConnectionException;
import jakarta.persistence.Table;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//공통 메소드 묶어놓은 클래스..
@Slf4j
public class CommonMethod {
    
    /***  @Table 어노테이션으로 테이블 명 가져오는 메서드
     * @param entityClass Class<?>
     * @return string
     */
    public static String getTableName(Class<?> entityClass) {
        Table tableAnnotation = entityClass.getAnnotation(Table.class);
        if (tableAnnotation != null) {
            return tableAnnotation.name();
        }
        return null; // 테이블명이 지정되지 않은 경우
    }

    /*** 정규표현식 사용하여 숫자 말고 전부 제거
     * @param input string
     * @return string
     */
    public static String regexMethod(String input) {
        // 방법 1: 정규표현식 사용
        String result = input.replaceAll("[^0-9.]+", "");
        return result;
    }

    /*** Xml data를 변환 class 객체로 parser
     * @param xmlData string
     * @param targetClass Class<T>
     * @return Class<T>
     */
    public static <T> T xmlToObject(String xmlData, Class<T> targetClass) {
        try {
            // JAXBContext 객체 생성
            JAXBContext jaxbContext = JAXBContext.newInstance(targetClass);
            // Unmarshaller 생성
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xmlData);
            // Unmarshal 수행하여 XML을 객체로 변환
            return targetClass.cast(unmarshaller.unmarshal(reader));
        } catch (JAXBException e) {
            e.printStackTrace();
            // 여기서 언마샬 오류 처리를 수행할 수 있습니다.
            return null;
        }
    }

    // 벌크 http 통신
    public static void sendBulk(String bulkUrl, String sysUrl, List<Map<String, Object>> mapList, String indexName, String docId, String tableName) throws HttpConnectionException {
        Gson gson = new Gson();
        HttpConnection<String> httpConnection = new HttpConnection<>();
        Map<String, Object> reqParam = new HashMap<>();
        reqParam.put("dataList", mapList);
        reqParam.put("indexName", indexName);
        reqParam.put("docId", docId);
        reqParam.put("tableName", tableName);
        String jsonString = gson.toJson(reqParam);
        Map<String, Object> reqHeader = new HashMap<>();
        reqHeader.put("Content-Type", "application/json");
        String response = httpConnection.doPost(bulkUrl + "/insert", reqHeader, jsonString);
        log.info(response);
        BulkResponseDTO bulkResponse = gson.fromJson(response, BulkResponseDTO.class);
        if (bulkResponse.getResponseCode() == 200) {
            // 시스템모니터링 엘라스틱 상태 up
            sendSys(sysUrl + "/up", "bulk", tableName);
        } else {
            // 시스템모니터링 엘라스틱 상태 down
            sendSys(sysUrl + "/down", "bulk", tableName);
        }
    }

    // 시스템모니터링 http 통신
    public static void sendSys(String sysUrl, String type, String tableName) throws HttpConnectionException {
        HttpConnection<String> httpConnection = new HttpConnection<>();
        Map<String, Object> reqParam = new HashMap<>();
        reqParam.put("tableName", tableName);
        reqParam.put("type", type);
        String response = httpConnection.doGet(sysUrl, null, reqParam);
        log.info("sendSys : " + response);
    }

    // 위젯서버 http 통신
    public static String sendEvent(String widgetUrl, WidgetEventDTO rinoEventRegister) throws HttpConnectionException {

        // 현재 시간 가져오기
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

        // 현재 시간을 문자열로 변환
        String formattedTime = currentTime.format(formatter);
        rinoEventRegister.setOutbDtm(formattedTime);
        // 이벤트 발송
        HttpConnection<String> httpConnection = new HttpConnection<>();
        Map<String, Object> reqParam = rinoEventRegister.getMap();
        Map<String, Object> reqHeader = new HashMap<>();
        reqHeader.put("Content-Type", "application/x-www-form-urlencoded");

        log.info("--- event request ---");
        log.info(rinoEventRegister.toString());
        String response = httpConnection.doPost(widgetUrl, reqHeader, reqParam);
        log.info(response);
        return response;
    }


    // 현재 시간 return 하는 함수
    public static Timestamp getCurrTimestamp() {

        return new Timestamp(new Date().getTime());
    }

    /**
     * 1. entity to map
     * 2. 만약 값이 timestamp type 이면 -변환함.
     **/
    public static <T> Map<String, Object> mapEntityToMap(T entity) {
        Map<String, Object> map = new HashMap<>();

        // WeatherHtmlCrawlerEntity의 모든 필드를 가져와서 매핑
        Field[] fields = entity.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // private 필드에 접근하기 위해

            try {
                Object value = field.get(entity);

                // Timestamp 타입인 경우 LocalDateTime로 변환
                if (value instanceof Timestamp) {
                    Timestamp timestamp = (Timestamp) value;
                    value = timestamp.toLocalDateTime();
                }

                //map에 담기
                map.put(field.getName(), value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return map;
    }

    // 반올림 후 지정된 자릿수 이하는 버리기
    public static float roundAndTrim(float value, int roundDigits, int trimDigits) {
        if (roundDigits < 0 || trimDigits < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Float.toString(value));
        bd = bd.setScale(roundDigits, RoundingMode.HALF_UP); // 반올림

        // 이하 trimDigits 자리 이하는 버림
        bd = bd.setScale(roundDigits + trimDigits, RoundingMode.DOWN);

        return bd.floatValue();
    }

}
