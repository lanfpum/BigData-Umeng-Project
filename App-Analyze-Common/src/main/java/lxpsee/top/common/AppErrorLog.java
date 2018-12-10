package lxpsee.top.common;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 10:07.
 * <p>
 * 应用上报的app错误日志相关信息
 */
public class AppErrorLog extends AppBaseLog {

    private static final long serialVersionUID = 5873571115065113408L;

    private String errorBrief;  //错误摘要
    private String errorDetail; //错误详情

    public String getErrorBrief() {
        return errorBrief;
    }

    public void setErrorBrief(String errorBrief) {
        this.errorBrief = errorBrief;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
