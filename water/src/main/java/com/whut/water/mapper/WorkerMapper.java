package com.whut.water.mapper;

import com.whut.water.entities.Worker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface WorkerMapper {
    List<Worker> listWorker();
    List<Worker> searchWorker(@Param("workerName") String workName);
    int updateWorker(@Param("worker") Worker worker);
    int adjustSalary(@Param("wid") Integer wid,@Param("workerSalary") Integer workerSalary);
    int deleteWorker(@Param("wid") Integer wid);
    int insertWorker(@Param("worker") Worker worker);
    List<Map> workerMassage();
    List<Map> searchWorkerMassage(@Param("searchName") String searchName);
}
