package org.xdweb.first.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Borrow {
    private Integer borrowid;

    private Integer userid;

    private String username;

    private Integer bookid;

    private String bookname;

    private Date borrowtime;

    private String borrowtimestr;

    private Date returntime;

    private String returntimestr;
}
