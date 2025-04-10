package org.xdweb.first.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    private Integer bookid;

    private String bookname;

    private String bookauthor;

    private BigDecimal bookprice;

    private Integer booktypeid;

    private String booktypename;

    private String bookdesc;

    private Byte isborrowed;

    private String bookimg;
}
