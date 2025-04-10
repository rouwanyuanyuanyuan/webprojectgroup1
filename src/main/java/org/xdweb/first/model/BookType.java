package org.xdweb.first.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookType {
    private Integer booktypeid;

    private String booktypename;

    private String booktypedesc;
}