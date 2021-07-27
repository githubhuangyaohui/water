package com.whut.water.service;

import com.whut.water.entities.History;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public interface HistoryService {
    List<History> listHistory();
    List<History> searchHistory(String start,String end);
    int deleteHistory(Integer hid);
    History getHistoryById(Integer hid);
    int updateHistory(History history,Integer custId,Integer workerId);
    int insertHistory(History history,Integer custId,Integer workerId);
    List<Map> getSalary(String start , String end);

}
