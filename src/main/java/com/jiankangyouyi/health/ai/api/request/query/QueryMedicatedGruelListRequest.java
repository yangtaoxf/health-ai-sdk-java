package com.jiankangyouyi.health.ai.api.request.query;

import com.jiankangyouyi.health.ai.api.HealthAiRequest;
import com.jiankangyouyi.health.ai.api.PageInfo;
import com.jiankangyouyi.health.ai.api.request.query.bean.HighlightBean;
import com.jiankangyouyi.health.ai.api.response.query.QueryMedicatedGruelListResponse;

/**
 * 
 * TODO:药粥查询
 *
 * @see /v2/query/medicated_gruel/list.do
 * @author yangsongbo
 * @date 2019年1月8日
 */
public class QueryMedicatedGruelListRequest extends HealthAiRequest<QueryMedicatedGruelListResponse> {

    private static final long serialVersionUID = 1L;

    /** 查询文本 **/
    private String text;

    /** 分页 **/
    private PageInfo pageInfo;

    /**
     * 高亮设置
     */
    private HighlightBean highlight;

    /**
     * 请求链接
     */
    private static final String API_URL = "/v2/query/medicated_gruel/list.do";

    @Override
    public String getApiUrl() {
        return API_URL;
    }

    /* (non-Javadoc)
     * @see com.jiankangyouyi.health.ai.api.IHealthAiRequest#check()
     */
    @Override
    public void check() {
        // TODO Auto-generated method stub

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public HighlightBean getHighlight() {
        return highlight;
    }

    public void setHighlight(HighlightBean highlight) {
        this.highlight = highlight;
    }

}