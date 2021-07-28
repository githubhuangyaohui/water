package com.whut.water.mapper;

import com.whut.water.entities.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface HistoryMapper {
    List<History> listHistory();

    History getHistoryById(Integer hid);
    List<History> getHistoryByWId(@Param("wid") Integer wid);
    List<History> getHistoryByCId(@Param("cid") Integer cid);

    List<History> searchHistory(@Param("start")String start,@Param("end") String end);
    int updateHistory(@Param("history") History history);
    int deleteHistory(@Param("hid") Integer hid);
    int insertHistory(@Param("history") History history);
    List<Map> getSalary(@Param("start") String start ,@Param("end") String end);
    List<Map> getCount();
}
