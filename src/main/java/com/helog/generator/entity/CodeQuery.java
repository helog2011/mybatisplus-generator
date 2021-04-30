package com.helog.generator.entity;
/**
 *
 * @author helog
 * @version 1.0
 * @Description:
 * @since 2018/09/17
 */
public class CodeQuery extends Code {

    private static final long serialVersionUID = 1L;
    private String keyWord;

    public CodeQuery() {
    }

    public String getKeyWord() {
        return this.keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
