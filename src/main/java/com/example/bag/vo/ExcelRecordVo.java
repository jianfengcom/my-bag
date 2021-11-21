package com.example.bag.vo;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ExcelRecordVo {
    private Long smallTypeId;
    private String smallTypeName;
    private Long brandId;
    private String brandName;

    private String pubDir;
    private String logo;
}
