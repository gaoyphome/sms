package com.bmac.ffan.modular.basebusiness.poixls;

/**
 * 封装解析XLS文件过程中出现的异常
 */
public class ParseLineInfoException extends Exception  {
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ParseLineInfoException(){
        super();
    }

    public ParseLineInfoException(String msg){
        super(msg);
        this.msg = msg;
    }

    public ParseLineInfoException(String msg, Exception e){
        super(msg, e);
        this.msg = msg;
    }
}
