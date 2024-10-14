package com.ziumks.ccs.model.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties("widget.api")
public class WidgetEventDTO {
    //클라이언트 코드
    @Value("${widget.api.client.cd}")
    private String clientCd;
    
    //사이트 코드
    @Value("${widget.api.site.cd}")
    private String siteCd;

    //이벤트 그룹 코드
    @Value("${widget.api.evet.gbcd}")
    private String evetGbCd;

    //통계용
    @Value("${widget.api.outb.maingb}")
    private String outbMainGb;
    
    //이벤트 발생 시간 - 이것만 현재 시간으로 바꿔주면 됨
    private String outbDtm;

    //이벤트 등급
    @Value("${widget.api.stat.evetcd}")
    private String statEvetCd;
    
    //이벤트 내용
    @Value("${widget.api.stat.evetctnt}")
    private String statEvetCntn;

    //발생 이벤트 이름
    @Value("${widget.api.stat.evetnm}")
    private String statEvetNm;

    //이벤트 등급
    @Value("${widget.api.stat.evetgdcd}")
    private String statEvetGdCd;

    //이벤트 테마 코드
    @Value("${widget.api.svc.themecd}")
    private String svcThemeCd;

    //이벤트 유닛 서비스 코드
    @Value("${widget.api.svc.unitsvccd}")
    private String UnitSvcCd;

    //이벤트 위도 - x
    @Value("${widget.api.coodinate.x}")
    private String xCoordinate;

    //이벤트 경도 - y
    @Value("${widget.api.coodinate.y}")
    private String yCoordinate;

    //이벤트 고도 - z
    @Value("${widget.api.coodinate.z}")
    private String zCoordinate;
    //
    @Value("${widget.api.zncd}")
    private String znCd;
    //이벤트 장소
    @Value("${widget.api.outb.plac}")
    private String outbPlac;

    public Map<String, Object> getMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("clientCd", this.getClientCd());
        map.put("siteCd", this.getSiteCd());
        map.put("evetGbCd", this.getEvetGbCd());
        map.put("outbMainGb", this.getOutbMainGb());
        map.put("outbDtm", this.getOutbDtm());
        map.put("statEvetCd", this.getStatEvetCd());
        map.put("statEvetCntn", this.getStatEvetCntn());
        map.put("statEvetNm", this.getStatEvetNm());
        map.put("statEvetGdCd", this.getStatEvetGdCd());
        map.put("svcThemeCd", this.getSvcThemeCd());
        map.put("unitSvcCd", this.getUnitSvcCd());
        map.put("xCoordinate", this.getXCoordinate());
        map.put("yCoordinate", this.getYCoordinate());
        map.put("zCoordinate", this.getZCoordinate());
        map.put("znCd", this.getZnCd());
        map.put("outbPlac", this.getOutbPlac());

        return map;
    }




}
