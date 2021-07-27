package com.whut.water.mapper;

import com.whut.water.entities.History;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Mapper
@Component
public interface HistoryMapper {
    List<History> listHistory();
    History getHistoryById(Integer hid);
    List<History> searchHistory(@Param("start")String start,@Param("end") String end);
    int updateHistory(@Param("history") History history);
    int deleteHistory(@Param("hid") Integer hid);
    int insertHistory(@Param("history") History history);
}
