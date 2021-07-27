package com.whut.water.service;

import com.whut.water.entities.Worker;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerService {
    List<Worker> listWorker();
    List<Worker> searchWorker(String worker);
    int deleteWorker(Integer wid);
    int insertWorker(Worker worker);
    int updateWorker(Worker worker);
    int adjustSalary(Integer wid,Integer workerSalary);

}
