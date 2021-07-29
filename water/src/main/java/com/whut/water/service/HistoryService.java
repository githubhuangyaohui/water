package com.whut.water.service;

import com.github.pagehelper.PageInfo;
import com.whut.water.entities.History;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface HistoryService {
    List<History> listHistory();
    List<History> searchHistory(String start,String end);
    /**
     * 每页数量
     */
    public final static int PAGE_SiZE_HISTORY = 10;

    /**
     * 查询列表分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    public PageInfo<History> listHistoryForPage(Integer pageNum );

    /**
     * 表单搜索分页
     * @param pageNum 当前页码
     * @return 分页对象
     */
    PageInfo<History> searchHistory(Integer pageNum, String start, String end);


    int deleteHistory(Integer hid);
    int deleteHistoryBatch(String hidList);
    History getHistoryById(Integer hid);
    boolean isHaveHistoryByWid(Integer wid);
    boolean isHaveHistoryByCid(Integer cid);
    int updateHistory(History history,Integer custId,Integer workerId);
    int insertHistory(History history,Integer custId,Integer workerId);
    List<Map> getSalary(String start , String end);
    List<Map> getCount();
}
