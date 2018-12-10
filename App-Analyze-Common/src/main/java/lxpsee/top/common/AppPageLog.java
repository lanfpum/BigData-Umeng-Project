package lxpsee.top.common;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/7 10:10.
 * <p>
 * 应用上报的页面相关信息
 */
public class AppPageLog extends AppBaseLog {
    private static final long serialVersionUID = -960563767509906393L;

    /*
     * 一次启动中的页面访问次数(应保证每次启动的所有页面日志在一次上报中，即最后一条上报的页面记录的nextPage为空)
     */
    private int pageViewCntInSession = 0;
    private int visitIndex           = 0;             //访问顺序号，0为第一个页面

    private String pageId;                      //页面id
    private String nextPage;                    //下一个访问页面，如为空则表示为退出应用的页面
    private Long   stayDurationSecs = (long) 0; //当前页面停留时长

    public int getPageViewCntInSession() {
        return pageViewCntInSession;
    }

    public void setPageViewCntInSession(int pageViewCntInSession) {
        this.pageViewCntInSession = pageViewCntInSession;
    }

    public int getVisitIndex() {
        return visitIndex;
    }

    public void setVisitIndex(int visitIndex) {
        this.visitIndex = visitIndex;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public Long getStayDurationSecs() {
        return stayDurationSecs;
    }

    public void setStayDurationSecs(Long stayDurationSecs) {
        this.stayDurationSecs = stayDurationSecs;
    }
}
