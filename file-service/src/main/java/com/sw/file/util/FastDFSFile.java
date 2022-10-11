package com.sw.file.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FastDFSFile {
    private String name;

    private byte[] content;

    private String ext;

    private String md5;

    private String author;

}
